$composeView.create((slot) => {
    slot.Column((column) => {
        column.setModifier((modifier) => modifier.fillMaxSize(1.0));
        column.setHorizontalAlignment((alignments) => alignments.getCenterHorizontally());
        column.setVerticalArrangement((arrangements) => arrangements.getCenter());
        column.setContent((content) => {
            content.Text((text) => {
                text.setText("Test Control UI: JavaScript");
            });
        });
    });
});

$activity.start();
$logger.info("test-control-javascript-compose-ui:started");

const timer = $script.getThreads().getMain().getTimer();
timer.setTimeout(function () {
    $activity.finish();
    $activity.stop();
}, 1500);
