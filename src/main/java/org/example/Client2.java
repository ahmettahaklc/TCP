package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",6500);

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(inputStream.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();


        Thread thread1 = new Thread(() -> {
            while(true){
                try {
                    outputStream.writeUTF(scanner.nextLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }
}
