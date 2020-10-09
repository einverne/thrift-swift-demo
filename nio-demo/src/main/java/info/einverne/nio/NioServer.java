package info.einverne.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @since 2020-10-09
 */
public class NioServer {

  /**
   * SPI 调用不同平台的方法，Win 的 iocp, Linux 的 epoll, BSD 的 kqueue
   */
  private Selector selector;

  public void init() throws IOException {
    // initiate Selector
    selector = Selector.open();
    ServerSocketChannel channel = ServerSocketChannel.open();
    channel.configureBlocking(false);
    ServerSocket serverSocket = channel.socket();
    InetSocketAddress address = new InetSocketAddress(8080);
    serverSocket.bind(address);
    System.out.println("Nio bind port 8080");
    channel.register(selector, SelectionKey.OP_ACCEPT);
  }

  public void start() throws IOException {
    while (true) {
      selector.select(1000);
      Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        iterator.remove();
        if (key.isAcceptable()) {
          System.out.println("accept");
          accept(key);
        } else if (key.isReadable()) {
          System.out.println("read");
          read(key);
        }
      }
    }
  }

  private void read(SelectionKey key) throws IOException {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    SocketChannel channel = (SocketChannel) key.channel();
    channel.read(buffer);
    String request = new String(buffer.array()).trim();
    System.out.println("request: " + request);
    String outString = "HTTP/1.1 200 OK\n" + "Content-Type: text/html;charset=UTF-8\n\n" + "NIO success";
    ByteBuffer outBuffer = ByteBuffer.wrap(outString.getBytes());
    channel.write(outBuffer);
    channel.close();
  }

  private void accept(SelectionKey key) throws IOException {
    ServerSocketChannel server = (ServerSocketChannel) key.channel();
    SocketChannel channel = server.accept();
    channel.configureBlocking(false);
    channel.register(selector, SelectionKey.OP_READ);
  }

  public static void main(String[] args) throws IOException {
    NioServer server = new NioServer();
    server.init();
    server.start();
  }

}
