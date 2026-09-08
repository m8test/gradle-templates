# Groovy 模板 BuildProjectSource 设备运行

Groovy 模板通过 `com.m8test.gradle.script.groovy` 生成 BuildProjectSource。它声明 `com.m8test.extension.language.groovy`，版本必须与 Groovy Extension 最近一次 `debugReinstallExtension` 发布到本地 cache 的版本一致，并保持 `isPluginsBundled = false`。

`runGroovy` 必须完成生成、推送、BuildScript 和独立 ProjectScript 启动。入口使用当前 Compose API，side 使用当前事件 scope/payload API；旧 `subscribeLocally`、`publishGlobally` 和 `Event.setData` 示例全部删除。

本轮没有新增或修改公开 `@Keep` API；`.groovy` 仍是文件类型 descriptor。

模板只生成、推送并启动 BuildProjectSource 流程。BuildScript 和 ProjectScript 是 Development Kit 中两个独立的 Script 生命周期；模板不依赖构建脚本的 ClassLoader、Bindings 或完成事件来执行项目入口。入口和 side 的项目类加载由 Groovy Project Executor 通过当前 Script 的 Extensions 完成。

模板不声明或注册项目 dex/class/jar 为 Managed Extension；这些内容属于 BuildScript 输出的 ProjectCodeArtifact，由每个 ProjectScript 独立加载。模板验收必须覆盖入口和 side 的并发执行以及一次运行结束后的再次运行。

模板验收还必须确认：BuildScript 成功后才启动 ProjectScript；入口成功后才启动 side；一次运行结束后再次执行会创建新的 ProjectScript classpath，并且 UI、side 事件和生命周期日志仍然正常。
