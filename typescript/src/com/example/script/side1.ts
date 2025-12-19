// 获取事件订阅器，用于订阅事件
let subscriber = $events.getSubscriber();

// 通过订阅器订阅本地事件
subscriber.subscribeLocally((it) => {
    // 设置本地订阅的频道
    it.setChannel("subscription-channel");
    // 设置本地订阅的id
    it.setId("subscription-id");
}, (it) => {
    // 当收到同一个引擎中的其他脚本发送的事件时，会执行下面的逻辑
    $console.log("收到事件", it.getData(), it.getTime(), it.getChannel());
});

// 通过订阅器订阅全局事件
subscriber.subscribeGlobally((it) => {
    // 设置全局订阅的频道
    it.setChannel("subscription-channel");
    // 设置全局订阅的id
    it.setId("subscription-id");
}, (it) => {
    // 当收到其他脚本发送的事件时，会执行下面的逻辑
    $console.log("收到事件", it.getData(), it.getTime(), it.getChannel());
});

// 获取脚本主线程
let mainThread = $script.getThreads().getMain();
// 获取定时器
let timer = mainThread.getTimer();
// 添加一个延时任务，为了防止脚本过早结束
timer.setTimeout((it) => {
    // 保持脚本3秒钟不结束
}, 3000);