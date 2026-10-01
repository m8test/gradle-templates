// 定义 ToolA 类
class ToolA {
    // 此方法只是使用全局变量(参数传递的方式接收)简单输出日志以标识方法正确执行，会在 ToolB 中调用
    public methodA(logger: Packages.com.m8test.script.core.api.logger.Logger): void {
        logger.info("Method A in ToolA called.");
    }
}

export = ToolA
