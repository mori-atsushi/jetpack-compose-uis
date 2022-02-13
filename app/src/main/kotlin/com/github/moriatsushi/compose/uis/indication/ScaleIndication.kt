package com.github.moriatsushi.compose.uis.indication

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/**
 * Creates and remembers a [ScaleIndication] that is an [Indication]
 * to scale the component display at the time of pressed.
 * It can be used instead of [rememberRipple].
 *
 * @param pressedScale scale value while pressed.
 * @Param animationSpec specifies the [AnimationSpec] for scale animation.
 *  The default is a [spring] with bouncy.
 * @param minDuration minimum duration of animation when tapped.
 *  It is used to display the animation even with a short tap.
 */
@Composable
fun rememberScaleIndication(
    pressedScale: Float = 0.88F,
    animationSpec: AnimationSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow,
    ),
    minDuration: Long = 150
): Indication {
    return remember(pressedScale, animationSpec, minDuration) {
        ScaleIndication(pressedScale, animationSpec, minDuration)
    }
}

private class ScaleIndication(
    private val pressedScale: Float,
    private val animationSpec: AnimationSpec<Float>,
    private val minDuration: Long
) : Indication {
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val instance = remember(pressedScale, animationSpec, minDuration) {
            ScaleIndicationInstance(pressedScale, animationSpec, minDuration)
        }
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect {
                when (it) {
                    is PressInteraction.Press -> {
                        instance.start(this)
                    }
                    is PressInteraction.Release,
                    is PressInteraction.Cancel -> {
                        instance.stop()
                    }
                }
            }
        }
        return instance
    }
}

private class ScaleIndicationInstance(
    private val pressedScale: Float,
    private val animationSpec: AnimationSpec<Float>,
    private val minDuration: Long
) : IndicationInstance {
    companion object {
        private const val initialScale = 1.0F
    }

    private var animatable = Animatable(initialScale)
    private var finishEvent = Channel<Unit>(Channel.CONFLATED)

    fun start(scope: CoroutineScope) {
        scope.launch {
            animatable.animateTo(pressedScale, animationSpec)
        }
        scope.launch {
            delay(minDuration)
            finishEvent.receive()
            animatable.animateTo(initialScale, animationSpec)
        }
    }

    fun stop() {
        finishEvent.trySend(Unit)
    }

    override fun ContentDrawScope.drawIndication() {
        val scale = animatable.value
        if (scale != initialScale) {
            drawContext.transform.apply {
                scale(scale)
            }
        }
        drawContent()
    }
}

@Preview
@Composable
fun ScaleIndication_Preview() {
    MaterialTheme {
        Text(
            modifier = Modifier
                .clickable(
                    onClick = {},
                    indication = rememberScaleIndication(),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .background(Color.Blue, RoundedCornerShape(50))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            text = "Button",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}