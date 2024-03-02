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

public class Consumer extends Thread implements Interaction {
    final DataInputStream input;
    final DataOutputStream output;
    final Socket socket;

    public Consumer(Socket socket, DataInputStream input, DataOutputStream output) {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    @Override
    public void forward(String msg) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public void forward(IApplMessage msg) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public String request(String msg) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public IApplMessage request(IApplMessage msg) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public IApplMessage request(IApplMessage msg, int tout) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public void reply(String reqid) throws Exception {
        IApplMessage reply = unibo.basicomm23.utils.BasicMsgUtil.buildReply(this.getName(), reqid, "pong", "producer");

        try {
            this.reply(reply);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reply(IApplMessage msg) throws Exception {
        try {
            this.output.writeUTF(msg.msgContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String receiveMsg() throws Exception {
        try {
            return this.input.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public IApplMessage receive() throws Exception {
        try {
            String received = this.receiveMsg();

            return unibo.basicomm23.utils.BasicMsgUtil.buildRequest("producer", received, received, received);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() throws Exception {
        try {
            this.input.close();
            this.output.close();
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        IApplMessage msg;

        while (true) {

            try {
                msg = this.receive();

                System.out.println("Message received: " + msg.msgContent());

                this.reply(msg.msgId());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
