package com.example.tool;

import com.m8test.script.core.api.console.Console;

public class ToolB {
    // ToolB调用ToolA的方法
    public void methodB(Console console) {
        ToolA toolA = new ToolA();
        toolA.methodA(console);
        console.log("Method B in ToolB called.");
    }
}