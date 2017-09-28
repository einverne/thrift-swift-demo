package info.einverne.thrift;

import com.facebook.swift.codec.ThriftCodecManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mi on 17-9-28.
 */
@Configuration
@ComponentScan
public class SwiftClientConfig {

    @Bean
    ThriftCodecManager thriftCodecManager() {
        return new ThriftCodecManager();
    }
}
