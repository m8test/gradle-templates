package com.example.tool

import com.m8test.script.core.api.console.Console

class ToolB {
    // ToolB调用ToolA的方法
    fun methodB(console: Console) {
        val toolA = ToolA()
        toolA.methodA(console)
        console.log("Method B in ToolB called.")
    }
}