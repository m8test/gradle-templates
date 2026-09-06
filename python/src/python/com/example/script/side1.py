from m8test_java.com.m8test.script.GlobalVariables import _logger
from m8test_java.com.m8test.script.GlobalVariables import _events
from m8test_java.com.m8test.script.GlobalVariables import _script

_logger.info("PYTHON_SIDE1_STARTED")

# 获取事件订阅器，用于订阅事件
subscriber = _events.getSubscriber()
# 当前事件 API 通过 scope selector + channel 标识订阅范围。
subscriber.subscribe(lambda scopes: scopes.getEngine(), "subscription-channel",
                      lambda event: _logger.info(
                          "收到事件 data=" + str(event.getPayload().getStringOrNull("data"))
                          + " time=" + str(event.getTimeMillis())
                          + " channel=" + event.getChannel()))
subscriber.subscribe(lambda scopes: scopes.getApp(), "subscription-channel",
                      lambda event: _logger.info(
                          "收到全局事件 data=" + str(event.getPayload().getStringOrNull("data"))
                          + " time=" + str(event.getTimeMillis())
                          + " channel=" + event.getChannel()))
# 获取脚本主线程
mainThread = _script.getThreads().getMain()
# 获取定时器
timer = mainThread.getTimer()
# 添加一个延时任务，为了防止脚本过早结束
timer.setTimeout(lambda it: it
                 # 保持脚本3秒钟不结束
                 , 3000)
