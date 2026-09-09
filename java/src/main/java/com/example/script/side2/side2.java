package com.example.script.side2;

import static com.m8test.script.GlobalVariables.*;

class side2 {
    public static void run() {
        $logger.info("SIDE2_STARTED");
        var publisher = $events.getPublisher();
        publisher.publish(scopes -> scopes.getScript(), "subscription-channel", payload -> {
            payload.putString("data", "本地事件");
        });
        publisher.publish(scopes -> scopes.getApp(), "subscription-channel", payload -> {
            payload.putString("data", "全局事件");
        });
        $logger.info("SIDE2_PUBLISHED");
    }
}
//-m8test-remove side2.run();
