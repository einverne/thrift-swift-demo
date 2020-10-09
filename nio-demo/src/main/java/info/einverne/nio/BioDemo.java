package info.einverne.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @since 2020-10-09
 */
public class BioDemo {


  /**
   * server.accept() and br.readLine() block
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    ServerSocket server = new ServerSocket(8080);
    while (true) {
      Socket socket = server.accept();
      BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      System.out.println("socket input stream");
      String buffer = null;
      while ((buffer = br.readLine()) != null && !buffer.equals("")) {
        System.out.println(buffer);
      }
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      bw.write("HTTP/1.1 200 OK\n");
      bw.write("Content-Type: text/html;charset=UTF-8\n\n");
      bw.write("Blocking IO");
      bw.flush();
      bw.close();
      br.close();
      socket.close();
    }
  }

}
