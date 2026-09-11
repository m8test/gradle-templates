<?php
// 通过 use 导入java类, 通过"m8test_java\全类名"的方式
use m8test_java\java\lang\StringBuilder;
use m8test_java\android\view\Gravity;
use m8test_java\java\lang\System;

/** @var com.m8test.script.core.api.extension\Extensions $extensions */
/** @var com.m8test.script.core.api.file\Files $files */

$logger->info("access-jvm.php started");

$dynamicJava = $files->buildFile(function ($builder) {
    $builder->setRelativePath("", "res/com/example/script/DynamicJvmAccess.java");
});
$extensions->loadJavaFile($dynamicJava);
importJavaClass("com.example.script.DynamicJvmAccess", "DynamicJvmAccess");
// 通过 new 创建java对象
$sb = new StringBuilder("M8Test");
/** @var m8test_java\com\m8test\script\core\api\logger\Logger $logger */
// 调用java对象方法使用 ->
$sb->append("Php");
$logger->info("StringBuilder: " . $sb->toString());
// 调用java静态方法使用 ::
$logger->info("currentTimeMillis: " . System::currentTimeMillis());
// 调用java静态属性使用 :: 并且添加前缀 $_MJ_ , 编译后会变成 Gravity::CENTER
$logger->info("Gravity.CENTER: " . Gravity::$_MJ_CENTER);
// 如果 :: 不能调用静态属性, 那么需要通过反射调用
/** @var m8test_java\com\m8test\script\core\api\reflect\Reflectors $reflectors */
$v = $reflectors->byName("com.example.script.DynamicJvmAccess")->getStaticField(function ($f) {
    $f->setName("KIND");
});
$logger->info("dynamic.KIND: " . $v);
$logger->info("dynamic.add: " . DynamicJvmAccess::add(2, 3));
$logger->info("dynamic.byte: " . DynamicJvmAccess::echoByte(8));
$logger->info("dynamic.short: " . DynamicJvmAccess::echoShort(16));
$logger->info("dynamic.int: " . DynamicJvmAccess::echoInt(32));
$logger->info("dynamic.long: " . DynamicJvmAccess::echoLong(64));
$logger->info("dynamic.float: " . DynamicJvmAccess::echoFloat(1.25));
$logger->info("dynamic.double: " . DynamicJvmAccess::echoDouble(2.5));
$logger->info("dynamic.char: " . DynamicJvmAccess::echoChar("Z"));
$logger->info("dynamic.boolean: " . DynamicJvmAccess::echoBoolean(true));
$logger->info("dynamic.byteBoxed: " . DynamicJvmAccess::echoByteBoxed(9));
$logger->info("dynamic.intBoxed: " . DynamicJvmAccess::echoIntBoxed(33));
$logger->info("dynamic.booleanBoxed: " . DynamicJvmAccess::echoBooleanBoxed(false));
$logger->info("dynamic.negate: " . DynamicJvmAccess::negate(true));
$logger->info("dynamic.scale: " . DynamicJvmAccess::scale(1.5, 2.0));
$logger->info("dynamic.join: " . DynamicJvmAccess::join("甲", "乙"));
$logger->info("dynamic.boxed: " . DynamicJvmAccess::boxed(7));
$logger->info("dynamic.identity: " . DynamicJvmAccess::identity("对象"));
$logger->info("dynamic.nullable: " . DynamicJvmAccess::nullable(null));
$logger->info("dynamic.date: " . DynamicJvmAccess::date());
$logger->info("dynamic.calendar: " . DynamicJvmAccess::calendar());
$logger->info("dynamic.bigInteger: " . DynamicJvmAccess::bigInteger());
$logger->info("dynamic.bigDecimal: " . DynamicJvmAccess::bigDecimal());
$logger->info("dynamic.byteArrayLength: " . DynamicJvmAccess::byteArrayLength($arrays->byteArrayOf(1, 2, 3)));
$stringClass = $classes->getJavaClassByName("java.lang.String");
$logger->info("dynamic.className: " . DynamicJvmAccess::className($stringClass));
$logger->info("dynamic.objectArrayLength: " . DynamicJvmAccess::objectArrayLength(
    $arrays->arrayOf($stringClass, "a", "b")
));
$logger->info("dynamic.sumInts: " . DynamicJvmAccess::sumInts($arrays->intArrayOf(1, 2, 3)));
$logger->info("dynamic.sumLongs: " . DynamicJvmAccess::sumLongs($arrays->longArrayOf(4, 5)));
$logger->info("dynamic.listSize: " . DynamicJvmAccess::listSize($iterables->listOf("x", "y")));
$logger->info("dynamic.setSize: " . DynamicJvmAccess::setSize($iterables->setOf("x", "y")));
$logger->info("dynamic.mapValue: " . DynamicJvmAccess::mapValue($maps->mapOf($maps->pairOf("answer", 42)), "answer"));
$logger->info("dynamic.varargs: " . DynamicJvmAccess::varargs("left", "right"));
$logger->info("dynamic.intArrayRoundtrip: " . DynamicJvmAccess::sumInts(DynamicJvmAccess::ints(4, 5)));
$logger->info("dynamic.ints: " . DynamicJvmAccess::ints(4, 5));
$logger->info("dynamic.strings: " . DynamicJvmAccess::strings("a", "b"));
$logger->info("dynamic.list: " . DynamicJvmAccess::list("x", "y"));
$logger->info("dynamic.map: " . DynamicJvmAccess::map("answer", 42));
$logger->info("dynamic.enum: " . DynamicJvmAccess::enumValue());
$logger->info("dynamic.sam: " . DynamicJvmAccess::callSam(function ($value) {
    return $value * 2;
}, 6));
