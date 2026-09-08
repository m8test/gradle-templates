package com.example.tool

import com.m8test.script.core.api.logger.Logger

class ToolB {
    // ToolB调用ToolA的方法
    void methodB(Logger logger) {
        def toolA = new ToolA()
        toolA.methodA(logger)
        logger.info("Method B in ToolB called.")
    }
}
