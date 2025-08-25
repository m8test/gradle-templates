<?php
// 通过 use 导入java类, 通过"m8test_java\全类名"的方式
use m8test_java\java\lang\StringBuilder;
use m8test_java\com\m8test\script\core\impl\JavaTypeTester;
use m8test_java\android\view\Gravity;
use m8test_java\java\lang\System;
// 通过 new 创建java对象
$sb = new StringBuilder("M8Test");
/** @var m8test_java\com\m8test\script\core\api\console\Console $console */
// 调用java对象方法使用 ->
$sb->append("Php");
$console->log($sb);
// 调用java对象属性使用 ->
$test = new JavaTypeTester();
$console->log($test->OBJECT_FIELD);
// 调用java静态方法使用 ::
$console->log(System::currentTimeMillis());
// 调用java静态属性使用 :: 并且添加前缀 $_MJ_ , 编译后会变成 Gravity::CENTER
$console->log(Gravity::$_MJ_CENTER);
// 如果 :: 不能调用静态属性, 那么需要通过反射调用
/** @var m8test_java\com\m8test\script\core\api\reflect\Reflectors $reflectors */
$v = $reflectors->reflect("com.m8test.script.core.impl.JavaTypeTester")->getField(null, function ($f) {
    $f->setName("STATIC_FIELD");
});
$console->log($v);
class MultiAbstractMethodInterfaceImpl
{
    var $console;
    var $i;

    function __construct($p)
    {
        $this->console = $p;
    }

    function setInt($i)
    {
        $this->i = $i;
        $this->console->log("setInt", $i);
    }

    function getInt()
    {
        return $this->i;
    }
}
JavaTypeTester::setMultiAbstractMethodInterface(new MultiAbstractMethodInterfaceImpl($console));
$mami = JavaTypeTester::getMultiAbstractMethodInterface();
$mami->setInt(12345);
$console->log($mami->getInt());
// 方法一: 通过闭包(推荐)
JavaTypeTester::setSingleAbstractMethodInterface(function () use ($console) {
    $console->log("closure");
    return 1234;
});
$sami = JavaTypeTester::getSingleAbstractMethodInterface();
$console->log($sami->getInt());

// 方法二: 通过对象
class SingleAbstractMethodInterfaceImpl
{
    var $console;

    function __construct($p)
    {
        $this->console = $p;
    }

    function getInt()
    {
        $this->console->log("getInt");
        return 0;
    }
}

JavaTypeTester::setSingleAbstractMethodInterface(new SingleAbstractMethodInterfaceImpl($console));
$sami = JavaTypeTester::getSingleAbstractMethodInterface();
$console->log($sami->getInt());