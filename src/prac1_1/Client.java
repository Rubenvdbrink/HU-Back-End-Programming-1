package prac1_1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 4711);
        OutputStream os = s.getOutputStream();
        Scanner keyboardScan = new Scanner(System.in);
        String text = keyboardScan.nextLine();
        os.write(text.getBytes());
        s.close();
    }
}
