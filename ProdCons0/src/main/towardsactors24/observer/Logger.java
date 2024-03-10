package main.towardsactors24.observer;

import java.io.FileWriter;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;
import unibo.naiveactors24.ActorBasic24;
import unibo.naiveactors24.ActorContext24;

public class Logger extends ActorBasic24 {
    private FileWriter fileWriter;

    public Logger(String name, ActorContext24 ctx) {
        super(name, ctx);
        activateAndStart();

        CommUtils.outmagenta("Logger " + name + " activated");

        try {
            fileWriter = new FileWriter("obsloggerLog.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void elabMsg(IApplMessage msg) throws Exception {
        System.out.println("MSGID: " + msg.msgId());
        if (!msg.msgId().equals("update")) {
            return;
        }

        fileWriter.write(msg.toString() + "\n");
        fileWriter.flush();
    }
}
