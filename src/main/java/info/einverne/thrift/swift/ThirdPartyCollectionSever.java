package info.einverne.thrift.swift;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by mi on 17-9-25.
 */
public class ThirdPartyCollectionSever {
    public static void main(String[] args) {
        ThriftServiceProcessor processor = new ThriftServiceProcessor(
                new ThriftCodecManager(),
                ImmutableList.<ThriftEventHandler>of(),
                new ThirdPartyCollectionServiceImpl()
        );

        ExecutorService taskWorkerExecutor = newFixedThreadPool(1);

        ThriftServerDef serverDef = ThriftServerDef.newBuilder()
                .listen(8082)
                .withProcessor(processor)
                .using(taskWorkerExecutor)
                .build();

        ExecutorService bossExecutor = newCachedThreadPool();
        ExecutorService ioWorkerExecutor = newCachedThreadPool();
        //依赖于NettyServerTransport类来完成服务器的相关功能
        NettyServerConfig serverConfig = NettyServerConfig.newBuilder()
                .setBossThreadExecutor(bossExecutor)
                .setWorkerThreadExecutor(ioWorkerExecutor)
                .build();

        ThriftServer server = new ThriftServer(serverConfig, serverDef);
        server.start();
        //首先封装了ThriftServerDef和NettyServerConfig，由这两个参数来构造一个ThriftServer对象，然后启动Thrift服务。对外就可以提供端口号为8082的高并发的Thrift协议服务能力。
    }

    public static class ThirdPartyCollectionServiceImpl implements ThirdPartyCollectionIface {

        @Override
        public ThirdPartyCollection update(@ThriftField(name = "collection") ThirdPartyCollection collection) {
            System.out.println("id " + collection.getId());
            System.out.println("date " + collection.getDate());
            return collection;
        }

        @Override
        public List<ThirdPartyCollection> getAll() {
            List<ThirdPartyCollection> list = new ArrayList<ThirdPartyCollection>();
            for (int i = 0; i < 4; i++) {
                ThirdPartyCollection collection = new ThirdPartyCollection(1234L + i, "2017-01-0" + i);
                list.add(collection);
            }
            return list;
        }
    }
}
