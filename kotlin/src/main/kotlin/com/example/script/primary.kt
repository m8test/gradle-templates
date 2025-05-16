package com.example.script

import com.example.tool.ToolB
import com.m8test.script.GlobalVariables._console

fun run() {
    // 这里写代码
    var tool: ToolB = ToolB()
    tool.methodB(_console)
}
