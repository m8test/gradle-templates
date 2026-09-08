package com.example.tool;

import com.m8test.script.core.api.logger.Logger;

public class ToolA {
    // ToolA调用ToolB的方法
    public void methodA(Logger logger) {
        logger.info("Method A in ToolA called.");
    }
}
