package info.einverne.thrift;

import com.facebook.nifty.client.HttpClientConnector;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;

/**
 * Created by mi on 17-9-28.
 */
@RestController
public class BookSwiftController {
    @Autowired
    ThriftCodecManager thriftClientManager;
    private BookService client;

    @PostConstruct
    public void init() {
        try {
            HttpClientConnector connector = new HttpClientConnector(URI.create("http://localhost:8084/book"));
            ThriftClientManager clientManager = new ThriftClientManager(thriftClientManager);
            client = clientManager.createClient(connector, BookService.class).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/ping")
    public void ping() {
        client.ping();
    }

    @RequestMapping("/create")
    public void create() {
        List<String> keywords = Arrays.asList("科幻");
        Book book = new Book("isbn", "title", "author", 100, keywords, BookType.BOOK);
        client.createBook(book);
    }
}
