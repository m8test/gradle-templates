# from 引入时必须使用相对于src目录的路径
from m8test_java.android.view.Gravity import Gravity
from m8test_java.android.widget.Button import Button
from m8test_java.android.widget.FrameLayout import FrameLayout
# 必须使用 `from m8test_java.com.m8test.script.GlobalVariables import xxx` xxx是全局变量名
from m8test_java.com.m8test.script.GlobalVariables import _activity
from m8test_java.com.m8test.script.GlobalVariables import _androidView
from m8test_java.com.m8test.script.GlobalVariables import _console
from m8test_java.com.m8test.script.GlobalVariables import _reflectors
from m8test_java.com.m8test.script.GlobalVariables import runOnUiThread
from python.com.example.tool.ToolB import ToolB

toolB = ToolB()
toolB.methodB(_console)


# 用于构建界面的函数
def fn(frame_layout: FrameLayout):
    button = Button(frame_layout.getContext())
    button.setText("Hello")
    # 添加按钮点击事件, 点击后设置按钮文本为Hi
    button.setOnClickListener(lambda _: button.setText("Hi"))
    # 如果不能通过`.`访问静态属性, 那么可以反射获取
    wrap_content = (_reflectors.reflect(FrameLayout.LayoutParams)
                    .getField(None, lambda selector: selector.setName("WRAP_CONTENT")))
    layout_params = FrameLayout.LayoutParams(wrap_content, wrap_content)
    # 居中显示按钮, 访问非内部类静态属性直接通过`.`调用
    layout_params.gravity = Gravity.CENTER
    # 添加视图时必须在安卓UI线程
    runOnUiThread(lambda: frame_layout.addView(button, layout_params))


# 创建需要显示的界面
_androidView.create(False, fn)
# 启动ui界面
_activity.start()
