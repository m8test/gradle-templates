# encoding: utf-8
# 获取事件发布器，用于发布事件
publisher = $events.getPublisher()
java_import "com.m8test.script.core.api.event.LocalSubscription"
java_import "com.m8test.script.core.api.event.Event"
# 发送本地事件，只有同一个脚本引擎启动的脚本并且本地订阅的频道和id都相同时才能收到
publisher.publishLocally(lambda {
  # @type it [LocalSubscription]
  |it|
  # 设置本地订阅的频道
  it.setChannel("subscription-channel")
  # 设置本地订阅的id
  it.setId("subscription-id")
}) {
  # @type it [Event]
  |it|
  # 需要发送的数据，这里发送简单的字符串，如果需要复杂结构，推荐使用json格式的字符串
  it.setData("本地事件")
}
java_import "com.m8test.script.core.api.event.GlobalSubscription"
# 发送全局事件，只有全局订阅的频道和id都相同时才能收到
publisher.publishGlobally(lambda {
  # @type it [GlobalSubscription]
  |it|
  # 设置全局订阅的频道
  it.setChannel("subscription-channel")
  # 设置全局订阅的id
  it.setId("subscription-id")
}) {
  # @type it [Event]
  |it|
  # 需要发送的数据，这里发送简单的字符串，如果需要复杂结构，推荐使用json格式的字符串
  it.setData("全局事件")
}