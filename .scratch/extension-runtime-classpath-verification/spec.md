# 脚本模板 BuildProjectSource 与 Development Kit 联动规格

## Problem Statement

各语言分支的 Gradle wrapper、m8test 版本和插件配置已经漂移。Groovy 分支的新配置尚未同步到 Java、JavaScript、TypeScript 分支，旧配置会导致 Gradle 重新下载、使用旧插件并在破坏性 API 变更后失败。

## Solution

先将 Groovy 分支已经完成的公共适配同步到 Java、JavaScript、TypeScript 分支，再分别验证语言专属 BuildProjectSource。模板项目只生成和推送 BuildProjectSource；Development Kit 负责初始化、编译、执行和 Runtime APK 打包。

## User Stories

1. 作为脚本作者，我希望所有语言分支使用同一个 Gradle wrapper。
2. 作为脚本作者，我希望 version catalog、m8test 和 contracts 版本保持一致。
3. 作为 Groovy 作者，我希望初始化脚本 attach 的类能在入口脚本直接 import。
4. 作为 Java 作者，我希望 Java 包装源码仍能提供 IDE 代码提示并生成正确 BuildProjectSource。
5. 作为 JavaScript/TypeScript 作者，我希望语言包装和入口生成不被公共迁移破坏。
6. 作为维护者，我希望执行 Gradle 前就完成所有版本和配置迁移。
7. 作为维护者，我希望每个语言分支独立验证并独立提交。
8. 作为维护者，我希望业务脚本源码默认不被修改。

## Implementation Decisions

- 统一 Gradle 9.7.0、version catalog、m8test 版本和 contracts 版本。
- 公共适配包括插件配置、Development Kit 配置、BuildProjectSource 任务、Test Control 配置和版本字段。
- Groovy、Java、JavaScript、TypeScript 的业务脚本和语言专属源码转换保持独立。
- 运行入口由 Development Kit 执行；模板只负责生成、推送和触发验证。
- 旧的 m8test-build、runGroovy 等任务按新任务模型重命名或删除，不保留兼容别名。
- BuildProjectSource 覆盖初始化脚本、入口脚本、Extension 依赖声明和语言包装。
- 旧 Extension ID 配置迁移到统一 Extension ID 配置。

## Testing Decisions

每个分支在配置同步后才执行 Gradle：验证 wrapper 和版本、IDE 辅助源码、BuildProjectSource、Development Kit 初始化和入口执行。Groovy 验证直接 import、attach 和 loadClass；Java 验证 Java import；JavaScript/TypeScript 验证语言对应类加载语法。只有明确的 Keep/API 破坏才修改业务源码。

## Out of Scope

- 不在模板项目中实现 Runtime。
- 不在模板项目中复制 APK 打包逻辑。
- 不在本规格中完成所有语言分支。
- 不在执行旧配置状态下运行 Gradle 任务。

## Further Notes

分支适配必须先提交公共配置，再执行受控任务；各分支记录独立验证证据。
