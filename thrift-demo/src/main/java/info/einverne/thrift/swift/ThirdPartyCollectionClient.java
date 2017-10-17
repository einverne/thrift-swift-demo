package info.einverne.thrift.swift;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mi on 17-9-25.
 */
public class ThirdPartyCollectionClient {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ThriftClientManager clientManager = new ThriftClientManager();
        FramedClientConnector connector = new FramedClientConnector(new InetSocketAddress("localhost", 8082));
        ThirdPartyCollectionIface scribe = clientManager.createClient(connector, ThirdPartyCollectionIface.class).get();
        List<ThirdPartyCollection> all = scribe.getAll();
        for (ThirdPartyCollection c : all) {
            System.out.println("id " + c.getId() + " date " + c.getDate());
        }
        ThirdPartyCollection collection = new ThirdPartyCollection(1001, "2014-08-29");
        scribe.update(collection);
    }
}
