package com.example.script;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.example.tool.ToolB;
import kotlin.jvm.functions.Function1;

import static com.m8test.script.GlobalVariables.*;

class MyScript {
    public static void run() {
        // 这里写代码
        ToolB tool = new ToolB();
        tool.methodB($console);
        // Function1 不能使用泛型, 直接去除即可, 否则编译错误
        $androidView.create(true, new Function1() {
            @Override
            public Object invoke(Object o) {
                // 强制类型转换
                FrameLayout frameLayout = (FrameLayout) o;
                // 创建按钮对象
                Button button = new Button(frameLayout.getContext());
                button.setText("Hello");
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                // 设置按钮居中显示
                layoutParams.gravity = Gravity.CENTER;
                // 设置按钮监听器, 每点击一次数字自增1
                button.setOnClickListener(new View.OnClickListener() {
                    private int num = 0;

                    @Override
                    public void onClick(View v) {
                        Button button = (Button) v;
                        num++;
                        button.setText("Hello" + num);
                    }
                });
                frameLayout.addView(button, layoutParams);
                return null;
            }
        });
        // 启动界面
        $activity.start();
    }
}