package prac2_1;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {

    private String name;
    private int sNum;

    public MyThread(String name, int sNum){
        this.name = name;
        this.sNum = sNum;
    }

    public void run() {
        try {
            ServerSocket ss = new ServerSocket(sNum);
            StringBuilder outputString;
            while (true) {
                Socket s = ss.accept();
                Scanner inputScanner = new Scanner(s.getInputStream());
                outputString = new StringBuilder();
                while (inputScanner.hasNextLine()) {
                    String tempString = inputScanner.nextLine();
                    outputString.append(tempString).append("\n");
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
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
