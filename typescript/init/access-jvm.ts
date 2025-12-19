// 1. 创建 StringBuilder
// 推荐使用 const，因为引用不会变
const sb = new Packages.java.lang.StringBuilder("M8Test");

// 2. 访问内部类
// 注意：在 TypeScript 中直接通过点号访问即可，编译器会根据 .d.ts 解析
const LP = Packages.android.widget.FrameLayout.LayoutParams;
$console.log("LayoutParams class", LP);

// 3. 调用 Java 对象方法
sb.append("Javascript");
$console.log(sb.toString());

// 4. 定义类引用别名
// 使用 import ... = ... 是 TypeScript 特有的别名语法，既可以当值用，也可以当类型用
// 或者直接用 const JavaTypeTester = ... 也可以
const JavaTypeTester = Packages.com.m8test.script.core.impl.JavaTypeTester;

// 5. 调用 Java 对象属性
// 注意：如果是 getter/setter 自动生成的属性，TS 中通常直接访问
$console.log(new JavaTypeTester().OBJECT_FIELD);

// 6. 调用 Java 静态方法
$console.log(Packages.java.lang.System.currentTimeMillis());

// 7. 调用 Java 静态属性
$console.log(JavaTypeTester.STATIC_FIELD);

// 8. 实现 Java 非功能性接口 (多个抽象方法)
JavaTypeTester.setMultiAbstractMethodInterface({
    // 为参数 n 添加类型注解 'number'
    setInt: (n: number) => {
        $console.log("setInt " + n);
    },
    getInt: () => {
        $console.log("getInt");
        return 0;
    }
});

const mami = JavaTypeTester.getMultiAbstractMethodInterface();
mami.setInt(1234);
$console.log(mami.getInt());

// 9. 实现 Java 功能性接口 (Single Abstract Method)
// 这里保留了你偏好的对象字面量写法，这种写法对 IDE 推断更友好
JavaTypeTester.setSingleAbstractMethodInterface({
    getInt: () => {
        return 0;
    }
});

const sami = JavaTypeTester.getSingleAbstractMethodInterface();
$console.log(sami.getInt());