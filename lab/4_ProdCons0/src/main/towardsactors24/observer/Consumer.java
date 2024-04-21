package main.towardsactors24.observer;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;
import unibo.naiveactors24.ActorBasic24;
import unibo.naiveactors24.ActorContext24;

public class Consumer extends ActorBasic24 {
    private Logger logger;

    public Consumer(String name, ActorContext24 ctx) {
        super(name, ctx);
        activateAndStart();
    }

    @Override
    protected void elabMsg(IApplMessage msg) throws Exception {
        CommUtils.outyellow(name + " | elabMsg " + msg + " " + Thread.currentThread().getName());

        handleMsg(msg);
    }

    public void handleMsg(IApplMessage msg) {
        CommUtils.outgreen(name + "  | consumes " + msg.msgContent() + " sent by " + msg.msgSender() + " "
                + Thread.currentThread().getName());

        if (msg.msgId().equals("update")) {
            IApplMessage logMsg = CommUtils.buildDispatch(name, "update", msg.msgContent(), "obslogger");

            forward(logMsg);

            String outMsg = "ack(" + msg.msgContent() + ")";
            IApplMessage reply = CommUtils.buildReply(name, "ack", outMsg, msg.msgSender());
            CommUtils.outblue(name + "  | sends reply= " + reply);
            reply(msg, reply);
        }
    }
}
