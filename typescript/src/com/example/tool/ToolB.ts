// 假设 ToolA 已经被定义在另一个模块中
// 使用 import 语法，路径依赖于 tsconfig.json 的 baseUrl 配置
import ToolA = require('com/example/tool/ToolA');

// 定义 ToolB 类
class ToolB {
    // 此方法只是简单调用ToolA中的方法并且使用全局变量(参数传递的方式接收)简单输出日志以标识方法正确执行，会在入口文件中调用
    /**
     * @param console 这里使用了完整的 Java 类路径作为类型
     */
    public methodB(console: Packages.com.m8test.script.core.api.console.Console): void {
        // ToolB 调用 ToolA 的方法
        const toolA = new ToolA();
        toolA.methodA(console);
        console.log("Method B in ToolB called.");
    }
}

export = ToolB
