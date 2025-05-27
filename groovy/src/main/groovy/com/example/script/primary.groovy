package com.example.script

import android.view.Gravity
import android.widget.Button
import com.example.tool.ToolB

import static android.widget.FrameLayout.LayoutParams

ToolB tool = new ToolB()
tool.methodB($console)
$androidView.create(true) {
    def button = new Button(it.getContext())
    def num = 0
    button.setText("Hello")
    // 点击按钮时更改按钮文字
    button.setOnClickListener {
        num += 1
        button.setText("Hello" + num)
    }
    def lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    // 设置按钮居中显示
    lp.gravity = Gravity.CENTER
    // 添加按钮到界面
    it.addView(button, lp)
}
$activity.start()