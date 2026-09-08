# JavaScript 模板日志迁移

JavaScript 项目的入口脚本、side 脚本和 `init/access-jvm.js` 使用 `$logger` 及其
`trace/debug/info/warn/error` 方法。模板不再引用 `$console` 或已删除的
`com.m8test.script.core.api.console.Console`。

`settings.js` 与 `init.build.js` 由 `m8test-gradle-plugin` 生成，属于设备端
`BuildScript`，不依赖项目脚本 logger；生成文件可以为空或只包含构建配置。

验证边界是 `./gradlew -q :javascript:runJavascript`：它必须完成
`BuildProjectSource` 推送、设备端 BuildScript 和独立 JavaScript ProjectScript 启动。
