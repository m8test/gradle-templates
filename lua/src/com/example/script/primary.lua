-- 必须使用相对于src目录的路径
local ToolB = require("com.example.tool.ToolB")
-- 引入 Button 类, 必须以 m8test_java 开头, 后面跟 java 类名
local Button = require("m8test_java.android.widget.Button")
-- 引入内部类, 此写法能有代码提示, 需要通过 _N_ 来替换 $ , 因为 $ 在lua中不允许, 下面的代码实际是引入 android.widget.FrameLayout$LayoutParams 内部类
local FrameLayoutLayoutParams = require("m8test_java.android.widget.FrameLayout_N_LayoutParams")
local Gravity = require("m8test_java.android.view.Gravity")
ToolB.methodB(_console)

_androidView:create(true, function(frameLayout)
    -- 需要注意的是, 这里的 newJavaObject是对应为java的构造方法, 通过gradle构建项目的时候这样使用为了可以有代码提示, 项目构建后会自动把:newJavaObject去掉
    local button = Button:newJavaObject(frameLayout:getContext())
    local num = 0
    button:setText("hello")
    button:setOnClickListener(function(view)
        num = num + 1
        button:setText("hello" .. num)
    end)
    local lp = FrameLayoutLayoutParams:newJavaObject(FrameLayoutLayoutParams.WRAP_CONTENT,
        FrameLayoutLayoutParams.WRAP_CONTENT)
    -- 居中显示按钮
    lp.gravity = Gravity.CENTER
    -- 添加按钮到界面
    frameLayout:addView(button, lp)
end)
_activity:start()
