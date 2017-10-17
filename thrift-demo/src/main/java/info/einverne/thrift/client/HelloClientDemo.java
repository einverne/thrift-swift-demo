package info.einverne.thrift.client;

import info.einverne.thrift.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Thrift Client 测试类
 *
 * @author Administrator
 */
public class HelloClientDemo {
    /**
     * @param args 默认函数
     * @throws TException 异常
     */
    public static void main(String[] args) throws TException {
        System.out.println("starting client");
        /*
         * 传输层
         * Transport 层提供了一个简单的网络读写抽象层。这使得 thrift 底层的 transport 从系统其它部分（如：序列化/反序列化）解耦。
         * TSocket -阻塞式socker
        TFramedTransport – 以frame为单位进行传输，非阻塞式服务中使用。
        TFileTransport – 以文件形式进行传输。
        TMemoryTransport – 将内存用于I/O. java实现时内部实际使用了简单的ByteArrayOutputStream。
        TZlibTransport – 使用zlib进行压缩， 与其他传输方式联合使用。当前无java实现。
         */
        TTransport transport = new TSocket("127.0.0.1", 9000);
        transport.open();
        /*
         * 协议层
         * Protocol抽象层定义了一种将内存中数据结构映射成可传输格式的机制。换句话说，Protocol定义了datatype怎样使用底层的Transport对自己进行编解码。因此，Protocol的实现要给出编码机制并负责对数据进行序列化。
         */
        TProtocol protocol = new TBinaryProtocol(transport);

        // 创建RPC客户端
        HelloService.Client client = new HelloService.Client(protocol);

        // 调用服务
        String result = client.hello("there");
        System.out.printf("-->" + result);

        // 关闭通道
        transport.close();
    }
}
