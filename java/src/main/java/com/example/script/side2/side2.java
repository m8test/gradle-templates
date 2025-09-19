package com.example.script.side2;

import com.m8test.script.core.api.event.Event;
import com.m8test.script.core.api.event.GlobalSubscription;
import com.m8test.script.core.api.event.LocalSubscription;
import com.m8test.script.core.api.event.Publisher;
import kotlin.jvm.functions.Function1;

import static com.m8test.script.GlobalVariables.$events;

class test1 {
    public static void run() {
        // 获取事件发布器，用于发布事件
        Publisher publisher = $events.getPublisher();
    // 发送本地事件，只有同一个脚本引擎启动的脚本并且本地订阅的频道和id都相同时才能收到
        publisher.publishLocally(new Function1() {
            @Override
            public Object invoke(Object localSubscription) {
                LocalSubscription it = (LocalSubscription) localSubscription;
                // 设置本地订阅的频道
                it.setChannel("subscription-channel");
                // 设置本地订阅的id
                it.setId("subscription-id");
                return null;
            }
        }, new Function1() {
            @Override
            public Object invoke(Object event) {
                Event it = (Event) event;
                // 需要发送的数据，这里发送简单的字符串，如果需要复杂结构，推荐使用json格式的字符串
                it.setData("本地事件");
                return null;
            }
        });
        // 发送全局事件，只有全局订阅的频道和id都相同时才能收到
        publisher.publishGlobally(new Function1() {
            @Override
            public Object invoke(Object globalSubscription) {
                GlobalSubscription it = (GlobalSubscription) globalSubscription;
                // 设置全局订阅的频道
                it.setChannel("subscription-channel");
                // 设置全局订阅的id
                it.setId("subscription-id");
                return null;
            }
        }, new Function1() {
            @Override
            public Object invoke(Object event) {
                Event it = (Event) event;
                // 需要发送的数据，这里发送简单的字符串，如果需要复杂结构，推荐使用json格式的字符串
                it.setData("全局事件");
                return null;
            }
        });
    }
}
// 下面的语句中的 '//-m8test-remove' 在实际编译时会被删除，也就是将会改成 'test1.run();', 但是不能省略，否则 run 方法不会被执行
//-m8test-remove test1.run();
