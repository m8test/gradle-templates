package com.example.script

$logger.info("SIDE1_STARTED")
def subscriber = $events.getSubscriber()
subscriber.subscribe({ scopes -> scopes.getScript() }, "subscription-channel") { event ->
    $logger.info("收到本地事件 " + event.getPayload().getStringOrNull("data") + " "
        + event.getTimeMillis() + " " + event.getChannel())
}
subscriber.subscribe({ scopes -> scopes.getApp() }, "subscription-channel") { event ->
    $logger.info("SIDE1_GLOBAL_EVENT " + event.getPayload().getStringOrNull("data") + " "
        + event.getTimeMillis() + " " + event.getChannel())
}
$logger.info("SIDE1_SUBSCRIBED")
Thread.sleep(1500L)
