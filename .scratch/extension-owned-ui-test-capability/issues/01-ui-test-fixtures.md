# 增加文件型 UI 测试 fixture

Status: resolved
Type: task

## 当前证据

JavaScript 分支已经增加成功/失败的文件型 WebView Extension Test fixture，并确认 `:javascript:buildJavascript` 会把这些 fixture 和 Extension 声明复制到 `BuildProjectSource`。当前仓库只有 `javascript` 被 `settings.gradle.kts` 纳入 Gradle 构建；`java` 目录是生成示例输出，不是可执行 Gradle 子项目，因此 Java 分支的 `rerunJava` 验收不能在本仓库直接执行。

Java 分支在独立 Git worktree 中维护；当前 javascript 工作树已具备可执行的文件型 fixture，Java worktree 也已增加对应的 Java fixture。两者都通过各自的 Test Control 任务，不把不存在于当前 settings 的 Java 项目伪造成当前子项目。

## Goal

为模板提供不依赖 source 字符串转义的测试 manifest、场景文件和资源目录示例。

## Acceptance

- 场景可声明 Extension、Capability、协议、目标进程和资源根目录。
- Compose/WebView 场景正文由对应 Extension 解析，模板不实现 selector/matcher。
- Java/JavaScript 至少各有一个成功与一个失败 fixture。
