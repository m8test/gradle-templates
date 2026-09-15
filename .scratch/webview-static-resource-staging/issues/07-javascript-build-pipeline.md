# 07 — JavaScript Build Pipeline WebView 资源验证

**What to build:** 使用真实 JavaScript 脚本项目验证 WebView 静态资源从项目构建到设备运行的完整 Build Pipeline。

**Blocked by:** `m8test-gradle-plugin/03 — 文件型 Test Control properties 与 workspace staging`; `m8test-android/05 — Test Control 与 WebView Extension staging 集成`; `m8test-webview-extension/04 — WebView Extension project root 资源服务`

**Status:** resolved

- [x] JavaScript 模板项目可完成 WebView 资源构建和 APK 生成。
- [x] 构建产物中的项目 root/webview 资源可被运行时发现并服务。
- [x] 验证使用内置 JavaScript 语言，不依赖额外语言 Extension 安装。
- [x] 构建与运行结果可通过结构化证据确认，失败信息可定位到资源或启动阶段。

**Answer:** `:javascript:buildJavascriptApk` 串行验证通过；模板任务清单确认无独立 `runtime` 子项目任务，Runtime 准备由宿主仓库负责。
