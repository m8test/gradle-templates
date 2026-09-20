$webView.setUrl("data:text/html;base64,PCFkb2N0eXBlIGh0bWw+PGh0bWw+PGJvZHk+PGJ1dHRvbiBpZD0iY291bnRlciIgb25jbGljaz0idGhpcy5pbm5lclRleHQgPSBTdHJpbmcoTnVtYmVyKHRoaXMuaW5uZXJUZXh0KSArIDEpIj4xPC9idXR0b24+PC9ib2R5PjwvaHRtbD4=");
var page = $webView.getBridge().getPage();
var scope = $coroutines.newScope(null);
$activity.start();
scope.delay(1200).then((startScope, ignored) ->
    page.evaluate(startScope, "document.getElementById('counter').click()").then((clickScope, ignoredClick) ->
        page.locator(selectors -> selectors.getByText("2")).getText(clickScope).then((doneScope, text) -> {
            if (!"2".equals(text)) throw new RuntimeException("counter assertion failed: " + text);
            $logger.info("java-template-webview-e2e:passed");
            $activity.finish();
            $activity.stop();
            return doneScope.resolve(text);
        })
    )
);
