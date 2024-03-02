package _prodcons.tcp;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.IServer;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.Connection;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class ConsumerServer extends Thread {
    private final String name = "ConsumerServerTCP";
    private int port;
    private ServerSocket socket;

    public ConsumerServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new ServerSocket(port);

            System.out.println("Server " + this.name + " started on port " + port);

            while (true) {
                Socket connection = socket.accept();

                System.out.println("A new client is connected : " + connection);

                DataInputStream input = new DataInputStream(connection.getInputStream());
                DataOutputStream output = new DataOutputStream(connection.getOutputStream());

                new Thread(new Consumer(connection, input, output)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
