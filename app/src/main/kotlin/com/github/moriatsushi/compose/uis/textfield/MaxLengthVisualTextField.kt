package com.github.moriatsushi.compose.uis.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min

/**
 * Text field with character max limit.
 * It is displayed with the exact number of characters even during predictive conversion.
 * if you want to display all the characters during predictive conversion,
 * see [MaxLengthTextField].
 *
 * @param maxLength character max limit
 */
@Composable
fun MaxLengthVisualTextField(
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
        visualTransformation = MaxLengthVisualTransformation(maxLength),
        modifier = modifier,
        label = label
    )
}

class MaxLengthVisualTransformation(
    private val maxLength: Int
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString(text.text.take(maxLength)),
            maxOffsetMapping
        )
    }

    private val maxOffsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return min(offset, maxLength)
        }

        override fun transformedToOriginal(offset: Int): Int {
            return offset
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MaxLengthVisualTransformation) return false
        if (maxLength != other.maxLength) return false
        return true
    }

    override fun hashCode(): Int {
        return maxLength.hashCode()
    }
}

@Preview
@Composable
fun MaxLengthVisualTextField_Preview() {
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
            MaxLengthVisualTextField(
                value = value,
                onValueChange = { value = it },
                maxLength = 5,
                label = { Text(text = "label") }
            )
        }
    }
}
