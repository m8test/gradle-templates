package com.example.script.side1;

import static com.m8test.script.GlobalVariables.*;

class test {
    public static void run() {
        var subscriber = $events.getSubscriber();
        subscriber.subscribe(scopes -> scopes.getScript(), "subscription-channel", event -> {
            $logger.info("收到本地事件 " + event.getPayload().getStringOrNull("data") + " "
                + event.getTimeMillis() + " " + event.getChannel());
        });
        subscriber.subscribe(scopes -> scopes.getApp(), "subscription-channel", event -> {
            $logger.info("收到全局事件 " + event.getPayload().getStringOrNull("data") + " "
                + event.getTimeMillis() + " " + event.getChannel());
        });
    }
}
//-m8test-remove test.run();
