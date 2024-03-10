package main.towardsactors24.observer;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;
import unibo.naiveactors24.ActorBasic24;
import unibo.naiveactors24.ActorContext24;

public class Observer extends ActorBasic24 {
    public Observer(String name, ActorContext24 ctx) {
        super(name, ctx);
        activateAndStart();
    }

    @Override
    protected void elabMsg(IApplMessage msg) throws Exception {
        if (msg.msgId().equals("updateResource")) {
            CommUtils.outgreen(name + " | updateResource, content: " + msg.msgContent());

            return;
        }

        CommUtils.outyellow(name + " | elabMsg " + msg + " " + Thread.currentThread().getName());
    }

    public void updateResource(String s) {
        final String TARGET_REGEX = "^obs.*";

        this.ctx.getLocalActorNames().forEach(actor -> {
            System.out.println(actor);

            // don't send to self
            if (actor.equals(this.name)) {
                return;
            }

            if (actor.matches(TARGET_REGEX)) {
                IApplMessage msg = CommUtils.buildDispatch(this.name, "updateResource", s,
                        actor);
                forward(msg);
            }
        });
    }
}
