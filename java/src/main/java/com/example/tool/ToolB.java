package com.example.tool;

import com.m8test.script.core.api.logger.Logger;

public class ToolB {
    // ToolB调用ToolA的方法
    public void methodB(Logger logger) {
        ToolA toolA = new ToolA();
        toolA.methodA(logger);
        logger.info("Method B in ToolB called.");
    }
}
