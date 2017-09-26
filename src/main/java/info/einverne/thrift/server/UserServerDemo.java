package info.einverne.thrift.server;

import info.einverne.thrift.HelloException;
import info.einverne.thrift.Request;
import info.einverne.thrift.User;
import info.einverne.thrift.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 17-9-25.
 */
public class UserServerDemo {
    public static void main(String[] args) {
        try {
            System.out.println("服务启动");
            // 非阻塞式
            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8081);
            // 服务执行控制器,类似于rmi中的bind
            UserService.Processor<UserService.Iface> processor = new UserService.Processor<UserService.Iface>(new UserServiceImpl());
            // 为服务器设置对应的IO网络模型
            TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverSocket);
            // 设置控制器
            tArgs.processor(processor);
            //设置传输方式
            // tArgs.transportFactory(new TFramedTransport.Factory());
            // 设置消息封装格式
            tArgs.protocolFactory(new TBinaryProtocol.Factory());//Thrift特有的一种二进制描述格式
            // 启动Thrift服务
            TNonblockingServer server = new TNonblockingServer(tArgs);
            server.serve();//启动后,程序就停在这里了。
            System.out.println("服务结束");
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static class UserServiceImpl implements UserService.Iface {
        @Override
        public void ping() throws TException {
            System.out.println("pong");
        }

        @Override
        public List<User> getAllStudent(Request request) throws HelloException, TException {
            System.out.println(request.getName());
            System.out.println(request.getPassword());
            List<User> users = new ArrayList<User>();
            for (int i = 0; i < 2; i++) {
                User user = new User("name" + i, i);
                users.add(user);
            }
            return users;
        }
    }
}
