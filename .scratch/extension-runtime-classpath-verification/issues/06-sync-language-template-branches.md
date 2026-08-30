# 06 — 同步 Java、JavaScript、TypeScript 模板分支

**What to build:** 将 Groovy 分支的公共 Gradle、版本和 Development Kit 适配迁移到 Java、JavaScript、TypeScript 分支，使所有模板生成一致的 RuntimeSource。

**Blocked by:** 05 — RuntimeSource 与 Extension 验证任务

**Status:** ready-for-agent

- [ ] 所有目标分支统一 Gradle 9.7.0、version catalog、m8test 和 contracts 版本
- [ ] 执行 Gradle 前完成新 Extension ID 和 Development Kit 配置迁移
- [ ] 公共任务和 Test Control 配置同步，语言业务源码保持独立
- [ ] 各分支分别验证 IDE 源码和 RuntimeSource
- [ ] 各分支独立提交并记录验证证据

