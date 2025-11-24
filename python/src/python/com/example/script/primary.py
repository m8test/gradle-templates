# 必须使用 `from m8test_java.com.m8test.script.GlobalVariables import xxx` xxx是全局变量名
from m8test_java.com.m8test.script.GlobalVariables import _activity
from m8test_java.com.m8test.script.GlobalVariables import _composeView
from m8test_java.com.m8test.script.GlobalVariables import _console

# from 引入时必须使用相对于src目录的路径
from python.com.example.tool.ToolB import ToolB

toolB = ToolB()
toolB.methodB(_console)

# 使用 Compose UI 创建界面
_composeView.create(
    # 创建一个垂直方向的布局
    lambda slot: slot.Column(
        lambda column: (
            # 设置 Column 修饰符, 使其填满整个屏幕
            column.setModifier(lambda modifier: modifier.fillMaxSize(1.0)),
            # 设置 Column 内部子元素的对齐方式为水平居中对齐
            column.setHorizontalAlignment(
                lambda alignments: alignments.getCenterHorizontally()
            ),
            # 设置 Column 内部子元素在垂直方向的排列方式为居中排列
            column.setVerticalArrangement(
                lambda arrangements: arrangements.getCenter()
            ),
            # 设置 Column 的内容
            column.setContent(
                lambda columnSlot: (
                    # 1. 使用 mutableStateOf 创建可变状态
                    (state := columnSlot.mutableStateOf(0)),
                    # 在 Text 组件中显示点击次数
                    columnSlot.Text(
                        lambda text: (
                            # 2. 使用 trackSingleState 追踪状态变化, 如果状态值改变的话, 该 Composable 会重新组合
                            text.trackSingleState(state),
                            # 3. 使用 state.getValue() 获取状态值
                            text.setText("点击次数" + str(state.getValue())),
                        )
                    ),
                    # 创建一个按钮，点击按钮时修改状态值
                    columnSlot.TextButton(
                        lambda textButton: (
                            # 设置按钮内容显示一个简单的文本
                            textButton.setContent(
                                lambda buttonSlot: buttonSlot.Text(
                                    lambda text: text.setText("点击")
                                )
                            ),
                            textButton.setOnClick(
                                # 4. 使用 state.setValue(newValue) 修改状态值
                                lambda: state.setValue(state.getValue() + 1)
                            ),
                        )
                    ),
                )
            ),
        )
    )
)
# 启动ui界面
_activity.start()
