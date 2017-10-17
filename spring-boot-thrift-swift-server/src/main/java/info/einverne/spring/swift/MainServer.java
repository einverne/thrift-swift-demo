package info.einverne.spring.swift;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.concurrent.ExecutorService;

/**
 * 如果不使用 Spring boot 提供的 main 方法，直接使用 ThriftServer 也可以启动 Server
 *
 * https://github.com/facebook/swift/tree/master/swift-service
 */
public class MainServer {

    public static void main(String[] args) {
        ExecutorService executorService;
        ThriftServer thriftServer;
        ExecutorService bossExecutor;
        ExecutorService ioWorkerExecutor;

        ThriftServiceProcessor processor = new ThriftServiceProcessor(
                new ThriftCodecManager(),
                ImmutableList.<ThriftEventHandler>of(),
                new BookServiceImpl()
                );

        executorService = newFixedThreadPool(1);
        ThriftServerDef serverDef = ThriftServerDef.newBuilder()
                .listen(8088)
                .withProcessor(processor)
                .using(executorService)
                .build();

        bossExecutor = newCachedThreadPool();
        ioWorkerExecutor = newCachedThreadPool();
        NettyServerConfig serverConfig = NettyServerConfig.newBuilder()
                .setBossThreadExecutor(bossExecutor)
                .setWorkerThreadExecutor(ioWorkerExecutor)
                .build();
        thriftServer = new ThriftServer(serverConfig, serverDef);
        thriftServer.start();
    }
}
