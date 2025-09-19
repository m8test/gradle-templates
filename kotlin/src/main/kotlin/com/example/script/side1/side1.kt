package com.example.script.side1

import com.m8test.script.GlobalVariables.*

fun side1Run() {
    // 获取事件订阅器，用于订阅事件
    val subscriber = _events.getSubscriber()
    // 通过订阅器订阅本地事件，本地事件指的是由和当前脚本具有同一个脚本引擎的脚本发送的事件
    subscriber.subscribeLocally({
        // 设置本地订阅的频道
        setChannel("subscription-channel")
        // 设置本地订阅的id
        setId("subscription-id")
    }) {
        // 当收到同一个引擎中的其他脚本发送的事件时，会执行下面的逻辑
        _console.log("收到事件", it.getData(), it.getTime(), it.getChannel())
    }
    // 通过订阅器订阅全局事件，全局事件指的是所有其他脚本发送的事件
    subscriber.subscribeGlobally({
        // 设置全局订阅的频道
        setChannel("subscription-channel")
        // 设置全局订阅的id
        setId("subscription-id")
    }) {
        // 当收到其他脚本发送的事件时，会执行下面的逻辑
        _console.log("收到事件", it.getData(), it.getTime(), it.getChannel())
    }
    // 获取脚本主线程
    val mainThread = _script.getThreads().getMain()
    // 获取定时器
    val timer = mainThread.getTimer()
    // 添加一个延时任务，为了防止脚本过早结束
    timer.setTimeout({
        // 保持脚本3秒钟不结束
    }, 3000)
}
// 下面的语句中的 '//-m8test-remove' 在实际编译时会被删除，也就是将会改成 'side1Run();', 但是不能省略，否则 side1Run 方法不会被执行
//-m8test-remove side1Run();
