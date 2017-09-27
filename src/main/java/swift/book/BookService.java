package swift.book;

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
    }
    @ThriftMethod(value = "ping")
    void ping();

}