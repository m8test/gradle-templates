// 获取事件订阅器，用于订阅事件
let subscriber = $events.getSubscriber();

$logger.info("SIDE1_STARTED");
subscriber.subscribe((scopes) => scopes.getScript(), "subscription-channel", (event) => {
    $logger.info("SIDE1_LOCAL_EVENT", event.getPayload().getStringOrNull("data"), event.getTimeMillis(), event.getChannel());
});
subscriber.subscribe((scopes) => scopes.getApp(), "subscription-channel", (event) => {
    $logger.info("SIDE1_GLOBAL_EVENT", event.getPayload().getStringOrNull("data"), event.getTimeMillis(), event.getChannel());
});
$logger.info("SIDE1_SUBSCRIBED");

// 获取脚本主线程
let mainThread = $script.getThreads().getMain();
// 获取定时器
let timer = mainThread.getTimer();
// 添加一个延时任务，为了防止脚本过早结束
timer.setTimeout((it) => {
    // 保持脚本3秒钟不结束
}, 3000);
