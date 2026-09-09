package com.example.script.side1;

import static com.m8test.script.GlobalVariables.*;

class side1 {
    public static void run() {
        $logger.info("SIDE1_STARTED");
        var subscriber = $events.getSubscriber();
        subscriber.subscribe(scopes -> scopes.getScript(), "subscription-channel", event -> {
            $logger.info("SIDE1_LOCAL_EVENT " + event.getPayload().getStringOrNull("data") + " "
                + event.getTimeMillis() + " " + event.getChannel());
        });
        subscriber.subscribe(scopes -> scopes.getApp(), "subscription-channel", event -> {
            $logger.info("SIDE1_GLOBAL_EVENT " + event.getPayload().getStringOrNull("data") + " "
                + event.getTimeMillis() + " " + event.getChannel());
        });
        $logger.info("SIDE1_SUBSCRIBED");
        try {
            java.lang.Thread.sleep(1500L);
        } catch (java.lang.InterruptedException ignored) {
            // The script is being stopped.
        }
    }
}
//-m8test-remove side1.run();
