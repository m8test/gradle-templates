# -*- coding: utf-8 -*-
import os

from m8test_java.com.m8test.script.GlobalVariables import _logger, _files, _extensions, _java
from m8test_java.com.m8test.python.PythonJepBridge import PythonJepBridge

_logger.info("PYTHON_INIT_ACCESS_JVM_BEGIN")

# 动态加载随脚本项目推送的 Java 源文件。Java 文件必须由当前脚本显式加载，
# 这样可以验证外部 Java 源文件编译、Dex 转换和 ClassLoader 更新链路。
java_file = _files.buildFile(
    lambda builder: builder.setPath(
        os.path.abspath(os.path.join(
            os.path.dirname(__file__), "..", "res",
            "com", "example", "script", "PythonJvmAccess.java"
        ))
    )
)
PythonJepBridge().loadJavaFile(_extensions, java_file)
_logger.info("PYTHON_INIT_ACCESS_JVM_JAVA_FILE_LOADED")

PythonJvmAccess = _java.loadClass("com.example.script.PythonJvmAccess")
_logger.info(PythonJvmAccess.message())
_logger.info("PYTHON_INIT_ACCESS_JVM_STATIC_METHOD_DONE")
python_jvm_access = PythonJvmAccess()
_logger.info(python_jvm_access.instanceMessage())
_logger.info("PYTHON_INIT_ACCESS_JVM_INSTANCE_METHOD_DONE")
_logger.info(PythonJvmAccess.STATIC_FIELD)
_logger.info(python_jvm_access.instanceField)
_logger.info(str(PythonJvmAccess.sumInt(2, 3)))
_logger.info(str(PythonJvmAccess.sumLong(4, 5)))
_logger.info(str(PythonJvmAccess.sumNumbers(1, 2, 3.5, 4.25)))
_logger.info(PythonJvmAccess.describe("python", True, "!"))
_logger.info(str(PythonJvmAccess.arrayLength([1, 2, 3])))
_logger.info(PythonJvmAccess.joinStrings(["one", "two", "three"]))
_logger.info(PythonJvmAccess.listSummary(["first", "last"]))
_logger.info(PythonJvmAccess.callback(lambda value, count: value + ":" + str(count)))
_logger.info(python_jvm_access.instanceValues("instance", 9, False))
_logger.info(python_jvm_access.instanceArray([4, 5, 6]))

# 创建 java 对象
from m8test_java.java.lang.StringBuilder import StringBuilder

sb = StringBuilder("M8Test")
# 通过 . 调用 java 对象方法
sb.append("Python")
_logger.info(sb.toString())
# 通过 . 调用java静态方法
from m8test_java.java.lang.System import System

_logger.info(str(System.currentTimeMillis()))
# 可以通过`.`访问静态属性
from m8test_java.android.view.Gravity import Gravity

_logger.info(str(Gravity.CENTER))
# 如果不能通过`.`访问静态属性, 那么可以反射获取
from m8test_java.com.m8test.script.GlobalVariables import _reflectors
from m8test_java.android.widget.FrameLayout import FrameLayout

wrap_content = PythonJepBridge().getStaticField(
    _reflectors, FrameLayout.LayoutParams, "WRAP_CONTENT"
)
_logger.info(str(wrap_content))
# 访问java内部类可以使用 . , 必须使用'外部类.内部类'的方式调用
layout_params = FrameLayout.LayoutParams(wrap_content, wrap_content)
_logger.info(str(layout_params))
_logger.info("PYTHON_INIT_ACCESS_JVM_DONE")
