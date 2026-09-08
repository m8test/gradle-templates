# Groovy 分支迁移范围

本分支已完成 Groovy Extension 适配当前 `m8test-android`，因此本轮同步迁移统一 Logger。

- Groovy Extension 安装、缓存更新和项目运行使用当前 Extension contract。
- 主脚本、side 脚本和工具类使用 `$logger` 与六级日志，删除旧 `$console`、`Console` 和 `.log(...)`。
- JVM 访问验证覆盖标准类、参数/返回值、集合、数组和函数式接口；动态 Java 测试类保持 Java 7/8 语法。
- 设备验收确认 Extension 安装、BuildProjectSource、BuildScript、Groovy ProjectScript、side 脚本和日志输出。

Ruby、Python、TypeScript、Kotlin 等尚未完成当前 Runtime 适配的语言不在本分支迁移范围内。
