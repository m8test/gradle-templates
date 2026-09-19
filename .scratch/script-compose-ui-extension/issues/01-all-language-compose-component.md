# 01 — 所有语言模板声明 Compose Extension

**What to build:** 所有语言模板都能通过统一 `build.gradle.kts` 配置声明 Compose Extension，且不依赖宿主隐式提供 Compose。

**Blocked by:** m8test-gradle-plugin `01 — Gradle Plugin 生成 Compose component 与 API classpath`

**Status:** resolved

- [ ] 所有语言分支的 `build.gradle.kts` 显式声明 Compose component。
- [ ] 模板生成的项目配置、component metadata 和 API classpath 一致。
- [ ] Java 使用 `rerunJava` 完成真实设备验证。
- [ ] JavaScript 使用 `rerunJavascript` 完成真实设备验证。
- [ ] 其他语言完成脚本生成、依赖配置和构建验证。
- [ ] 测试串行执行，使用 `--quiet` 控制输出，不新增 Compose 专属 Test Control 协议。
