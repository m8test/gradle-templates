package com.example.script

import com.example.tool.ToolB

ToolB tool = new ToolB()
tool.methodB($logger)
$composeView.create { slot ->
    def state = slot.remember { slot.mutableStateOf(0) }
    slot.Column { column ->
        column.setModifier { modifier -> modifier.fillMaxSize(1.0f) }
        column.setHorizontalAlignment { alignments -> alignments.getCenterHorizontally() }
        column.setVerticalArrangement { arrangements -> arrangements.getCenter() }
        column.setContent { content ->
            content.Text { text -> text.setText("点击次数: " + state.getValue()) }
            content.TextButton { button ->
                button.setContent { row -> row.Text { text -> text.setText("点击我") } }
                button.setOnClick { state.setValue(state.getValue() + 1) }
            }
        }
    }
}
$activity.start()
