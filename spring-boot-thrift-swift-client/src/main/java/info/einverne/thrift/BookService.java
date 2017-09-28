package info.einverne.thrift;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.google.common.util.concurrent.ListenableFuture;

@ThriftService("BookService")
public interface BookService
{
    @ThriftService("BookService")
    public interface Async
    {
        @ThriftMethod(value = "ping")
        ListenableFuture<Void> ping();

        @ThriftMethod(value = "createBook")
        ListenableFuture<Void> createBook(
                @ThriftField(value = 1, name = "book", requiredness = Requiredness.NONE) final Book book
        );
    }
    @ThriftMethod(value = "ping")
    void ping();


    @ThriftMethod(value = "createBook")
    void createBook(
            @ThriftField(value = 1, name = "book", requiredness = Requiredness.NONE) final Book book
    );

}