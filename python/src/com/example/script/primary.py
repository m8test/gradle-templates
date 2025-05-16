# from 引入时必须使用相对于src目录的路径
from com.example.tool.ToolB import ToolB
# 必须使用 `from m8test_java.com.m8test.script.GlobalVariables import xxx` xxx是全局变量名
from m8test_java.com.m8test.script.GlobalVariables import _console

toolB = ToolB()
toolB.methodB(_console)
