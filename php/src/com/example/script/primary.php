<?php
// 必须使用相对于src目录的路径
require_once 'com/example/tool/ToolB.php';

// 使用 ToolB.php 中的 ToolB 类
use com\example\tool\ToolB;

// 声明全局变量, 如果不声明的话也可以但是没有代码提示
global $console;
global $accessibility;
global $files;

ToolB::methodB($console);
$selector = $accessibility->createSelector();