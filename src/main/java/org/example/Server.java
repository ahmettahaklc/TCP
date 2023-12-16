package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6500);
        ArrayList<Socket> sockets = new ArrayList<>();

        while (true) {
            Socket socket = serverSocket.accept();

            sockets.add(socket);
            new Thread(() -> {
                try {
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    while (true) {
                        String str = inputStream.readUTF();
                        for (Socket _socket : sockets) {
                            DataOutputStream outputStream = new DataOutputStream(_socket.getOutputStream());
                            outputStream.writeUTF(str);
                        }
                    }
                } catch (Exception e) {

                }
            }).start();
        }
    }
}
