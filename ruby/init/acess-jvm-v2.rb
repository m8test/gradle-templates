# encoding: utf-8
# 如果源代码中包含中文，那么就需要添加上面的编码语句
# 通过 M8testJava::Pkg1::Pkg2::...::PkgN::ClassName 访问 Java 类, 注意包名首字母需要大写
StringBuilder = M8testJava::Java::Lang::StringBuilder
# 创建java对象使用new
sb = StringBuilder.new("M8Test")
# 调用java对象的方法使用 .
sb.append("Ruby")
# 这里输出M8TestRuby
$console.log(sb)
FrameLayout = M8testJava::Android::Widget::FrameLayout
# java静态内部类以及静态常量需要通过双冒号(::)调用, 并且需要添加N__前缀, 如果看到 N__开头的就可以判断这个一个静态的变量或者内部类， 这样做的目的是可以在idea中获取代码提示功能
wrap_content = FrameLayout::N__LayoutParams::N__WRAP_CONTENT
$console.log(wrap_content)
# 你还可以一步到位，使用全类名然后后面使用::访问对应的静态方法/属性/内部类
gravity = M8testJava::Android::View::Gravity::N__CENTER
$console.log(gravity)
JavaTypeTester = M8testJava::Com.M8test::Script::Core::Impl::JavaTypeTester
# 调用java对象属性可以使用 .
$console.log(JavaTypeTester.new.OBJECT_FIELD)
# 调用java静态方法使用 :: 并添加 N__ 前缀
$console.log(M8testJava::Java::Lang::System::N__currentTimeMillis())
# 非final静态属性通过.调用. 并添加 N__ 前缀
$console.log(JavaTypeTester.N__STATIC_FIELD)
# 实现java非函数式接口（有多个方法的接口）
class MultiAbstractMethodInterfaceImpl
  include JavaTypeTester::N__MultiAbstractMethodInterface

  def setInt(i)
    $console.log("setInt #{i}")
  end

  def getInt()
    $console.log("getInt")
    0
  end
end

JavaTypeTester::N__setMultiAbstractMethodInterface(MultiAbstractMethodInterfaceImpl.new)
mami = JavaTypeTester::N__getMultiAbstractMethodInterface
mami.setInt(1234)
$console.log(mami.getInt)
# 实现java功能性接口(函数式接口)
JavaTypeTester::N__setSingleAbstractMethodInterface {
  $console.log("getInt")
  0
}
sami = JavaTypeTester::N__getMultiAbstractMethodInterface
$console.log(sami.getInt)