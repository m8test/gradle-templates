$logger.info("SIDE2_STARTED");
const timer = $script.getThreads().getMain().getTimer();
timer.setTimeout(() => {
    const publisher = $events.getPublisher();
    publisher.publish((scopes) => scopes.getScript(), "subscription-channel", (payload) => {
        payload.putString("data", "本地事件");
    });
    publisher.publish((scopes) => scopes.getApp(), "subscription-channel", (payload) => {
        payload.putString("data", "全局事件");
    });
    $logger.info("SIDE2_PUBLISHED");
}, 500);
