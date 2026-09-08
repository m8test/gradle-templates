<?php
/** @var m8test_java\com\m8test\script\core\api\console\Console $console */
/** @var m8test_java\com\m8test\script\core\api\event\Events $events */
$console->log("PHP side2 started");
// 获取事件发布器，用于发布事件
$publisher = $events->getPublisher();
// 同一个 Engine 中的脚本使用 engine scope 互通事件。
$publisher->publish(function ($scopes) {
    return $scopes->getEngine();
}, "subscription-channel", function ($payload) {
    $payload->putString("message", "来自 PHP side2");
});
