package info.einverne.thrift.server;

import info.einverne.thrift.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
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
        // 传输层
        TServerSocket serverTransport = new TServerSocket(9000);// 也可以设置超时时间
        // 协议层
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory(true, true);// 可读可写
        // 处理层
        HelloService.Iface handler = new HelloServiceImpl();
        HelloService.Processor<HelloService.Iface> processor = new HelloService.Processor<HelloService.Iface>(handler);
        // 服务层
        TServer server = new TThreadPoolServer(
                new TThreadPoolServer.Args(serverTransport).protocolFactory(protocolFactory).processor(
                        processor));
        // 启动服务监听
        server.serve();
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
            return "Hello " + name;
        }
    }

}
