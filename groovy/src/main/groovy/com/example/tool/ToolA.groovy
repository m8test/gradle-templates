package com.example.tool

import com.m8test.script.core.api.logger.Logger

class ToolA {
    // ToolA调用ToolB的方法
    void methodA(Logger logger) {
        logger.info("Method A in ToolA called.")
    }
}
