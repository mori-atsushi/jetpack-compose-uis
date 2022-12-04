package com.github.moriatsushi.compose.uis.layout

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.text.style.TextOverflow
import jp.co.cyberagent.katalog.group

val layoutGroup = group("Layout") {
    compose("TitleLayout") {
        TitleLayout(
            title = {
                Text(
                    text = "Title",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            },
            button = {
                TextButton(
                    onClick = { /* ... */ }
                ) {
                    Text(text = "Button")
                }
            }
        )
    }
}
