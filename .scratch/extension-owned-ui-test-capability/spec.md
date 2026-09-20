# Gradle 模板 Extension UI 测试规格

Status: ready-for-agent
Type: task
标签：ready-for-agent

## 问题

模板项目需要验证脚本 UI 测试链路，但不能把 Compose、WebView 或 instrumentation 实现复制到每个语言分支。不同语言的脚本输入形式可以不同，测试控制协议必须一致。

## 方案

所有语言分支使用同一套测试 manifest/场景语义：声明 Extension、Capability、协议、目标进程和场景文件。脚本 UI 内容使用文件资源（例如 `index.html` 或 Compose 场景文件）进入 staging，不再依赖复杂的内嵌 source 字符串转义。

JavaScript 和 Java 作为真实设备验收分支，通过现有 `rerunJavascript`/对应任务启动脚本并调用通用 Test Control；Lua、Groovy 等分支先完成 `build.gradle.kts` 的组件依赖、manifest 和构建验证，再按各自 Runtime 可用性补充设备测试。

## 决策

1. 每个需要脚本 UI 的分支只声明相应 UI Extension；WebView 不强制 Compose Extension，Compose 也不携带 WebView 测试依赖。
2. 测试目标明确指定 `main`、`runtime1` 或 `runtime2`，不依赖默认主进程。
3. UI 断言由 Extension 的测试 Capability 解析：Compose 使用官方 Semantics/Compose UI Test 概念，WebView 使用 Page/Locator/Frame/CDP 概念；模板不解析节点。
4. 测试步骤支持 action 后的显式 wait/assert，禁止用固定 sleep 作为成功条件。
5. 真实设备测试串行执行，先确认 ADB、Extension、目标进程和 UI session ready，再执行场景；失败输出稳定错误码和有限诊断日志。

## 验收

- JavaScript/Java 分支可通过文件型场景验证 UI 初始状态、点击动作和点击后状态。
- 至少一条场景验证目标进程选择不会错误落到主进程。
- WebView 场景能够访问实际 Page/Locator；Compose 场景能够访问实际 Semantics 节点。
- 构建失败时输出资源/staging/协议定位信息，不打印完整 Gradle 日志撑爆上下文。
- 所有分支的 `build.gradle.kts` 仅声明公开组件依赖，不复制宿主内部实现。

## 不在范围内

- 不在模板中实现 Extension 测试引擎。
- 不为每种语言设计不同的 UI 测试协议。
- 不把测试 APK 作为模板项目自行下载或安装；其生命周期由 Extension 和 Development Kit 的统一 ADB 流程处理。

## 归属

本规格只描述模板输入、示例和验收；通用构建交接归 `m8test-gradle-plugin`，协议外壳归 `m8test-contracts`，UI 具体执行归对应 Extension。
