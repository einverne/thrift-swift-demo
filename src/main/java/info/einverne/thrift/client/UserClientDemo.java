package info.einverne.thrift.client;

import info.einverne.thrift.HelloException;
import info.einverne.thrift.Request;
import info.einverne.thrift.User;
import info.einverne.thrift.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

/**
 * Created by mi on 17-9-25.
 */
public class UserClientDemo {
    public static void main(String[] args) {
        try {
            System.out.println("starting client");
            //建立socket连接
            //如果是非阻塞型  需要使用
            TTransport tSocket = new TFramedTransport(new TSocket("127.0.0.1", 8081, 30000));
            //设置封装协议
            TBinaryProtocol protocol = new TBinaryProtocol(tSocket);
            //建立调用client
            UserService.Client client = new UserService.Client(protocol);
            //设置调用参数:
            Request request = new Request().setName("loginName").setPassword("123456");
            //准备传输
            tSocket.open();
            //正式调用接口
            List<User> users = client.getAllStudent(request);

            // test
            int max = 100000;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < max; i++) {
                client.ping();
            }
            long endTime = System.currentTimeMillis();
            long elpase = endTime - startTime;
            System.out.println("call thrift method " + max + " times, cost " + elpase + " ms");

            //请求结束，断开连接
            tSocket.close();
            for (User user : users) {
                System.out.println(user.getName() + ":" + user.getAge());
            }
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (HelloException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }

    }
}
