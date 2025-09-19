package com.example.script.side2

import com.m8test.script.GlobalVariables._events

fun side2Run() {
    // 获取事件发布器，用于发布事件
    val publisher = _events.getPublisher()
    // 发送本地事件，只有同一个脚本引擎启动的脚本并且本地订阅的频道和id都相同时才能收到
    publisher.publishLocally({
        // 设置本地订阅的频道
        setChannel("subscription-channel")
        // 设置本地订阅的id
        setId("subscription-id")
    }) {
        // 需要发送的数据，这里发送简单的字符串，如果需要复杂结构，推荐使用json格式的字符串
        setData("本地事件")
    }
    // 发送全局事件，只有全局订阅的频道和id都相同时才能收到
    publisher.publishGlobally({
        // 设置全局订阅的频道
        setChannel("subscription-channel")
        // 设置全局订阅的id
        setId("subscription-id")
    }) {
        // 需要发送的数据，这里发送简单的字符串，如果需要复杂结构，推荐使用json格式的字符串
        setData("全局事件")
    }
}
// 下面的语句中的 '//-m8test-remove' 在实际编译时会被删除，也就是将会改成 'side2Run();', 但是不能省略，否则 side2Run 方法不会被执行
//-m8test-remove side2Run();
