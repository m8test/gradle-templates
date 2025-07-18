plugins {
    // 使用 m8test kotlin gradle 插件, 表示这是一个 m8test kotlin 项目
    alias(libs.plugins.m8test.kotlin)
}
// 在gradle.properties中定义的变量, 用于指定m8test版本
val m8testVersion: String by project
// 在gradle.properties中定义的变量, 用于指定scrcpy版本
val scrcpyVersion: String by project
// 在gradle.properties中定义的变量, 用于指定无障碍插件版本
val accessibilityVersion: String by project
// m8testKotlin 闭包用于配置 m8test kotlin 项目
m8testKotlin {
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
            versionName = m8testVersion
            // 模板apk下载地址
            downloadUrl =
                "https://github.com/m8test/runtime-release/releases/download/${versionName}/com.m8test.app.runtime-release_${versionName}.apk"
        }
        // M8Test 开发工具配置闭包
        developmentKitConfig {
            // 开发工具的包名, 使用默认就好
            packageName = "com.m8test.app.developmentkit"
            // 开发工具版本
            versionName = m8testVersion
            // 开发工具下载地址
            downloadUrl =
                "https://github.com/m8test/development-kit-release/releases/download/$versionName/com.m8test.app.developmentkit-release_$versionName.apk"
        }
    }
    // 脚本入口文件(entry)运行时需要调用的内容, 例如需要调用其中的 run() 表示执行该文件中run方法
    caller = "run()"
    // 尝试多少次拉取全局变量的文件, 由于kotlin编译需要较长时间, 所以设置大一点
    pullGlobalVariablesFileTimes = 60
    // 脚本项目配置闭包
    projectConfig {
        // 相对于res目录的资源文件,会打包到apk的assets目录下
        // 这个文件表示apk启动时自动执行的脚本, 只能是true或者false
        addAsset("auto_start_spa.txt")
        // 这个文件表示脚本项目官网,可以是一个网址
        addAsset("official_website.txt")
        // 这个文件表示脚本项目版权信息
        addAsset("copyright.txt")
        // 脚本项目入口文件, 这里是相对于src目录的路径,由于kts后缀文件会被认为是gradle脚本, 所以需要使用kt后缀名作为入口文件, 插件会自动将入口文件的kt改为kts, 请注意, 不要在入口文件之外的其他kt文件中使用m8test脚本提供的全局变量
        entry = "com/example/script/primary.kt"
        // 脚本项目包名, 和java包名规则一样
        packageName = "com.example.script.kotlin"
        // 脚本项目logo配置, 这里是相对于 res 目录的路径
        logo = "logo.png"
        // 在脚本项目中引用 M8Test 组件
        requireComponent {
            // 无障碍组件
            name = "YumiMiyamotoAccessibility"
            // 组件版本
            version = accessibilityVersion
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
            version = scrcpyVersion
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