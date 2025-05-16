package com.example.tool;

import com.m8test.script.core.api.console.Console;

public class ToolA {
    // ToolA调用ToolB的方法
    public void methodA(Console console) {
        console.log("Method A in ToolA called.");
    }
}