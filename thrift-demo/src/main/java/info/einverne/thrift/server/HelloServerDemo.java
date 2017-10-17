package info.einverne.thrift.server;

import info.einverne.thrift.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 服务端测试类
 */
public class HelloServerDemo {
    /**
     * 演示代码
     *
     * @param args 默认参数
     * @throws TTransportException 异常
     */
    public static void main(String[] args) throws TTransportException {
        System.out.println("starting server");
        /*
         * 传输层
         * Transport 层提供了一个简单的网络读写抽象层。这使得 thrift 底层的 transport 从系统其它部分（如：序列化/反序列化）解耦。
         */
        TServerSocket serverTransport = new TServerSocket(9000);// 也可以设置超时时间

        /*
         * 协议层
         * Protocol抽象层定义了一种将内存中数据结构映射成可传输格式的机制。换句话说，Protocol定义了datatype怎样使用底层的Transport对自己进行编解码。因此，Protocol的实现要给出编码机制并负责对数据进行序列化。
         *
         * TBinaryProtocol – 二进制格式.
        TCompactProtocol – 压缩格式
        TJSONProtocol – JSON格式
        TSimpleJSONProtocol –提供JSON只写协议, 生成的文件很容易通过脚本语言解析。
         */
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory(true, true);// 可读可写
        /* 处理层
         * Processor封装了从输入数据流中读数据和向数据数据流中写数据的操作。读写数据流用Protocol对象表示。Processor接口的定义非常简单, 相关服务的 processor 由编译器产生
         * Processor 从连接中读取数据，使用输入 protocol
         * 将数据授权给 handler 用户实现
         * 将结果写到输出 protocol 上
         */
        HelloService.Iface handler = new HelloServiceImpl();
        HelloService.Processor<HelloService.Iface> processor = new HelloService.Processor<HelloService.Iface>(handler);
        /*
         * 服务层
         * thrift 各服务器比较说明
         * TSimplerServer 接受一个连接，处理连接请求，直到客户端关闭了连接，它才回去接受一个新的连接。正因为它只在一个单独的线程中以阻塞I/O的方式完成这些工作，所以它只能服务一个客户端连接，其他所有客户端在被服务器端接受之前都只能等待。TSimpleServer 主要用于测试目的，不要在生产环境中使用它
         * TNonblockingServer 使用非阻塞的I/O解决了 TSimpleServer 一个客户端阻塞其他所有客户端的问题。它使用了java.nio.channels.Selector，通过调用select()，它使得你阻塞在多个连接上，而不是阻塞在单一的连接上。当一或多个连接准备好被接受/读/写时，select()调用便会返回。TNonblockingServer处理这些连接的时候，要么接受它，要么从它那读数据，要么把数据写到它那里，然后再次调用select()来等待下一个可用的连接。通用这种方式，server可同时服务多个客户端，而不会出现一个客户端把其他客户端全部“饿死”的情况
         * THsHaServer 所有消息是被调用select()方法的同一个线程处理的。假设有10个客户端，处理每条消息所需时间为100毫秒，那么，latency和吞吐量分别是多少？当一条消息被处理的时候，其他9个客户端就等着被select，所以客户端需要等待1秒钟才能从服务器端得到回应，吞吐量就是10个请求/秒。如果可以同时处理多条消息的话，会很不错吧？因此，THsHaServer（半同步/半异步的server）就应运而生了。它使用一个单独的线程来处理网络I/O，一个独立的worker线程池来处理消息。这样，只要有空闲的worker线程，消息就会被立即处理，因此多条消息能被并行处理。用上面的例子来说，现在的latency就是100毫秒，而吞吐量就是100个请求/秒。
         * TThreadedSelectorServer Thrift 0.8引入了另一种server实现，即TThreadedSelectorServer。它与THsHaServer的主要区别在于，TThreadedSelectorServer 允许你用多个线程来处理网络I/O。它维护了两个线程池，一个用来处理网络I/O，另一个用来进行请求的处理。当网络I/O是瓶颈的时候，TThreadedSelectorServer 比 THsHaServer的表现要好。
         * TThreadPoolServer 与其他三种 server 不同的是有一个专用的线程用来接受连接。一旦接受了一个连接，它就会被放入ThreadPoolExecutor中的一个worker线程里处理。 worker线程被绑定到特定的客户端连接上，直到它关闭。一旦连接关闭，该worker线程就又回到了线程池中。 你可以配置线程池的最小、最大线程数，默认值分别是5（最小）和Integer.MAX_VALUE（最大）。这意味着，如果有1万个并发的客户端连接，你就需要运行1万个线程。所以它对系统资源的消耗不像其他类型的server一样那么“友好”。此外，如果客户端数量超过了线程池中的最大线程数，在有一个worker线程可用之前，请求将被一直阻塞在那里。TThreadPoolServer的表现非常优异。如果你提前知道了将要连接到你服务器上的客户端数量，并且你不介意运行大量线程的话，TThreadPoolServer 对你可能是个很好的选择。
         * from: https://beyondalbert.com/thrift-in-springboot-demo/#toc-8
         *
         * Server 将所有的特性集成
         * 1. 创建 TTransport
         * 2. 为 TTransport 创建输入输出 TProtocol
         * 3. 基于输入输出 TProtocol 创建 TProcessor
         * 4. 等待连接并交给 TProcessor 处理
         * http://dongxicheng.org/search-engine/thrift-guide/
         */
        TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).protocolFactory(protocolFactory).processor(processor));
        // 启动服务监听
        server.serve();
    }

    public static int port = 9000;
    public static int clientTimeout = 1000;

    /**
     * 简单服务器，单线程阻塞
     */
    public static void startSimpleServer() {
        try {
            TServerSocket serverSocket = new TServerSocket(port, clientTimeout);
            TBinaryProtocol.Factory protocol = new TBinaryProtocol.Factory(true, true);
            TProcessor processor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
            TServer.Args args = new TServer.Args(serverSocket).protocolFactory(protocol).processor(processor);
            TServer server = new TSimpleServer(args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程服务器，阻塞多线程
     */
    public static void startThreadPoolServer() {
        try {
            TServerSocket serverSocket = new TServerSocket(port, clientTimeout);
            TBinaryProtocol.Factory protocol = new TBinaryProtocol.Factory(true, true);
            TProcessor processor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverSocket).protocolFactory(protocol).processor(processor);
            TServer server = new TThreadPoolServer(args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * 非阻塞I/O
     */
    public static void startTNonBlockingServer() {
        try {
            TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(port, clientTimeout);
            TCompactProtocol.Factory protocol = new TCompactProtocol.Factory();
            TFramedTransport.Factory transport = new TFramedTransport.Factory(); // 创建transport 数据传输方式 非阻塞需要用这种方式传输
            TProcessor processor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
            TNonblockingServer.Args args = new TNonblockingServer.Args(tNonblockingServerSocket).processor(processor).protocolFactory(protocol).transportFactory(transport);
            TNonblockingServer server = new TNonblockingServer(args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * 半同步半异步的非阻塞 I/O
     */
    public static void startTHsHaServer() {
        try {
            TProcessor processor = new HelloService.Processor<HelloService.Iface>(new HelloServiceImpl());
            TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(port, clientTimeout);
            TCompactProtocol.Factory factory = new TCompactProtocol.Factory();
            TFramedTransport.Factory transport = new TFramedTransport.Factory();
            THsHaServer.Args args = new THsHaServer.Args(tNonblockingServerSocket).processor(processor).protocolFactory(factory).transportFactory(transport);
            THsHaServer server = new THsHaServer(args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现接口
     */
    public static class HelloServiceImpl implements HelloService.Iface {
        /**
         * 实现接口方法
         *
         * @param name 显示的参数
         * @return 返回字符串
         * @throws TException 异常
         */
        public String hello(String name)
                throws TException {
            System.out.println("client param + " + name);
            return "Hello " + name;
        }
    }

}
