# [M8Test](https://dev-docs.m8test.com) Kotlin Gradle 模板

> 如有疑问，请加入我们的官方 QQ 群：[749248182](https://qm.qq.com/q/jVVADJCAI8) 或 QQ
> 频道：[m8testofficial](https://pd.qq.com/s/8pidhywcy)

---

## 📦 Gradle 任务说明

### 任务分组

1. **`m8test-build`**  
   M8Test构建相关任务组，包含`buildKotlin`（构建Kotlin项目）、`runKotlin`（运行项目）等任务。

2. **`m8test-code-completion`**  
   M8Test代码补全相关任务组，包含`generateKotlinGlobalVariables`（生成全局变量代码补全文件）等任务。

3. **`m8test-developmenet-environment`**  
   M8Test开发环境相关任务组，包含`installDevelopmentEnvironment`（安装开发环境）、`installKotlinPlugin`（安装语言插件）等任务。

4. **`m8test-download`**  
   M8Test资源下载相关任务组，包含`downloadKotlinCodeTemplate`（下载示例代码模板）、`downloadKotlinDocs`（下载文档）等任务。

> 实际开发中主要关注构建任务和代码补全任务即可。

### 常用任务

| 任务名称                            | 任务分组                     | 功能描述                                                           |
|---------------------------------|--------------------------|----------------------------------------------------------------|
| `generateKotlinGlobalVariables` | `m8test-code-completion` | 生成Kotlin全局变量代码补全文件，提供IDE代码提示功能。需连接安卓设备，建议编写代码前执行一次，依赖更新后需重新执行。 |
| `buildKotlin`                   | `m8test-build`           | 构建M8Test Kotlin脚本项目源码（不执行），构建结果位于`build/project`目录。            |
| `runKotlin`                     | `m8test-build`           | 构建并将项目推送到已连接的安卓设备上运行。                                          |
| `buildKotlinApk`                | `m8test-build`           | 将M8Test Kotlin脚本项目打包成APK文件。                                    |

---

## 🚀 执行任务的方法

### 方法一：使用快捷搜索

1. 双击`Ctrl`键打开全局搜索框
2. 输入`gradle 任务名`（如`gradle runKotlin`）
3. 按回车执行

![方法一示例](images/1.png)

### 方法二：通过Gradle面板

1. 点击IDE右侧Gradle面板图标
2. 依次展开`kotlin` > `Tasks` > `m8test`
3. 双击需要执行的任务

![方法二示例](images/2.png)

### 方法三：使用终端命令

1. 按`Alt + F12`打开终端
2. 输入命令`./gradlew 任务名`（如`./gradlew runKotlin`）
3. 按回车执行

![方法三示例](images/3.png)

---

## ⚙️ 配置脚本项目

脚本项目配置位于`kotlin/build.gradle.kts`文件，可根据需要修改设备IP、ADB端口等参数，文件内包含详细注释可供参考。

---

## 🛠️ 项目开发流程

1. **生成全局变量**  
   执行`generateKotlinGlobalVariables`任务生成代码补全文件，提供IDE代码提示功能。

2. **连接日志服务**  
   按`Alt + T`，依次选择`M8Test` > `连接日志服务`。

3. **编写并运行脚本**  
   编写代码保存后，执行`runKotlin`任务在安卓设备上运行项目（需提前开启ADB调试），运行日志可在M8Test日志面板查看。

4. **打包Apk**  
   所有的脚本开发工作都完整后, 如果你需要打包成独立的apk可以执行 `buildKotlinApk` 任务。
