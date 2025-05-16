package com.example.tool

import com.m8test.script.core.api.console.Console

class ToolA {
    // ToolA调用ToolB的方法
    fun methodA(console: Console) {
        console.log("Method A in ToolA called.")
    }
}