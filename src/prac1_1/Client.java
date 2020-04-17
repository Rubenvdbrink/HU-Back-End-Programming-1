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

        while(true) {
            String text = keyboardScan.nextLine();
            text += "\r\n";
            os.write(text.getBytes());
        }
    }
}
