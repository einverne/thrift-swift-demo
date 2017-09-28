package info.einverne.spring.swift;

import com.facebook.swift.codec.ThriftField;
import org.springframework.stereotype.Service;

/**
 * Created by mi on 17-9-28.
 */
@Service
public class BookServiceImpl implements BookService {
    @Override
    public void ping() {
        System.out.println("pong");
    }

    @Override
    public void createBook(@ThriftField(value = 1, name = "book", requiredness = ThriftField.Requiredness.NONE) Book book) {
        System.out.println("create book " + book.toString());
    }
}
