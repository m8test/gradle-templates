# 模板 Extension UI 测试任务地图

Status: resolved
Type: task
标签：ready-for-agent

## Tickets

- [01-ui-test-fixtures.md](issues/01-ui-test-fixtures.md)：增加语言无关测试 manifest/scenario fixture。
- [02-device-validation.md](issues/02-device-validation.md)：串行验证 Java/JavaScript 真机链路及其他分支构建配置。

当前 JavaScript 和 Java 分支的构建、文件型 fixture 与 UI session 编排证据已具备；Java 在独立 Git worktree 中执行。APK Tools 的 `libaapt2.so` 执行问题属于另一个仓库的 deferred 缺口。
