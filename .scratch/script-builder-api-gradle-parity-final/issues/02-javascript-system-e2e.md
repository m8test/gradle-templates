# 02 — JavaScript 模板真实设备 system E2E

**What to build:** JavaScript 分支通过真实 Gradle task 生成、上传、构建、运行并验证 Rhino 项目，同时保持 UI 普通运行功能。

**Blocked by:** m8test-gradle-plugin/03 — 发布并验证 Gradle plugin 本地版本；m8test-android/07 — Android 真实设备 Builder E2E

**Status:** done

- [x] 通过真实 plugin task 执行完整 init/settings/build/Task/artifact 链路。
- [x] 断言结构化 outcome、artifact 和 JavaScript 脚本行为。
- [x] 区分普通长运行与 E2E stop/cleanup，不能删除仍运行的普通项目。

## 验收记录

Rhino/JavaScript 通过真实 Development Kit 设备执行 `rerunJavascript`。日志标记确认 Build 成功、side1/side2 启动、
side1 完成订阅、side2 发布，以及 side1 收到 `app` scope 全局事件；side2 的 `script` scope 事件保持隔离。
