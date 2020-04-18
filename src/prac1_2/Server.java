package prac1_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4711);
        while(true) {
            Socket s = ss.accept();
            Scanner inputScanner = new Scanner(s.getInputStream());

            while(inputScanner.hasNextLine()) {
                System.out.println(inputScanner.nextLine());
            }
            s.close();
        }
    }
}