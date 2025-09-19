from m8test_java.com.m8test.script.GlobalVariables import _console
from m8test_java.com.m8test.script.GlobalVariables import _events
from m8test_java.com.m8test.script.GlobalVariables import _script

# 获取事件订阅器，用于订阅事件
subscriber = _events.getSubscriber()
# 通过订阅器订阅本地事件，本地事件指的是由和当前脚本具有同一个脚本引擎的脚本发送的事件
subscriber.subscribeLocally(lambda it:
                            # 设置本地订阅的频道
                            (it.setChannel("subscription-channel"),
                             # 设置本地订阅的id
                             it.setId("subscription-id")),
                            lambda it:
                            # 当收到同一个引擎中的其他脚本发送的事件时，会执行下面的逻辑
                            _console.log("收到事件", it.getData(), it.getTime(), it.getChannel())
                            )
# 通过订阅器订阅全局事件，全局事件指的是所有其他脚本发送的事件
subscriber.subscribeGlobally(lambda it:
                             # 设置全局订阅的频道
                             (it.setChannel("subscription-channel"),
                              # 设置全局订阅的id
                              it.setId("subscription-id")
                              ), lambda it:
                             # 当收到其他脚本发送的事件时，会执行下面的逻辑
                             _console.log("收到事件", it.getData(), it.getTime(), it.getChannel())
                             )
# 获取脚本主线程
mainThread = _script.getThreads().getMain()
# 获取定时器
timer = mainThread.getTimer()
# 添加一个延时任务，为了防止脚本过早结束
timer.setTimeout(lambda it: it
                 # 保持脚本3秒钟不结束
                 , 3000)