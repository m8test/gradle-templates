package com.example.script

import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.example.tool.ToolB
import com.m8test.script.GlobalVariables.*

fun primaryRun() {
    // 这里写代码
    val tool = ToolB()
    tool.methodB(_console)
    _androidView.create(true) {
        val button = Button(context)
        button.text = "Hello"
        var num = 0
        val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        // 居中显示按钮
        lp.gravity = Gravity.CENTER
        // 这里不能使用 lambda, 否则会崩溃
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                v as Button
                v.text = "Hello${num++}"
            }
        })
        // 添加按钮到界面
        addView(button, lp)
    }
    // 显示界面
    _activity.start()
}
// 下面的语句中的 '//-m8test-remove' 在实际编译时会被删除，也就是将会改成 'primaryRun();', 但是不能省略，否则 primaryRun 方法不会被执行
//-m8test-remove primaryRun();
