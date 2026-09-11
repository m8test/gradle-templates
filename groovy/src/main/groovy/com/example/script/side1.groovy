package com.example.script

$logger.info("SIDE1_STARTED")
def subscriber = $events.getSubscriber()
subscriber.subscribe({ scopes -> scopes.getScript() }, "subscription-channel") { event ->
    $logger.info("SIDE1_LOCAL_EVENT " + event.getPayload().getStringOrNull("data") + " "
        + event.getTimeMillis() + " " + event.getChannel())
}
subscriber.subscribe({ scopes -> scopes.getApp() }, "subscription-channel") { event ->
    $logger.info("SIDE1_GLOBAL_EVENT " + event.getPayload().getStringOrNull("data") + " "
        + event.getTimeMillis() + " " + event.getChannel())
}
$logger.info("SIDE1_SUBSCRIBED")
$events.getPublisher().publish({ scopes -> scopes.getScript() }, "subscription-channel") { payload ->
    payload.putString("data", "SIDE1_LOCAL_EVENT_PAYLOAD")
}
Thread.sleep(1500L)
