# 串行验证模板 UI 测试链路

Status: resolved
Type: task

## 当前证据

- `:javascript:buildJavascript` 已在真实设备上串行通过，证明 BuildProjectSource 和 Extension 声明生成链路可用。
- Compose Extension 的 `verifyDebugTestControl` 已通过 1/1 fixture；WebView Extension 的 `verifyDebugTestControl` 已通过 20/20 fixture，包含 Java/JavaScript/Rhino 的真实脚本 UI 行为。
- 模板自身的 `verifyDebugTestControl` 已由脚本插件统一任务替代；JavaScript 模板的 `verifyJavascriptTestControl` 已在真实设备上串行通过 1/1，摘要位于 `javascript/build/test-control/debug/summary.json`，覆盖独立 HTML、点击和点击后文本断言。`buildJavascriptApk` 另外受 APK Tools 的 `libaapt2.so` native executable staging 缺口影响，该缺口记录在 `m8test-apk-tools-extension`，不应在本 ticket 中伪造为通过。

## Goal

使用现有 rerun 任务和 Development Kit Test Control 验证脚本 UI 测试，不引入第二套安装流程。

## Acceptance

- Java/JavaScript 真机测试串行执行，覆盖目标进程、UI ready、action 后断言和超时；JavaScript `verifyJavascriptTestControl` 与 Java worktree 的 `verifyJavaTestControl` 均已在同一真实设备上串行通过 1/1。Java fixture 使用显式 UI session 结束信号，断言 `1 → 2`，不依赖日志到达时序。
- 其他语言分支至少完成组件依赖和构建验证。
- 日志可定位失败但不输出完整 Gradle 日志。
