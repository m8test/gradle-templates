# 01 — 运行 Groovy 模板项目

**Status:** resolved

- [x] 生成并推送 BuildProjectSource。
- [x] 使用 Groovy Extension cache 完成设备构建。
- [x] 启动 Groovy ProjectScript。
- [x] 设备显示 Compose UI，按钮计数由 12 变为 13。
- [x] BuildScript 的类加载状态没有泄漏到 ProjectScript。
- [x] 入口和两个 side 使用独立 ProjectScript 类加载实例并成功执行。
- [x] `rerunGroovy` 关闭旧 Script 后创建新 ProjectScript classpath，UI 计数重置为 0。
- [x] 设备日志区分 BuildScript、当前 Script classpath、入口加载和脚本运行错误。

## Answer

已在 Pixel 7 上完成 `runGroovy` 与 `rerunGroovy` 验收；入口 Compose UI 和两个 side 脚本均成功运行。
