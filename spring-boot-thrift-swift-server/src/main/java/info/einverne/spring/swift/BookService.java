package info.einverne.spring.swift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

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
            @ThriftField(value=1, name="book", requiredness=Requiredness.NONE) final Book book
        );
    }
    @ThriftMethod(value = "ping")
    void ping() throws org.apache.thrift.TException;

    @ThriftMethod(value = "createBook")
    void createBook(
        @ThriftField(value=1, name="book", requiredness=Requiredness.NONE) final Book book
    ) throws org.apache.thrift.TException;
}