package prac2_1;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        MyThread mt1 = new MyThread("one", 4711);

        mt1.start();
    }
}