# 必须使用相对于src目录的路径
require 'com/example/tool/ToolB'
java_import "android.widget.FrameLayout"
java_import "android.widget.Button"
java_import "android.view.Gravity"
toolB = ToolB.new
toolB.methodB($console)
# 创建需要显示在界面的内容
$androidView.create(true) {
  # @type frameLayout [FrameLayout]
  |frameLayout|
  button = Button.new(frameLayout.getContext)
  button.setText("Hello")
  @num = 1
  button.setOnClickListener { |v|
    @num += 1
    button.setText("Hello #{@num}")
  }
  # java静态内部类以及静态属性需要通过双冒号(::)调用
  layoutParams = FrameLayout::LayoutParams.new(FrameLayout::LayoutParams::WRAP_CONTENT, FrameLayout::LayoutParams::WRAP_CONTENT)
  # 居中显示按钮
  layoutParams.gravity = Gravity::CENTER
  # 添加按钮到界面
  frameLayout.addView(button, layoutParams)
}
# 启动界面
$activity.start