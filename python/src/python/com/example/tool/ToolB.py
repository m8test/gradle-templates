from m8test_java.com.m8test.script.core.api.logger.Logger import Logger
from python.com.example.tool.ToolA import ToolA


class ToolB:
    def methodB(self, logger: Logger):
        toolA = ToolA()
        toolA.methodA(logger)
        logger.info("Method B in ToolB called.")
