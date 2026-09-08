// 获取事件发布器，用于发布事件
let publisher = $events.getPublisher()
// 通过 scope 和 channel 发布事件；payload 使用统一 JSON contract。
publisher.publish(scopes => scopes.getScript(), "subscription-channel", payload => {
    payload.putString("data", "本地事件")
})
publisher.publish(scopes => scopes.getApp(), "subscription-channel", payload => {
    payload.putString("data", "全局事件")
})
