# 必须使用相对于src目录的路径
require 'com/example/tool/ToolB'
toolB = ToolB.new
toolB.methodB($console)
# 引用 java 类可以通过 M8TestJava 开头, 后面跟全类名，当然包名的第一个首字母要大写
Button = M8testJava::Android::Widget::Button
FrameLayout = M8testJava::Android::Widget::FrameLayout
# 创建需要显示在界面的内容
$androidView.create(true) { |frameLayout|
  button = Button.new(frameLayout.getContext)
  button.setText("Hello")
  @num = 1
  button.setOnClickListener {
    # @type v [M8testJava::Android::View::View]
    |v|
    @num += 1
    button.setText("hi #{@num}")
  }
  # java静态内部类以及静态属性需要通过双冒号(::)调用, 并且需要添加N__前缀, 如果看到 N__开头的就可以判断这个一个静态的变量或者内部类， 这样做的目的是可以在idea中获取代码提示功能
  wrap_content = FrameLayout::N__LayoutParams::N__WRAP_CONTENT
  layout_params = FrameLayout::N__LayoutParams.new(wrap_content, wrap_content)
  # 居中显示按钮, 还可以通过全类名的方式引用java静态属性/内部类或者调用静态方法
  layout_params.gravity = M8testJava::Android::View::Gravity::N__CENTER
  # 添加按钮到界面
  frameLayout.addView(button, layout_params)
}
# 启动界面
$activity.start