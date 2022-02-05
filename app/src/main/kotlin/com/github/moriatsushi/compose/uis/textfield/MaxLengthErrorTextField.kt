package com.github.moriatsushi.compose.uis.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Text field with character max limit.
 * Characters that exceed the max limit are displayed in red.
 * If you want to prevent input exceeding the limit of characters,
 * please see [MaxLengthTextField] or [MaxLengthVisualTextField].
 *
 * @param maxLength character max limit
 */
@Composable
fun MaxLengthErrorTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = MaxLengthErrorTransformation(maxLength),
        modifier = modifier,
        label = label,
        isError = value.length > maxLength
    )
}

class MaxLengthErrorTransformation(
    private val maxLength: Int
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString(
                text = text.text,
                spanStyles = if (text.length > maxLength) {
                    listOf(AnnotatedString.Range(errorStyle, maxLength, text.length))
                } else {
                    emptyList()
                }
            ),
            OffsetMapping.Identity
        )
    }

    private val errorStyle = SpanStyle(color = Color.Red)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MaxLengthErrorTransformation) return false
        if (maxLength != other.maxLength) return false
        return true
    }

    override fun hashCode(): Int {
        return maxLength.hashCode()
    }
}

@Preview
@Composable
fun MaxLengthErrorTextField_Preview() {
    MaterialTheme {
        var value by remember { mutableStateOf("") }
        val maxLength = 5
        val isError = value.length > maxLength
        Column {
            Text(
                text = value
            )
            Text(
                text = "(${value.length}/$maxLength)",
                color = if (isError) Color.Red else Color.Unspecified
            )
            Spacer(modifier = Modifier.height(8.dp))
            MaxLengthErrorTextField(
                value = value,
                onValueChange = { value = it },
                maxLength = 5,
                label = { Text(text = "label") }
            )
        }
    }
}
