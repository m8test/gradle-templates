(function () {
    function init() {
        // $webViewBridge.registerHandler() 用于设置供脚本端调用的处理器，第一个参数是处理器名，第二个参数是一个函数，会在处理器被调用时执行
        $webViewBridge.registerHandler("functionForScriptToCall", function (data, responseCallback) {
            // data 为脚本端调用处理器时传递过来的参数
            // responseCallback 用于返回处理结果到脚本端
            if (responseCallback) {
                var responseData = "I am from Javascript " + data
                responseCallback(responseData)
            }
        })
    }

    if (window.$webViewBridge) {
        init();
    } else {
        document.addEventListener(
            'WebViewBridgeReady',
            function () {
                //do your work here
                init();
            },
            false
        );
    }
})()

document.getElementById("button").addEventListener("click", function (e) {
    // $webViewBridge.callHandler() 用于调用脚本端注册的处理器，参数一为处理器名，参数二为需要传递给处理器的参数，参数三为回调函数用于接收脚本端的返回值
    $webViewBridge.callHandler("handlerForWebView", "params from js", function (p) {
        alert(p)
    })
})