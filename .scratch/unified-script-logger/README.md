# Lua 分支迁移范围

本分支已完成 Lua Extension 适配当前 `m8test-android`，因此本轮同步迁移统一 Logger。

- 项目脚本、side 脚本和 init 脚本使用 `$logger` 与六级日志。
- `init/access-jvm.lua` 删除旧 `_console` 和 `JavaTypeTester`。
- JVM 访问验证覆盖标准类、基本类型、装箱类型、数组、集合、参数/返回值和函数式接口。
- 若使用动态 Java 测试类，源码放在 `res`，保持 Java 7/8 语法，并通过 `$extensions.loadJavaFile` 加载。
- 设备验收执行 `runLua`，确认 BuildProjectSource、BuildScript、主脚本、side 脚本和日志输出。

Ruby、Python、TypeScript、Kotlin 等尚未完成当前 Runtime 适配的语言不在本分支迁移范围内。
