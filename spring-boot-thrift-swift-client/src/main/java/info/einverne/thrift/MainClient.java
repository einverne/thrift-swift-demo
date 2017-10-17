package info.einverne.thrift;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import static com.google.common.net.HostAndPort.fromParts;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mi on 17-10-17.
 */
public class MainClient {
    public static void main(String[] args) {

        try {
            ThriftClientManager clientManager = new ThriftClientManager();
            FramedClientConnector connector = new FramedClientConnector(fromParts("localhost", 8088));
            BookService bookService = clientManager.createClient(connector, BookService.class).get();
            bookService.ping();
            List<String> keywords = Arrays.asList("科幻");
            Book book = new Book("isbn", "title", "author", 100, keywords, BookType.BOOK);
            bookService.createBook(book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
