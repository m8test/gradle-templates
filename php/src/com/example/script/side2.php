<?php
/** @var m8test_java\com\m8test\script\core\api\logger\Logger $logger */
/** @var m8test_java\com\m8test\script\core\api\event\Events $events */
global $logger;
global $events;
$logger->info("PHP side2 started");
// 获取事件发布器，用于发布事件
$publisher = $events->getPublisher();
// 同一个 Engine 中的脚本使用 engine scope 互通事件。
global $script;
$timer = $script->getThreads()->getMain()->getTimer();
$timer->setTimeout(function ($it) use ($publisher) {
    $publisher->publish(function ($scopes) {
        return $scopes->getEngine();
    }, "subscription-channel", function ($payload) {
        $payload->putString("message", "来自 PHP side2");
    });
    global $logger;
    $logger->info("PHP side2 published event");
}, 500);
