# encoding: utf-8
$logger.info("RUBY_SIDE2_STARTED")
$script.getThreads().getMain().getTimer().setTimeout(lambda { |task|
  $events.getPublisher().publish(lambda { |scopes| scopes.getScript }, "subscription-channel") { |payload|
    payload.putString("data", "RUBY_SIDE2_EVENT")
  }
  $logger.info("RUBY_SIDE2_PUBLISHED")
  nil
}, 500)
