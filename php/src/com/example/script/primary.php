<?php

// 必须使用相对于src目录的路径
require_once 'com/example/tool/ToolB.php';

// 使用 ToolB.php 中的 ToolB 类
use com\example\tool\ToolB;

/** 通过use使用java中的类, 不能使用 use xxx as xxx 只能使use,因为下面这句话会被替换为 import android.widget.Button, 这样写的目的是有代码提示 **/

// 声明全局变量, 如果不声明的话也可以但是没有代码提示
global $console;
ToolB::methodB($console);

/** @var m8test_java\com\m8test\script\core\api\ui\compose\ComposeView $composeView */
$composeView->create(function ($slot) {
    // 创建一个垂直布局
    $slot->Column(function ($column) {
        // 设置布局的水平对齐方式为水平居中
        $column->setHorizontalAlignment(function ($alignments) {
            return $alignments->getCenterHorizontally();
        });
        // 设置布局的垂直排列方式为居中
        $column->setVerticalArrangement(function ($arrangements) {
            return $arrangements->getCenter();
        });
        // 设置布局填满父容器
        $column->setModifier(function ($modifier) {
            $modifier->fillMaxSize(1.0);
        });
        // 设置垂直布局的内容
        $column->setContent(function ($columnSlot) {
            // 1. 创建状态
            $state = $columnSlot->mutableStateOf(0);
            $columnSlot->Text(function ($text) use ($state) {
                // 2. 跟踪状态变化， 当状态变化时， 自动更新文本内容
                $text->trackSingleState($state);
                // 3. 在文本的内容中显示状态的值
                $text->setText(javaString("点击次数:") . $state->getValue());
            });
            $columnSlot->TextButton(function ($button) use ($state) {
                $button->setOnClick(function () use ($state) {
                    // 4. 点击按钮时， 更新状态的值
                    $state->setValue($state->getValue() + 1);
                });
                $button->setContent(function ($buttonSlot) {
                    $buttonSlot->Text(function ($text) {
                        $text->setText(javaString("点击"));
                    });
                });
            });
        });
    });
});

// 启动界面
/** @var m8test_java\com\m8test\script\core\api\ui\Activity $activity */
$activity->start();
