plugins {
    // 使用 m8test java gradle 插件, 表示这是一个 m8test java 项目
    alias(libs.plugins.m8test.java)
}

// m8testJava 闭包用于配置 m8test java 项目
m8testJava {
    // debugger 闭包用于配置调试器(安卓设备)的信息
    debugger {
        // adb 设备序列, 如果您通过数据线连接则需要填写adb设备序列, 例如 emulator-5554, 如果设置了此属性的话并且不为null的话 adbPort 和 deviceIp 会被忽略
        adbDeviceSerial = "emulator-5554"
        // 安卓设备的ip地址, 这里是局域网的ip地址, 如果是云手机的话可能需要内网穿透, 这里的ip就需要填写有公网ip的服务器的ip地址
        deviceIp = "172.16.0.204"
        // M8Test脚本项目根路径, 电脑端构建好的项目会推送到该路径对应的目录
        projectRoot = "/sdcard/M8Test/project"
        // 安卓设备的adb调试端口, 如果是云手机的话就需要填写内网穿透时映射的adb端口
        adbPort = 5555
        // 安卓设备调试器服务启动的端口
        debuggerPort = 5354
        // 打包apk时的运行时模板apk配置, 打包apk时会将脚本项目打包到该模板apk中
        runtimeConfig {
            // 模板apk的版本
            versionName = "0.1.8"
            // 模板apk下载地址
            downloadUrl =
                "https://github.com/m8test/runtime-release/releases/download/${versionName}/com.m8test.app.runtime-release_${versionName}.apk"
        }
        // M8Test 开发工具配置闭包
        developmentKitConfig {
            // 开发工具的包名, 使用默认就好
            packageName = "com.m8test.app.developmentkit"
            // 开发工具版本
            versionName = "0.1.8"
            // 开发工具下载地址
            downloadUrl =
                "https://github.com/m8test/development-kit-release/releases/download/$versionName/com.m8test.app.developmentkit-release_$versionName.apk"
        }
    }
    // 脚本入口文件(entry)运行时需要调用的内容, 例如需要调用其中的 MyScript.run(); 表示执行该文件中 MyScript 类的静态run方法
    caller = "MyScript.run();"
    // 脚本项目配置闭包
    projectConfig {
        // 脚本项目入口文件, 这里是相对于src目录的路径, 这里直接使用java后缀的文件, 因为这样会有代码提示, 构建脚本项目时入口文件会自动更改为javas后缀
        entry = "com/example/script/primary.java"
        // 脚本项目包名, 和java包名规则一样
        packageName = "com.example.script.java"
        // 脚本项目logo配置, 这里是相对于 res 目录的路径
        logo = "logo.png"
        // 在脚本项目中引用 M8Test 组件
        requireComponent {
            // 无障碍组件
            name = "YumiMiyamotoAccessibility"
            // 组件版本
            version = "0.1.0"
            // 组件下载地址
            url =
                "https://github.com/YumiMiyamoto/accessibility-release/releases/download/Accessibility-$version/com.m8test.accessibility-release_$version.apk"
        }
        // 在脚本项目中引用 M8Test 组件
        requireComponent {
            // ocr组件
            name = "YumiMiyamotoOcr"
            // 组件版本
            version = "0.1.0"
            // 组件下载地址
            url =
                "https://github.com/YumiMiyamoto/ocr-release/releases/download/ocr-$version/com.m8test.ocr-release_$version.apk"
        }
        // 在脚本项目中引用 M8Test 组件
        requireComponent {
            // adb自动化组件
            name = "YumiMiyamotoScrcpy"
            // 组件版本
            version = "3.2.0"
            // 组件下载地址
            url =
                "https://github.com/YumiMiyamoto/scrcpy-release/releases/download/Scrcpy-$version/com.m8test.scrcpy-release_$version.apk"
        }
        // 在脚本项目中引用 M8Test 组件
        requireComponent {
            // opencv 图色组件
            name = "YumiMiyamotoOpencv"
            // 组件版本
            version = "0.1.0"
            // 组件下载地址
            url =
                "https://github.com/YumiMiyamoto/opencv-release/releases/download/opencv-$version/com.m8test.image-release_$version.apk"
        }
    }
    // 构建脚本配置, 一般不会用到
    buildScriptConfig {
//        // 在构架脚本中引入插件
//        requirePlugin {
//            name = "Debugger"
//            version = "0.1.2"
//            url = "https://github.com/m8test/debugger/releases/download/$version/debugger-release-unsigned.apk"
//        }
    }
}