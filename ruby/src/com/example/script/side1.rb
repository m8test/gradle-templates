# encoding: utf-8
$logger.info("RUBY_SIDE1_STARTED")
subscriber = $events.getSubscriber()
subscriber.subscribe(lambda { |scopes| scopes.getScript }, "subscription-channel") { |event|
  $logger.info("RUBY_SIDE1_EVENT " + event.getPayload().getStringOrNull("data"))
}
$logger.info("RUBY_SIDE1_SUBSCRIBED")
$events.getPublisher().publish(lambda { |scopes| scopes.getScript }, "subscription-channel") { |payload|
  payload.putString("data", "RUBY_SIDE1_SELF_EVENT")
}
$script.getThreads().getMain().getTimer().setTimeout(lambda { |task| nil }, 1500)
