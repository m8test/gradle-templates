package com.example.script.side1;

import com.m8test.script.core.api.event.Event;
import com.m8test.script.core.api.event.GlobalSubscription;
import com.m8test.script.core.api.event.LocalSubscription;
import com.m8test.script.core.api.event.Subscriber;
import com.m8test.script.core.api.thread.NewScriptThread;
import com.m8test.script.core.api.thread.Timer;
import kotlin.jvm.functions.Function1;

import static com.m8test.script.GlobalVariables.*;

class test {
    public static void run() {
        // 获取事件订阅器，用于订阅事件
        Subscriber subscriber = $events.getSubscriber();
        // 通过订阅器订阅本地事件，本地事件指的是由和当前脚本具有同一个脚本引擎的脚本发送的事件
        subscriber.subscribeLocally(new Function1() {
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
                // 当收到同一个引擎中的其他脚本发送的事件时，会执行下面的逻辑
                $console.log("收到事件", it.getData(), it.getTime(), it.getChannel());
                return null;
            }
        });
        // 通过订阅器订阅全局事件，全局事件指的是所有其他脚本发送的事件
        subscriber.subscribeGlobally(new Function1() {
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
                // 当收到其他脚本发送的事件时，会执行下面的逻辑
                $console.log("收到事件", it.getData(), it.getTime(), it.getChannel());
                return null;
            }
        });
        // 获取脚本主线程
        NewScriptThread mainThread = $script.getThreads().getMain();
        // 获取定时器
        Timer timer = mainThread.getTimer();
        // 添加一个延时任务，为了防止脚本过早结束
        timer.setTimeout(new Function1() {
            @Override
            public Object invoke(Object objects) {
                // 保持脚本3秒钟不结束
                return null;
            }
        }, 3000);
    }
}
// 下面的语句中的 '//-m8test-remove' 在实际编译时会被删除，也就是将会改成 'test.run();', 但是不能省略，否则 run 方法不会被执行
//-m8test-remove test.run();
