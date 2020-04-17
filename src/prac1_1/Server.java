package prac1_1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4711);
        Socket s = ss.accept();
        InputStream is = s.getInputStream();

        int c = is.read();
        while (true) {
            System.out.println((char) c);
            c = is.read();
        }
//        s.close();
//        ss.close();
    }
}
