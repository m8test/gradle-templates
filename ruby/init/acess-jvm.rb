# 通过java_import导入java类
java_import 'java.lang.StringBuilder'
java_import 'java.lang.System'
java_import "com.m8test.script.core.impl.JavaTypeTester"
# 通过 new 创建 java 对象
sb = StringBuilder.new("M8Test")
# 通过.调用java对象方法
sb.append("Ruby")
jtt = JavaTypeTester.new
# 通过.调用java对象属性
$console.log(jtt.OBJECT_FIELD)
# 通过.调用java静态方法
$console.log(System.currentTimeMillis)
# 非final静态属性通过.调用
$console.log(JavaTypeTester.STATIC_FIELD)
java_import "android.os.Build"
# final静态属性通过 :: 调用
$console.log(Build::BRAND)
# 实现java非函数式接口
class MultiAbstractMethodInterfaceImpl
  include JavaTypeTester.#::
  MultiAbstractMethodInterface

  def setInt(i)
    $console.log("setInt #{i}")
  end

  def getInt()
    $console.log("getInt")
    0
  end
end

JavaTypeTester.setMultiAbstractMethodInterface(MultiAbstractMethodInterfaceImpl.new)
mami = JavaTypeTester.getMultiAbstractMethodInterface
mami.setInt(1234)
$console.log(mami.getInt)
# 实现java功能行接口
JavaTypeTester.setSingleAbstractMethodInterface {
  $console.log("getInt")
  0
}
sami = JavaTypeTester.getMultiAbstractMethodInterface
$console.log(sami.getInt)