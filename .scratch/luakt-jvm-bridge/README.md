# Lua 模板 luakt JVM 验收

## 验收入口

使用 `./gradlew -q :lua:rerunLua` 在 Android 真机执行 Lua 项目。完整 Gradle 输出重定向到 `/tmp`，终端只保留失败摘要和关键设备日志。

## 验收结果

- `access-jvm.lua` 明确输出 `access-jvm init started` 和 `access-jvm init completed`。
- 动态 `.java` 文件通过 `$extensions.loadJavaFile` 加载成功。
- 基本类型、包装类型、`char`、数组、List、Set、Map、日期、大数、`null` 和 varargs 访问成功。
- `Runnable` SAM 回调输出 `dynamic.sam Runnable invoked`。
- 主脚本完成 UI 执行，项目生命周期输出 `onFinish`。
- 两个 side 脚本分别输出 `side1 side script executed` 和 `side2 side script executed`。
- 连续两次 `rerunLua` 均成功，第二次仍能重新加载动态类并输出相同关键日志。
- 人为 side 错误可以直接显示脚本路径和行号，例如 `side1.lua:6 attempt to call nil`。

## 约束

- 动态 Java 测试文件使用 `.java` 扩展名；`javas` 继续表示 Java 脚本形式。
- 本模板验收只验证 Lua/luakt 已适配的 JVM 和 Logger 行为，不扩大到其他语言分支。
