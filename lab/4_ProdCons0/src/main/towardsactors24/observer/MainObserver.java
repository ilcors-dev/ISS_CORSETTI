package main.towardsactors24.observer;

import unibo.basicomm23.utils.CommUtils;
import unibo.naiveactors24.ActorContext24;

/*
 * Test main for the WORKTODO of lecture 07/03/2024
 */
public class MainObserver {
    public static final int port = 8223;
    public static final String entry = "" + port;

    public void configureTheSystem() {
        ActorContext24 ctx1 = new ActorContext24("a", "localhost", port);

        new Logger("obslogger", ctx1);

        new Observer("obs1", ctx1);
        new Observer("obs2", ctx1);
        new Observer("a", ctx1).updateResource("test");

        new Consumer("consumer", ctx1);
        new Producer("producer", ctx1).sendUpdate("update");

        CommUtils.delay(1000);
        CommUtils.outblack("MainOneNodeWithActors24 TERMINATES thread=" + Thread.currentThread().getName());
        System.exit(0);
    }

    public static void main(String[] args) {
        new MainObserver().configureTheSystem();
    }
}
