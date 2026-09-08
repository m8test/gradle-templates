// 假设 ToolA 已经被定义在另一个模块中
// 不能使用./, 需要使用相对于src目录的路径
// const ToolA = require('./ToolA'); // 错误示例
const ToolA = require('com/example/tool/ToolA');

// 定义 ToolB 类
function ToolB() {
    /**
     * @param {Packages.com.m8test.script.core.api.logger.Logger} logger
     */
    this.methodB = function (logger) {
        // ToolB 调用 ToolA 的方法
        const toolA = new ToolA();
        toolA.methodA(logger);
        logger.info("Method B in ToolB called.");
    };
}

// 导出 ToolB 类
module.exports = ToolB;
