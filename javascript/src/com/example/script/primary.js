// 引入 ToolB 类，相对于src目录的路径
let ToolB = require('com/example/tool/ToolB');
// 创建 ToolB 实例
const toolBInstance = new ToolB();
// 调用 methodB 方法
toolBInstance.methodB($console);
$composeView.create((slot) => {
    // 1. 创建一个状态
    let state = slot.mutableStateOf(0)
    // 创建一个垂直布局
    slot.Column((column) => {
        // 设置垂直布局修饰器
        column.setModifier((m) => {
            // 设置垂直布局填充满屏幕
            m.fillMaxSize(1.0)
        })
        // 水平居中对齐元素
        column.setHorizontalAlignment((alignments) => { return alignments.getCenterHorizontally() })
        // 垂直居中排列元素
        column.setVerticalArrangement((arrangements) => { return arrangements.getCenter() })
        column.setContent((columnSlot) => {
            columnSlot.Text((text) => {
                // 2. 追踪状态变化, 当状态变化时，文本会重新组合
                text.trackSingleState(state)
                // 3. 使用状态值设置文本内容
                text.setText("点击次数: " + state.getValue())
            })
            columnSlot.TextButton((button) => {
                // 设置按钮显示的内容
                button.setContent((buttonSlot) => {
                    // 设置按钮文本
                    buttonSlot.Text((text) => {
                        text.setText("点击我")
                    })
                })
                button.setOnClick(() => {
                    // 4. 点击按钮时，更新状态值
                    state.setValue(state.getValue() + 1)
                })
            })
        })
    })
})
// 启动 android 活动用于展示脚本UI
$activity.start()