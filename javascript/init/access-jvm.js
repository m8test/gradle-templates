// 可以通过 Pacakges.全类名 的方式访问java类
let sb = new Packages.java.lang.StringBuilder("M8Test")
// 内部类同样也可以通过 Pacakge.全类名 方式访问, 但是需要将 $ 改成 ., 如 android.widget.FrameLayout$LayoutParams
let LP = Packages.android.widget.FrameLayout.LayoutParams
$logger.info("LayoutParams class", LP)
// 调用java对象方法使用 .
sb.append("Javascript")
$logger.info(sb.toString());
// 调用 Java 对象属性可以使用 .
let list = new Packages.java.util.ArrayList()
list.add("Javascript")
$logger.info("ArrayList size", list.size(), list.get(0))
// 调用java静态方法使用 .
$logger.info(Packages.java.lang.System.currentTimeMillis())
// Java 8 的函数式接口可以直接接收 JavaScript 函数。
let runnable = new Packages.java.lang.Runnable(function () {
    $logger.info("Runnable invoked")
})
runnable.run()
