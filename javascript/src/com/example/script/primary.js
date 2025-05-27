// 引入 ToolB 类，相对于src目录的路径
let ToolB = require('com/example/tool/ToolB');
// 创建 ToolB 实例
const toolBInstance = new ToolB();
// 调用 methodB 方法
toolBInstance.methodB($console);
// 创建脚本UI界面
$androidView.create(true, (frameLayout) => {
    let num = 0
    // 创建按钮
    let button = new Packages.android.widget.Button(frameLayout.getContext())
    button.setText("测试按钮")
    button.setOnClickListener(function (view) {
        num++
        button.setText("测试按钮" + num)
    })
    let LP = Packages.android.widget.FrameLayout.LayoutParams
    // 通过 `.` 直接访问java静态属性
    let layoutParams = new LP(LP.WRAP_CONTENT, LP.WRAP_CONTENT)
    // 设置按钮居中显示
    layoutParams.gravity = Packages.android.view.Gravity.CENTER
    // 添加按钮到界面中
    frameLayout.addView(button, layoutParams)
})
// 启动 android 活动用于展示脚本UI
$activity.start()