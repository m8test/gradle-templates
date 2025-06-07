<?php

// 必须使用相对于src目录的路径
require_once 'com/example/tool/ToolB.php';

// 使用 ToolB.php 中的 ToolB 类
use com\example\tool\ToolB;
use m8test_java\android\view\Gravity;
use m8test_java\android\widget\Button;

/** 通过use使用java中的类, 不能使用 use xxx as xxx 只能使use,因为下面这句话会被替换为 import android.widget.Button, 这样写的目的是有代码提示 **/

// 声明全局变量, 如果不声明的话也可以但是没有代码提示
/** @var m8test_java\com\m8test\script\core\api\console\Console $console */
global $console;
ToolB::methodB($console);

/** @var m8test_java\com\m8test\script\core\api\ui\view\AndroidView $androidView */
global $androidView;
// 创建显示的界面内容
$androidView->create(false, function ($frameLayout) use ($console) {
    // 添加下面的注释执行 $frameLayout 的类型, 这样才有代码提示
    /** @var \m8test_java\android\widget\FrameLayout $frameLayout */
    $context = $frameLayout->getContext();
    /** @var \m8test_java\android\app\Activity $context */
    $button = new Button($context);
    $num = 0;
    $button->setText("Hello");
    $button->setOnClickListener(function ($view) use (&$num) {
        $num = $num + 1;
        /** @var \m8test_java\android\widget\Button $view */
        $view->setText("hello" . $num);
    });
    /** @var m8test_java\com\m8test\script\core\api\reflect\Reflectors $reflectors */
    global $reflectors;
    // 在ui线程添加视图, 不然会导致崩溃
    runOnUiThread(function ($frameLayout, $button, $reflectors, $console) {
        // 反射获取静态变量的值
        /** @var m8test_java\com\m8test\script\core\api\reflect\Reflectors $reflectors */
        $wrapContent = $reflectors->reflect("android.widget.FrameLayout\$LayoutParams")->getField(null, function ($fieldSelector) {
            /** @var \m8test_java\com\m8test\script\core\api\reflect\FieldSelector $fieldSelector */
            $fieldSelector->setName("WRAP_CONTENT");
        });
        /** @var m8test_java\com\m8test\script\core\api\console\Console $console */
        $console->log("WRAP_CONTENT", $wrapContent);
        // 访问java内部类需要使用 "m8test_java\全类名" 的方式, 内部类的 $ 需要替换为 _N_
        $layoutParams = new m8test_java\android\widget\FrameLayout_N_LayoutParams($wrapContent, $wrapContent);
        // 双冒号获取静态常量的值, 需要添加 $_MJ_ 实际上下面的代码会被编译成 $layoutParams->gravity = Gravity::CENTER;
        $layoutParams->gravity = Gravity::$_MJ_CENTER;
        /** @var \m8test_java\android\widget\FrameLayout $frameLayout */
        $frameLayout->addView($button, $layoutParams);
    }, $frameLayout, $button, $reflectors, $console);
});
/** @var m8test_java\com\m8test\script\core\api\ui\Activity $activity */
global $activity;
// 启动界面
$activity->start();
