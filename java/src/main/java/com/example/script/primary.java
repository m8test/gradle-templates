package com.example.script;

import static com.m8test.script.GlobalVariables.$console;
import com.example.tool.ToolB;

class MyScript {
    public static void run() {
        // 这里写代码
        ToolB tool = new ToolB();
        tool.methodB($console);
    }
}