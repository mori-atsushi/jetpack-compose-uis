package com.github.moriatsushi.compose.uis.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun TitleLayout(
    title: @Composable () -> Unit,
    button: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Layout(
        modifier = modifier.padding(16.dp),
        content = {
            Box(modifier = Modifier.layoutId("title")) {
                title()
            }
            Box(modifier = Modifier.layoutId("button")) {
                button()
            }
        },
        measurePolicy = { measurables, constraints ->
            val titleMeasurable = measurables.find { it.layoutId == "title" }
                ?: error("title not found")
            val buttonMeasurable = measurables.find { it.layoutId == "button" }
                ?: error("button not found")
            val buttonConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val buttonPlaceable = buttonMeasurable.measure(buttonConstraints)
            val titleConstraints = constraints.copy(
                maxWidth = maxOf(0, constraints.maxWidth - buttonPlaceable.width * 2),
                minHeight = 0,
                minWidth = 0
            )
            val titlePlaceable = titleMeasurable.measure(titleConstraints)

            val width = constraints.maxWidth
            val height = maxOf(
                constraints.minHeight,
                titlePlaceable.height,
                buttonPlaceable.height
            )
            layout(width, height) {
                val space = IntSize(width, height)
                val titleSize = IntSize(titlePlaceable.width, titlePlaceable.height)
                val buttonSize = IntSize(buttonPlaceable.width, buttonPlaceable.height)
                val titleOffset = Alignment.Center.align(titleSize, space, layoutDirection)
                val buttonOffset = Alignment.CenterEnd.align(buttonSize, space, layoutDirection)

                titlePlaceable.place(titleOffset)
                buttonPlaceable.place(buttonOffset)
            }
        }
    )
}
