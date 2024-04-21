package _prodcons.tcp;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import java.io.*;
import java.net.Socket;

public class Producer extends Thread implements Interaction {
    final String id;
    String host;
    int port;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    int seq = 0;

    public Producer(String host, int port) {
        this.id = "Producer " + java.util.UUID.randomUUID().toString();
        this.host = host;
        this.port = port;

        try {
            this.socket = new Socket(this.host, this.port);
            this.input = new DataInputStream(this.socket.getInputStream());
            this.output = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IApplMessage receive() throws Exception {
        throw new Exception("Not implemented");
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
        try {
            IApplMessage req = unibo.basicomm23.utils.BasicMsgUtil.buildRequest(this.id, String.valueOf(++seq), msg,
                    "consumer");

            this.output.writeUTF(req.toString());

            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
        throw new Exception("Not implemented");
    }

    @Override
    public void reply(IApplMessage msg) throws Exception {
        throw new Exception("Not implemented");
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
    public void close() throws Exception {
        try {
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(this.id + " sending request");

                this.request("ping");

                String received = this.receiveMsg();

                System.out.println(this.id + " received:" + received);

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
