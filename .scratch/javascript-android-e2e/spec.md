# JavaScript 模板 Android 端到端规格

## Problem Statement

gradle-templates/javascript 必须使用最新 m8test-android Development Kit，在 Android 设备上真正执行项目脚本。`runJavascript` 的成功不能只由 Gradle、推送或 BuildScript 日志判断。

## Solution

使用现有 Gradle Plugin 链路生成并推送 `BuildProjectSource`，触发设备端 BuildScript，等待构建完成后启动独立 JavaScript ProjectScript。模板的 `primary.js` 创建可见 UI，作为最终验收信号。

## Acceptance Criteria

- `runJavascript` 使用 `/sdcard/M8Test/project` 作为设备项目根目录。
- 设备使用最新 `com.m8test.app.developmentkit`，并能加载 `com.m8test.extension.language.javascript`。
- `primary.js`、side scripts 和 `init/access-jvm.js` 按配置进入正确目录。
- Android 设备上实际出现 `primary.js` 创建的脚本 UI。
- 失败时保留有界、可定位的 Gradle/ADB/设备日志。

## Testing Seam

真实设备执行模板仓库根目录的 `runJavascript` 任务，检查 BuildProjectSource、BuildScript、ProjectScript 和可见 UI 的完整链路。
