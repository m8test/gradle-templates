from m8test_java.com.m8test.script.GlobalVariables import _events
from m8test_java.com.m8test.script.GlobalVariables import _logger
from m8test_java.com.m8test.script.GlobalVariables import _script

_logger.info("PYTHON_SIDE2_STARTED")

# 获取事件发布器，用于发布事件
publisher = _events.getPublisher()
# 发送本地事件，只有同一个脚本引擎启动的脚本并且本地订阅的频道和id都相同时才能收到
def publish_events(_):
    publisher.publish(lambda scopes: scopes.getEngine(), "subscription-channel",
                      lambda payload: payload.putString("data", "本地事件"))
    publisher.publish(lambda scopes: scopes.getApp(), "subscription-channel",
                      lambda payload: payload.putString("data", "全局事件"))
    _logger.info("PYTHON_SIDE2_EVENTS_PUBLISHED")

_script.getThreads().getMain().getTimer().setTimeout(publish_events, 1000)
