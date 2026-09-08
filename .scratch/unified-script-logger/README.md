# PHP 分支迁移范围

本分支已完成 PHP/Quercus Extension 适配当前 `m8test-android`，因此本轮同步迁移统一 Logger。

- `init/access-jvm.php`、主脚本和 side 脚本使用 `$logger`，删除旧 `$console` 和旧 Console 类型。
- Quercus JVM 访问继续覆盖动态 Java 源文件、基本类型、装箱类型、字符串、空值、数组、集合、对象返回值和函数式接口。
- 动态测试 Java 源码放在 `res`，保持 Java 7/8 语法，并通过 `$extensions.loadJavaFile` 加载。
- 设备验收执行 `runPhp`，确认 BuildProjectSource、BuildScript、Quercus ProjectScript、side 脚本和日志输出。

Ruby、Python、TypeScript、Kotlin 等尚未完成当前 Runtime 适配的语言不在本分支迁移范围内。
