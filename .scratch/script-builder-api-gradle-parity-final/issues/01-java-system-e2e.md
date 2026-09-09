# 01 — Java 模板真实设备 system E2E

**What to build:** Java 分支通过真实 Gradle task 生成、上传、构建、运行并验证 Janino 项目。

**Blocked by:** m8test-gradle-plugin/03 — 发布并验证 Gradle plugin 本地版本；m8test-android/07 — Android 真实设备 Builder E2E

**Status:** done

- [x] 通过真实 plugin task 执行完整 init/settings/build/Task/artifact 链路。
- [x] 断言结构化 outcome、artifact 和 Java 脚本行为。
- [x] E2E stop 后只清理本次拥有的 workspace/run 资源。

## 验收记录

Janino/Java 通过真实 Development Kit 设备执行 `rerunJava`。日志标记确认 Build 成功、side1/side2 启动、side1
完成订阅、side2 发布，以及 side1 收到 `app` scope 全局事件；side2 的 `script` scope 事件未错误投递给 side1。
