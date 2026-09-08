# 03 — runJavascript 真机执行 JavaScript 项目

**Type:** task
**Status:** resolved
**Blocked by:** m8test-android#01、m8test-android#02

## What to build

让 javascript 分支的 `runJavascript` 通过 Gradle Plugin 完成 BuildProjectSource 生成、推送、设备端构建和独立 ProjectScript 启动，并以 UI 可见作为验收。

## Acceptance

- 不绕过 Gradle Plugin 链路直接编写独立 adb/test 流程。
- 真机执行成功，能够看到 `primary.js` 创建的 UI。
- 失败日志写入临时文件，只输出定位所需摘要。

## Comments

已完成。通过本地构建的 Development Kit 执行 `runJavascript`，设备端生成了 `project.config.json`，并显示 `primary.js` 创建的 Compose UI。首次验证发现模板缓存了旧 BuildProjectSource，清理 `javascript` 构建产物后复测通过。
