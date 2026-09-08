// 获取事件订阅器，用于订阅事件
let subscriber = $events.getSubscriber();
// 当前事件 API 通过 scope 和 channel 订阅，不再通过可变配置对象设置频道。
subscriber.subscribe(scopes => scopes.getScript(), "subscription-channel", function (event) {
    $console.log("收到本地事件", event.getPayload().getStringOrNull("data"),
        event.getTimeMillis(), event.getChannel())
})
subscriber.subscribe(scopes => scopes.getApp(), "subscription-channel", function (event) {
    $console.log("收到全局事件", event.getPayload().getStringOrNull("data"),
        event.getTimeMillis(), event.getChannel())
})
// 获取脚本主线程
let mainThread = $script.getThreads().getMain()
// 获取定时器
let timer = mainThread.getTimer()
// 添加一个延时任务，为了防止脚本过早结束
timer.setTimeout(function (it) {
    // 保持脚本3秒钟不结束
}, 3000)
