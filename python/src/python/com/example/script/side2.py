from m8test_java.com.m8test.script.GlobalVariables import _events

# 获取事件发布器，用于发布事件
publisher = _events.getPublisher()
# 发送本地事件，只有同一个脚本引擎启动的脚本并且本地订阅的频道和id都相同时才能收到
publisher.publishLocally(lambda it:
                         # 设置本地订阅的频道
                         (it.setChannel("subscription-channel"),
                          # 设置本地订阅的id
                          it.setId("subscription-id")
                          ), lambda it:
                         # 需要发送的数据，这里发送简单的字符串，如果需要复杂结构，推荐使用json格式的字符串
                         it.setData("本地事件")
                         )
# 发送全局事件，只有全局订阅的频道和id都相同时才能收到
publisher.publishGlobally(lambda it:
                          # 设置全局订阅的频道
                          (it.setChannel("subscription-channel"),
                           # 设置全局订阅的id
                           it.setId("subscription-id")
                           ), lambda it:
                          # 需要发送的数据，这里发送简单的字符串，如果需要复杂结构，推荐使用json格式的字符串
                          it.setData("全局事件")
                          )