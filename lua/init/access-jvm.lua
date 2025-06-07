-- 引入java类, 必须以 m8test_java 开头, 后面跟 java 类名
local StringBuilder = require("m8test_java.java.lang.StringBuilder")
local JavaTypeTester = require("m8test_java.com.m8test.script.core.impl.JavaTypeTester")
-- 创建java对象, 可以通过 newJavaObject 方法, 实际上会修改为 local sb = StringBuilder("M8Test")
local sb = StringBuilder:newJavaObject("M8Test")
-- 通过':'调用java对象方法
sb:append("Lua")
_console:log(sb:toString())
-- 通过'.'调用java对象属性
_console:log(JavaTypeTester().OBJECT_FIELD)
local System = require("m8test_java.java.lang.System")
-- 通过':'调用java静态方法
_console:log(System:currentTimeMillis())
-- 通过'.'调用静态属性
_console:log(JavaTypeTester.STATIC_FIELD)
-- 引入内部类, 此写法能有代码提示, 需要通过 _N_ 来替换 $ , 因为 $ 在lua中不允许, 下面的代码实际是引入 android.widget.FrameLayout$LayoutParams 内部类
local FrameLayoutLayoutParams = require("m8test_java.android.widget.FrameLayout_N_LayoutParams")
_console:log(FrameLayoutLayoutParams)
-- 实现java非功能性接口
JavaTypeTester:setMultiAbstractMethodInterface({
    setInt = function(i)
        _console:log("setInt" .. i)
    end,
    getInt = function()
        _console:log("getInt")
        return 0
    end,
    new = function() end
})
local mami = JavaTypeTester:getMultiAbstractMethodInterface()
mami:setInt(1234)
_console:log(mami:getInt())
-- 实现java功能新接口, 可以直接传递 function
JavaTypeTester:setSingleAbstractMethodInterface(function()
    _console:log("getInt")
    return 0
end)
local sami = JavaTypeTester:getSingleAbstractMethodInterface()
_console:log(sami:getInt())