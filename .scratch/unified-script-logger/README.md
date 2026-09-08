# Java 分支迁移范围

Java 使用当前项目内置的 Janino Runtime。统一 Logger 的核心 contract 已在
`m8test-android` 完成，本分支只检查生成的 BuildScript、主脚本和 side 脚本是否仍引用旧
`$console`、`Console` 或 `.log(...)`，并执行 `runJava` 设备验收。

Java 分支不重复设计新的 JVM 访问 API；JVM 参数、返回值和函数式接口边界由 Janino 的
内置测试负责。
