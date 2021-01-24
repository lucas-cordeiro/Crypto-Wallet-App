package br.com.lucas.cordeiro.cryptowalletapp.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import br.com.lucas.cordeiro.cryptowalletapp.theme.purpleLight
import br.com.lucas.cordeiro.cryptowalletapp.theme.purpleMedium
import br.com.lucas.cordeiro.cryptowalletapp.utils.fromPx

@Composable
fun CardGradient(
    accentColor: Color,
    modifier: Modifier = Modifier,
    contentBrush: Brush = Brush.linearGradient(
        0.0f to purpleLight.copy(alpha = 0.7f),
        0.2f to purpleLight,
        0.8f to purpleMedium,
        1.0f to purpleMedium.copy(alpha = 0.7f),
        start = Offset(Float.POSITIVE_INFINITY, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY),
    ),
    drawBorder: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
){
    val margin = remember { 8.dp }

    val (width, updateWidth) = remember { mutableStateOf(10) }
    val (height, updateHeight) = remember { mutableStateOf(10) }

    val backgroundColor = if(drawBorder) purpleMedium else Color.Transparent

    Box(modifier) {
        Canvas(
            Modifier
                .preferredWidth(
                    width
                        .fromPx()
                        .plus(margin * 2)
                )
                .preferredHeight(
                    height
                        .fromPx()
                        .plus(margin * 2)
                )
        ){
            drawRoundRect(
                brush = Brush.horizontalGradient(
                    0.0f to accentColor.copy(alpha = 1f),
                    0.2f to backgroundColor,
                    0.8f to backgroundColor,
                    1f to accentColor.copy(alpha = 1f)
                ),
                cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx()),
                blendMode = BlendMode.Darken
            )

            if(drawBorder){
                drawRoundRect(
                    color = backgroundColor,
                    cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx()),
                    style = Stroke(width = margin.toPx()),
                )
                drawRect(
                    color = backgroundColor,
                    style = Stroke(width = margin.toPx() / 2f),
                )
            }
        }

        Box(Modifier
            .padding(horizontal = margin)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = contentBrush
            )
            .onGloballyPositioned { layoutCoordinates ->
                if (width != layoutCoordinates.size.width)
                    updateWidth(layoutCoordinates.size.width)

                if (height != layoutCoordinates.size.height)
                    updateHeight(layoutCoordinates.size.height)
            }
            .align(Alignment.Center)
        ) {
            content()
        }

    }
}
