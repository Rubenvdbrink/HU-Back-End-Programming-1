package prac1_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class  Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4711);
        while(true) {
            Socket s = ss.accept();
            Scanner inputScanner = new Scanner(s.getInputStream());
            StringBuilder outputString = new StringBuilder();
            while(inputScanner.hasNextLine()) {
                String tempString = inputScanner.nextLine();
                outputString.append(tempString + "\n");
                if (tempString.isBlank()){
                    break;
                }
            }
            s.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
            s.getOutputStream().write("<h1>It works!</h1>".getBytes());
            s.close();
        }
    }
}