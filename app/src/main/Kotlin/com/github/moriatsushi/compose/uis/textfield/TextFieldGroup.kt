package com.github.moriatsushi.compose.uis.textfield

import jp.co.cyberagent.katalog.group

val textFieldGroup = group("TextFieldGroup") {
    compose("MaxLengthTextField") {
        MaxLengthTextField_Preview()
    }
    compose("MaxLengthVisualTextField") {
        MaxLengthVisualTextField_Preview()
    }
    compose("MaxLengthErrorTextField") {
        MaxLengthErrorTextField_Preview()
    }
}
