from com.example.tool.ToolA import ToolA
from m8test_java.com.m8test.script.core.api.console.Console import Console


class ToolB:
    def methodB(self, console: Console):
        toolA = ToolA()
        toolA.methodA(console)
        console.log("Method B in ToolB called.")
