# Java 与 JavaScript 模板真实设备构建规格

**Status:** ready-for-agent

## Problem Statement

只验证 Builder 单元测试、生成文件或 Android instrumentation，不能证明真实模板用户通过 Gradle plugin 生成的项目会在设备上执行 init/settings/build/Task 并运行最终 artifact。模板的普通 run 又可能持续显示 UI，测试若直接复用并删除目录会破坏真实运行语义。

## Solution

在 Java 与 JavaScript 分支使用插件提供的真实 Gradle 任务完成 system E2E。任务生成并上传唯一 BuildProjectSource，经 debugger 与 typed JSON-RPC client 构建，断言结构化 outcome、TaskOutcome、artifact 和脚本可观察行为，然后停止测试 run 并清理测试拥有的目录。普通 run 保持持续运行且不自动删除。

## User Stories

1. 作为 Java 模板用户，我希望 Janino 项目通过真实设备构建和运行。
2. 作为 JavaScript 模板用户，我希望 Rhino 项目通过真实设备构建和运行。
3. 作为模板维护者，我希望 init/settings/build 与 Task action 都被真实执行。
4. 作为测试执行者，我希望 Gradle 任务在结构化结果错误时失败。
5. 作为普通用户，我希望 run 后 UI/脚本继续运行且项目文件不被删除。
6. 作为测试维护者，我希望 E2E stop 后只清理自己的唯一目录。
7. 作为调试者，我希望 Gradle 输出简洁但完整日志可从临时文件定位。
8. 作为跨项目维护者，我希望模板验证明确使用刚发布的 plugin 和 contracts 版本。

## Implementation Decisions

- Java 分支验证 Janino，JavaScript 分支验证 Rhino。
- 复用 plugin 贡献的生成、上传、构建、运行和停止能力；不实现第二套 ADB/RPC client。
- 如既有任务不能断言结果，新增聚合 verification task，但不改变普通任务行为。
- 每次 E2E 使用唯一 workspace child 和 runId。
- 构建链路必须经过 init settings、settings、各 project init build、build scripts、Task graph 和 artifact publish。
- 普通 run 持有 artifact lease；E2E 在断言后 stop、释放 lease、再删除测试目录。
- 其他语言分支不作为本批运行矩阵，但必须静态扫描确定不存在旧 Builder API 调用。
- 一次性迁移生成调用，不保留旧模板入口。

## Testing Decisions

- 验证 Gradle task 真正连接当前设备并运行脚本，不以生成文件存在代替执行。
- 断言 BuildOutcome 成功、预期 TaskOutcome、artifactId 和语言侧 marker/行为。
- 覆盖一个结构化构建失败场景，证明错误可定位且不启动旧 UI 项目。
- 验证 workspace root 从不作为项目执行。
- 验证普通 run 不清理，E2E 只清理 owned child。
- Java 与 JavaScript 分支分别执行相同语义矩阵。
- 命令使用 quiet，完整日志写临时目录，只输出失败摘要。

## Out of Scope

- 在模板仓库实现 Builder runtime、contracts 或客户端。
- 把所有语言分支加入本批真实设备矩阵。
- 改变示例项目功能、UI 或普通长运行行为。
- 解析构建日志文本作为成功依据。

## Further Notes

本规格被 contracts、Android core 和 Gradle plugin 发布阻塞，是跨项目迁移的最终验收门禁。
