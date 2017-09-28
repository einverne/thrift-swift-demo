package info.einverne.spring.swift;

import com.facebook.nifty.processor.NiftyProcessorAdapters;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServiceProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * thrift server is listen at 8080
 */
@Configuration
@ComponentScan
public class SwiftServerConfig {

    @Bean
    public TProtocolFactory tProtocolFactory() {
        return new TBinaryProtocol.Factory();
    }

    @Bean
    ThriftCodecManager thriftCodecManager() {
        return new ThriftCodecManager();
    }

    @Bean
    public ServletRegistrationBean swiftBookServlet(ThriftCodecManager thriftCodecManager, TProtocolFactory tProtocolFactory, BookServiceImpl bookService)
            throws TTransportException {
        ThriftServiceProcessor processor = new ThriftServiceProcessor(thriftCodecManager, Arrays.<ThriftEventHandler>asList(), bookService);

        TServlet tServlet = new TServlet(NiftyProcessorAdapters.processorToTProcessor(processor), tProtocolFactory, tProtocolFactory);
        return new ServletRegistrationBean(tServlet, "/book");
    }
}
