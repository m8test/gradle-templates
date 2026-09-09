// 延迟发布，确保 side1 已完成订阅；这是测试夹具的确定性时序。
$logger.info("SIDE2_STARTED")
let timer = $script.getThreads().getMain().getTimer()
timer.setTimeout(function (it) {
    let publisher = $events.getPublisher()
    // 通过 scope 和 channel 发布事件；payload 使用统一 JSON contract。
    publisher.publish(scopes => scopes.getScript(), "subscription-channel", payload => {
        payload.putString("data", "本地事件")
    })
    publisher.publish(scopes => scopes.getApp(), "subscription-channel", payload => {
        payload.putString("data", "全局事件")
    })
    $logger.info("SIDE2_PUBLISHED")
}, 500)
