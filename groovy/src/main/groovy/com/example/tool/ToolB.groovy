package com.example.tool

import com.m8test.script.core.api.console.Console

class ToolB {
    // ToolB调用ToolA的方法
    void methodB(Console console) {
        def toolA = new ToolA()
        toolA.methodA(console)
        console.log("Method B in ToolB called.")
    }
}