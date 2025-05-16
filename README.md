# [M8Test](https://dev-docs.m8test.com) JavaScript Gradle 模板

> 如有疑问，请加入我们的官方 QQ 群：[749248182](https://qm.qq.com/q/jVVADJCAI8) 或 QQ
> 频道：[m8testofficial](https://pd.qq.com/s/8pidhywcy)

---

## 📦 Gradle 任务说明

1. **`buildM8TestJavascript`**  
   构建 M8Test JavaScript 脚本项目源码，但不执行脚本。构建完成后，源码位于 `build/project` 目录。

2. **`generateJavascriptGlobalVariables`**  
   生成 JavaScript 全局变量，提供代码提示功能。需连接安卓设备，建议在编写代码前执行一次。如果 `build.gradle.kts`
   中的依赖组件更新，需重新执行。

3. **`handleJavascriptAndroidJar`**  
   生成 Android API 的 JavaScript 代码提示文件。此任务耗时较长，执行一次即可。成功后，JavaScript 代码中将有 Android API
   的代码提示。

4. **`installNodeJs`**  
   安装 Node.js。执行此任务会从网络下载 Node.js 并安装到 `~/.m8test/bin/nodejs` 目录。若已安装 Node.js，执行此任务不会重新安装。

5. **`runM8TestJavascript`**  
   构建 M8Test JavaScript 脚本项目，并将项目推送到已连接的安卓设备上运行。

---

## 🚀 执行任务的方法

### 方法一：使用快捷搜索

1. 双击 `Ctrl` 键，打开搜索框。
2. 输入 `gradle 任务名`，例如 `gradle runM8TestJavascript`，然后按回车执行。

   ![方法一示例](images/1.png)

### 方法二：通过 Gradle 面板

1. 点击 Gradle 图标。
2. 依次展开 `javascript` > `Tasks` > `m8test`。
3. 双击需要执行的任务。

   ![方法二示例](images/2.png)

### 方法三：使用终端命令

1. 按下快捷键 `Alt + F12` 打开终端。
2. 输入命令 `./gradlew 任务名`，例如 `./gradlew runM8TestJavascript`，然后按回车执行。

   ![方法三示例](images/3.png)

---

## ⚙️ 配置脚本项目

脚本项目的配置位于 `javascript/build.gradle.kts` 文件中。您可以根据需要修改设备的 IP、ADB 端口等参数。该文件中包含详细的代码注释，供参考。

---

## 🛠️ 项目开发流程

1. **生成全局变量**  
   执行 `generateJavascriptGlobalVariables` 任务，生成 JavaScript 全局变量，提供代码提示功能。

2. **生成 Android API 提示文件**  
   执行 `handleJavascriptAndroidJar` 任务，生成 Android API 的 JavaScript 代码提示文件。

3. **安装 Node.js**  
   执行 `installNodeJs` 任务，安装 Node.js。

4. **配置 Node.js 环境**

    - 按下快捷键 `Ctrl + Alt + S` 打开设置。
    - 依次选择 `Language & Framework` > `Node.js` > `Node Interceptor`。
    - 点击 `Add` > `Add Local`，选择 Node.js 的安装路径，例如 `C:\Users\Your username\.m8test\bin\nodejs\node.exe`。
    - 点击 `OK`，然后点击 `Apply` 应用设置。

      ![配置 Node.js 环境](images/4.png)

5. **启用 Node.js 代码助手**

   如果出现 `Unresolved function or method require()` 的提示，点击 `Enable coding assistance for Node.js`。

   ![启用代码助手提示](images/5.png)

   在弹出的对话框中点击 `Configure` 按钮。

   ![配置代码助手](images/6.png)

   等待启用成功，成功后界面如下所示：

   ![代码助手启用成功](images/7.png)

6. **连接日志服务**

   按下快捷键 `Alt + T`，依次选择 `M8Test` > `连接日志服务`。

7. **编写并运行脚本**

   编写代码并保存后，执行 `runM8TestJavascript` 任务，即可在安卓设备上运行脚本项目。确保安卓设备已开启 ADB 调试。运行日志可在
   M8Test 日志面板中查看。
