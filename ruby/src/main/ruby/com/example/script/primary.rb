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
    button.setText("hi #{@num}")
  }
  # java静态内部类以及静态属性需要通过双冒号(::)调用, 实际上 '.#::' 会被替换为 '::#', 也就是将 '.' 替换为 '::', 这样做的目的是可以在idea中获取代码提示功能
  wrap_content = FrameLayout.#::
  LayoutParams.#::
  WRAP_CONTENT
  layout_params = FrameLayout.#::
  LayoutParams.new(wrap_content, wrap_content)
  # 居中显示按钮, 如果静态属性本身就有代码提示的例如 Gravity::CENTER 就不需要使用 '.#::' , 直接使用 '::' 即可
  layout_params.gravity = Gravity::CENTER
  # 添加按钮到界面
  frameLayout.addView(button, layout_params)
}
# 启动界面
$activity.start