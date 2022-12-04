# Jetpack Compose UIs
This is a collection of some UIs using [Jetpack Compose](https://developer.android.com/jetpack/compose).
It is built using [Katalog](https://github.com/cyberagent-zemi/katalog).

## [Indication](https://developer.android.com/reference/kotlin/androidx/compose/foundation/Indication)
### [ScaleIndication](https://github.com/Mori-Atsushi/jetpack-compose-uis/blob/main/app/src/main/kotlin/com/github/moriatsushi/compose/uis/indication/ScaleIndication.kt)

It is an Indication to scale the component display at the time of pressed.

https://user-images.githubusercontent.com/13435109/152808326-bd5432be-2c0e-4136-a027-3cb718b09445.mp4

## [TextField](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary?hl=ja#TextField(kotlin.String,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Boolean,kotlin.Boolean,androidx.compose.ui.text.TextStyle,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Function0,kotlin.Boolean,androidx.compose.ui.text.input.VisualTransformation,androidx.compose.foundation.text.KeyboardOptions,androidx.compose.foundation.text.KeyboardActions,kotlin.Boolean,kotlin.Int,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.ui.graphics.Shape,androidx.compose.material.TextFieldColors))
### [MaxLengthTextField](https://github.com/Mori-Atsushi/jetpack-compose-uis/blob/main/app/src/main/kotlin/com/github/moriatsushi/compose/uis/textfield/MaxLengthTextField.kt)

TextField with character max limit.
It may be displayed beyond the character limit during predictive conversion.

https://user-images.githubusercontent.com/13435109/152644684-f8a449c5-7c34-4f5e-ac9f-7775719f9442.mp4

### [MaxLengthVisualTextField](https://github.com/Mori-Atsushi/jetpack-compose-uis/blob/main/app/src/main/kotlin/com/github/moriatsushi/compose/uis/textfield/MaxLengthVisualTextField.kt)

TextField with character max limit.
It is displayed with the exact number of characters even during predictive conversion.

https://user-images.githubusercontent.com/13435109/152644733-626ba3f8-90d6-4991-8c5d-a7ef78be730a.mp4

### [MaxLengthErrorTextField](https://github.com/Mori-Atsushi/jetpack-compose-uis/blob/main/app/src/main/kotlin/com/github/moriatsushi/compose/uis/textfield/MaxLengthErrorTextField.kt)
TextField with character max limit.
Characters that exceed the max limit are displayed in red.

https://user-images.githubusercontent.com/13435109/152644787-0f093f18-194d-4231-b3db-4417b24a98e0.mp4

## Layout
### [TitleLayout](https://github.com/Mori-Atsushi/jetpack-compose-uis/blob/main/app/src/main/kotlin/com/github/moriatsushi/compose/uis/layout/TitleLayout.kt)
Layout composable sample.
Place a button on the far right and a title in the center.

<img src="https://user-images.githubusercontent.com/13435109/205494612-ee2120af-4159-4d4d-aa8d-14d34c1810ff.png" width="320px" />
