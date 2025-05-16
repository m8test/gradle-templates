// 定义 ToolA 类
function ToolA() {
    // ToolA调用ToolB的方法
    /**
     * @param {Packages.com.m8test.script.core.api.console.Console} console
     */
    this.methodA = function (console) {
        console.log("Method A in ToolA called.");
    };
}

// 导出 ToolA 类
module.exports = ToolA;