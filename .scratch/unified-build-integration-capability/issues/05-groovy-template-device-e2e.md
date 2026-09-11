# 05 — Groovy Template 真实设备 E2E

**What to build:** Groovy 模板通过真实 Gradle task 生成并上传独立 workspace 子项目，在 Development Kit 中完成 BuildScript 和 ProjectScript 执行，验证最终用户可观察行为。

**Blocked by:** 02 — Groovy Extension 接入统一 BuildIntegrationCapability；04 — Gradle Plugin 适配统一 Capability 与安装编排

**Status:** done

- [x] `buildGroovy`/`generateBuildProjectSource` 生成的 BuildProjectSource 使用当前 DSL 和 manifest 语义。
- [x] 真实设备执行 BuildScript，结构化 BuildOutcome 成功且不再出现 provider discovery 错误。
- [x] ProjectScript 的 entry、side、UI、Logger、app scope 事件和 script scope 隔离通过断言。
- [x] `rerunGroovy` 使用新 run/lease/classpath，长期运行项目不被测试清理逻辑删除。
- [x] 只要求 debug Development Kit/Extension；release/R8 另立门禁。

验证记录：在真实设备上执行 `./gradlew -q :groovy:runGroovy`；设备日志确认 `BUILD SUCCESSFUL`、`compileGroovySrc`、`SIDE1_STARTED`、`SIDE2_STARTED`、`SIDE1_SUBSCRIBED`、`SIDE1_LOCAL_EVENT`、`SIDE2_LOCAL_PUBLISHED`、`SIDE2_GLOBAL_PUBLISHED` 和 `SIDE1_GLOBAL_EVENT`。其中 side 间的本地 scope 按设计隔离，不要求 `side1` 接收 `side2` 的本地事件；`side1` 自己发布的本地事件用于证明本地订阅可用。
