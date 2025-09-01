# -*- coding: utf-8 -*-
# 导入java类, 使用 from import 语句, from m8test_java.全类名 import 类名，这是固定格式

# 创建 java 对象
from m8test_java.java.lang.StringBuilder import StringBuilder

sb = StringBuilder("M8Test")
# 通过 . 调用 java 对象方法
sb.append("Python")
from m8test_java.com.m8test.script.GlobalVariables import _console

_console.log(sb.toString())
# 通过 . 调用 java 对象属性
from m8test_java.com.m8test.script.core.impl.JavaTypeTester import JavaTypeTester

_console.log(JavaTypeTester().OBJECT_FIELD)
# 通过 . 调用java静态方法
from m8test_java.java.lang.System import System

_console.log(System.currentTimeMillis())
# 可以通过`.`访问静态属性
from m8test_java.android.view.Gravity import Gravity

_console.log(Gravity.CENTER)
# 如果不能通过`.`访问静态属性, 那么可以反射获取
from m8test_java.com.m8test.script.GlobalVariables import _reflectors
from m8test_java.android.widget.FrameLayout import FrameLayout

wrap_content = (_reflectors.reflect(FrameLayout.LayoutParams)
                .getField(None, lambda selector: selector.setName("WRAP_CONTENT")))
_console.log(wrap_content)
# 访问java内部类可以使用 . , 必须使用'外部类.内部类'的方式调用
layout_params = FrameLayout.LayoutParams(wrap_content, wrap_content)
_console.log(layout_params)

# 使用 from import 语句同时使用 as m8test_javaxxx这样格式的, 如果在类的继承中会自动移除, 例如 class A(m8test_javaxxx) , class A(m8test_javaxxx.yyy.zz) 会被替换为 class A()
from m8test_java.com.m8test.script.core.impl.JavaTypeTester import JavaTypeTester as m8test_javaHello


# 下面的类定义会被替换为  class MultiAbstractMethodInterfaceImpl(): , 下面的写法主要是为了能有代码提示
class MultiAbstractMethodInterfaceImpl(m8test_javaHello.MultiAbstractMethodInterface):
    def setInt(self, i):
        _console.log("setInt " + str(i))

    def getInt(self):
        _console.log("getInt")
        return 0

# _java.proxy用于将python对象映射为java接口对象的实现类, 也就是第二个参数中指定的接口数组
from m8test_java.com.m8test.script.GlobalVariables import _java
JavaTypeTester.setMultiAbstractMethodInterface(_java.proxy(MultiAbstractMethodInterfaceImpl(), [
    "com.m8test.script.core.impl.JavaTypeTester$MultiAbstractMethodInterface"]))
mami = JavaTypeTester.getMultiAbstractMethodInterface()
mami.setInt(1234)
_console.log(mami.getInt())


# 实现java功能性接口, 定义一个函数/lambda
def sami():
    _console.log("getInt")
    return 0


# 功能性接口可以直接使用函数/lambda
JavaTypeTester.setSingleAbstractMethodInterface(sami)
s = JavaTypeTester.getSingleAbstractMethodInterface()
_console.log(s == sami)
_console.log(s())
