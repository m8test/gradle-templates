# 04 — Groovy 模板 DSL 与真实设备 E2E

**What to build:** Groovy 模板使用当前 Builder DSL，通过真实 Development Kit debug 设备完成 BuildProjectSource 生成、构建、Project READY 和项目运行。

**Blocked by:** 01 — Android `@Keep` contract 与 Project READY 复核；02 — Groovy Extension executor 与 debug APK 适配；03 — Groovy BuildProjectSource producer 与 READY consumer

**Status:** ready-for-agent

- [ ] 模板 build/settings/init 脚本只使用当前公开 DSL，不依赖宿主非 `@Keep` API。
- [ ] `buildGroovy`/`generateBuildProjectSource` 生成正确的 entry、side、资源和 Extension metadata。
- [ ] `rerunGroovy` 在真实设备验证 Build 成功、entry/side 启动、Logger 和 UI。
- [ ] 验证 side1 收到 `app` scope 事件但不收到 side2 的 `script` scope 事件。
- [ ] 验证旧项目停止、重复运行隔离和 workspace 不被错误清空。
