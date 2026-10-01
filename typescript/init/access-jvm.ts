// 通过 Packages.全类名 的方式访问 Java 类
const sb = new Packages.java.lang.StringBuilder("M8Test");

// 内部类使用点号访问，例如 android.widget.FrameLayout.LayoutParams
const layoutParams = Packages.android.widget.FrameLayout.LayoutParams;
$logger.info("LayoutParams class", layoutParams);

// 调用 Java 对象方法
sb.append("TypeScript");
$logger.info(sb.toString());

// 调用 Java 集合 API
const list = new Packages.java.util.ArrayList();
list.add("TypeScript");
$logger.info("ArrayList size", list.size(), list.get(0));

// 调用 Java 静态方法
$logger.info(Packages.java.lang.System.currentTimeMillis());

// Java SAM 接口可以由 JavaScript/TypeScript 函数实现
const runnable = new Packages.java.lang.Runnable(function () {
    $logger.info("Runnable invoked");
});
runnable.run();
