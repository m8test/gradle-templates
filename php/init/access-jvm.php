<?php
// 通过 use 导入java类, 通过"m8test_java\全类名"的方式
use m8test_java\java\lang\StringBuilder;
use m8test_java\android\view\Gravity;
use m8test_java\java\lang\System;

/** @var com.m8test.script.core.api.extension\Extensions $extensions */
/** @var com.m8test.script.core.api.file\Files $files */

$console->log("access-jvm.php started");

$dynamicJava = $files->buildFile(function ($builder) {
    $builder->setRelativePath("", "res/com/example/script/DynamicJvmAccess.java");
});
$extensions->loadJavaFile($dynamicJava);
importJavaClass("com.example.script.DynamicJvmAccess", "DynamicJvmAccess");
// 通过 new 创建java对象
$sb = new StringBuilder("M8Test");
/** @var m8test_java\com\m8test\script\core\api\console\Console $console */
// 调用java对象方法使用 ->
$sb->append("Php");
$console->log("StringBuilder: " . $sb->toString());
// 调用java静态方法使用 ::
$console->log("currentTimeMillis: " . System::currentTimeMillis());
// 调用java静态属性使用 :: 并且添加前缀 $_MJ_ , 编译后会变成 Gravity::CENTER
$console->log("Gravity.CENTER: " . Gravity::$_MJ_CENTER);
// 如果 :: 不能调用静态属性, 那么需要通过反射调用
/** @var m8test_java\com\m8test\script\core\api\reflect\Reflectors $reflectors */
$v = $reflectors->byName("com.example.script.DynamicJvmAccess")->getStaticField(function ($f) {
    $f->setName("KIND");
});
$console->log("dynamic.KIND: " . $v);
$console->log("dynamic.add: " . DynamicJvmAccess::add(2, 3));
$console->log("dynamic.byte: " . DynamicJvmAccess::echoByte(8));
$console->log("dynamic.short: " . DynamicJvmAccess::echoShort(16));
$console->log("dynamic.int: " . DynamicJvmAccess::echoInt(32));
$console->log("dynamic.long: " . DynamicJvmAccess::echoLong(64));
$console->log("dynamic.float: " . DynamicJvmAccess::echoFloat(1.25));
$console->log("dynamic.double: " . DynamicJvmAccess::echoDouble(2.5));
$console->log("dynamic.char: " . DynamicJvmAccess::echoChar("Z"));
$console->log("dynamic.boolean: " . DynamicJvmAccess::echoBoolean(true));
$console->log("dynamic.byteBoxed: " . DynamicJvmAccess::echoByteBoxed(9));
$console->log("dynamic.intBoxed: " . DynamicJvmAccess::echoIntBoxed(33));
$console->log("dynamic.booleanBoxed: " . DynamicJvmAccess::echoBooleanBoxed(false));
$console->log("dynamic.negate: " . DynamicJvmAccess::negate(true));
$console->log("dynamic.scale: " . DynamicJvmAccess::scale(1.5, 2.0));
$console->log("dynamic.join: " . DynamicJvmAccess::join("甲", "乙"));
$console->log("dynamic.boxed: " . DynamicJvmAccess::boxed(7));
$console->log("dynamic.identity: " . DynamicJvmAccess::identity("对象"));
$console->log("dynamic.nullable: " . DynamicJvmAccess::nullable(null));
$console->log("dynamic.date: " . DynamicJvmAccess::date());
$console->log("dynamic.calendar: " . DynamicJvmAccess::calendar());
$console->log("dynamic.bigInteger: " . DynamicJvmAccess::bigInteger());
$console->log("dynamic.bigDecimal: " . DynamicJvmAccess::bigDecimal());
$console->log("dynamic.byteArrayLength: " . DynamicJvmAccess::byteArrayLength($arrays->byteArrayOf(1, 2, 3)));
$stringClass = $classes->getJavaClassByName("java.lang.String");
$console->log("dynamic.className: " . DynamicJvmAccess::className($stringClass));
$console->log("dynamic.objectArrayLength: " . DynamicJvmAccess::objectArrayLength(
    $arrays->arrayOf($stringClass, "a", "b")
));
$console->log("dynamic.sumInts: " . DynamicJvmAccess::sumInts($arrays->intArrayOf(1, 2, 3)));
$console->log("dynamic.sumLongs: " . DynamicJvmAccess::sumLongs($arrays->longArrayOf(4, 5)));
$console->log("dynamic.listSize: " . DynamicJvmAccess::listSize($iterables->listOf("x", "y")));
$console->log("dynamic.setSize: " . DynamicJvmAccess::setSize($iterables->setOf("x", "y")));
$console->log("dynamic.mapValue: " . DynamicJvmAccess::mapValue($maps->mapOf($maps->pairOf("answer", 42)), "answer"));
$console->log("dynamic.varargs: " . DynamicJvmAccess::varargs("left", "right"));
$console->log("dynamic.intArrayRoundtrip: " . DynamicJvmAccess::sumInts(DynamicJvmAccess::ints(4, 5)));
$console->log("dynamic.ints: " . DynamicJvmAccess::ints(4, 5));
$console->log("dynamic.strings: " . DynamicJvmAccess::strings("a", "b"));
$console->log("dynamic.list: " . DynamicJvmAccess::list("x", "y"));
$console->log("dynamic.map: " . DynamicJvmAccess::map("answer", 42));
$console->log("dynamic.enum: " . DynamicJvmAccess::enumValue());
$console->log("dynamic.sam: " . DynamicJvmAccess::callSam(function ($value) {
    return $value * 2;
}, 6));
