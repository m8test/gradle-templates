// 定义 ToolA 类
class ToolA {
    // 此方法只是使用全局变量(参数传递的方式接收)简单输出日志以标识方法正确执行，会在 ToolB 中调用
    /**
     * @param console 这里使用了完整的 Java 类路径作为类型
     */
    public methodA(console: Packages.com.m8test.script.core.api.console.Console): void {
        console.log("Method A in ToolA called.");
    }
}

export = ToolA