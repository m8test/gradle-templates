package com.example.script

def publisher = $events.getPublisher()
publisher.publish({ scopes -> scopes.getScript() }, "subscription-channel") { payload ->
    payload.putString("data", "本地事件")
}
publisher.publish({ scopes -> scopes.getApp() }, "subscription-channel") { payload ->
    payload.putString("data", "全局事件")
}
