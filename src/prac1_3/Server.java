package prac1_3;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4711);
        StringBuilder outputString;
        while (true) {
            Socket s = ss.accept();
            Scanner inputScanner = new Scanner(s.getInputStream());
            outputString = new StringBuilder();
            while (inputScanner.hasNextLine()) {
                String tempString = inputScanner.nextLine();
                outputString.append(tempString + "\n");
                if (tempString.isBlank()) {
                    break;
                }
            }
            String fileName = "";
            try {
                s.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                fileName = outputString.toString().split("\n")[0].split(" ")[1];
                fileName = fileName.substring(1);
                s.getOutputStream().write(Files.readAllBytes(Paths.get("files/" + fileName)));
            } catch (NoSuchFileException ne) {
                s.getOutputStream().write(String.format("<h1>Sorry, %s niet gevonden!</h1>", fileName).getBytes());
            }
        }
    }
}
