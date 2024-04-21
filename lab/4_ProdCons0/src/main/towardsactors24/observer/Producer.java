package main.towardsactors24.observer;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;
import unibo.naiveactors24.ActorBasic24;
import unibo.naiveactors24.ActorContext24;

public class Producer extends ActorBasic24 {
    protected String msgId = "update";
    protected String msgReceiver = "consumer";
    protected String pfx = "        ";

    public Producer(String name, ActorContext24 ctx) {
        super(name, ctx);
        activateAndStart();
    }

    @Override
    protected void elabMsg(IApplMessage msg) throws Exception {
        CommUtils.outblue(pfx + name + " | elabMsg " + msg + " " + Thread.currentThread().getName());

        if (msg.msgContent().equals("ack(update)")) {
            IApplMessage logMsg = CommUtils.buildDispatch(name, "update", msg.msgContent(), "obslogger");

            forward(logMsg);
        }
    }

    public void sendUpdate(String s) {
        IApplMessage msg = CommUtils.buildDispatch(name, msgId, s, msgReceiver);
        forward(msg);
    }
}
