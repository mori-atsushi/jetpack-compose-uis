package com.github.moriatsushi.compose.uis.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Text field with character max limit.
 * It may be displayed beyond the character limit during predictive conversion.
 * If you want to display the exact number of characters,
 * see [MaxLengthVisualTextField].
 *
 * @param maxLength character max limit
 */
@Composable
fun MaxLengthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    var textFieldValueState by remember {
        mutableStateOf(TextFieldValue(text = value))
    }
    val currentText = textFieldValueState.text.take(maxLength)
    val textFieldValue = if (value != currentText) {
        textFieldValueState.copy(text = value)
    } else {
        textFieldValueState
    }
    val onTextFieldValueChange = { text: TextFieldValue ->
        val isComposing = text.composition != null
        val nextText = text.text.take(maxLength)
        onValueChange(nextText)
        textFieldValueState = if (!isComposing) {
            text.copy(text = nextText)
        } else {
            text
        }
    }
    TextField(
        value = textFieldValue,
        onValueChange = onTextFieldValueChange,
        modifier = modifier,
        label = label
    )
}

@Preview
@Composable
fun MaxLengthTextField_Preview() {
    MaterialTheme {
        var value by remember { mutableStateOf("") }
        val maxLength = 5
        Column {
            Text(
                text = value
            )
            Text(
                text = "(${value.length}/$maxLength)"
            )
            Spacer(modifier = Modifier.height(8.dp))
            MaxLengthTextField(
                value = value,
                onValueChange = { value = it },
                maxLength = 5,
                label = { Text(text = "label") }
            )
        }
    }
}
