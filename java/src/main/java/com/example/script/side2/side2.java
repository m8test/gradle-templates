package com.example.script.side2;

import static com.m8test.script.GlobalVariables.*;

class test1 {
    public static void run() {
        var publisher = $events.getPublisher();
        publisher.publish(scopes -> scopes.getScript(), "subscription-channel", payload -> {
            payload.putString("data", "本地事件");
        });
        publisher.publish(scopes -> scopes.getApp(), "subscription-channel", payload -> {
            payload.putString("data", "全局事件");
        });
    }
}
//-m8test-remove test1.run();
