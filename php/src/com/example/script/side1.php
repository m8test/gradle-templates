<?php
/** @var m8test_java\com\m8test\script\core\api\console\Console $console */
/** @var m8test_java\com\m8test\script\core\api\event\Events $events */
$console->log("PHP side1 started");
// 获取事件订阅器，用于订阅事件
$subscriber = $events->getSubscriber();
// 同一个 Engine 中的脚本使用 engine scope 互通事件。
$subscriber->subscribe(function ($scopes) {
    return $scopes->getEngine();
}, "subscription-channel", function ($event) use ($console) {
    $console->log("PHP side1 received event");
});
/** @var m8test_java\com\m8test\script\core\api\engine\Script $script */
// 获取脚本主线程
$mainThread = $script->getThreads()->getMain();
// 获取定时器
$timer = $mainThread->getTimer();
// 添加一个延时任务，为了防止脚本过早结束
$timer->setTimeout(function ($it) {
    // 保持脚本3秒钟不结束
}, 3000);
