package br.com.lucas.cordeiro.cryptowalletapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import br.com.lucas.cordeiro.cryptowalletapp.ui.*
import br.com.lucas.cordeiro.cryptowalletapp.utils.fromPx
import br.com.lucas.cordeiro.cryptowalletapp.utils.toPx
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ProvideWindowInsets {
                CryptoWalletAppTheme {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Box(
            Modifier
                    .fillMaxSize()
                    .background(
                            brush = Brush.linearGradient(
                                    0.0f to purpleLight,
                                    0.2f to purpleMedium,
                                    1.0f to purpleDark,
                                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                                    end = Offset(0f, Float.POSITIVE_INFINITY),
                            )
                    )
                    .statusBarsPadding()
                    .padding(24.dp)
    ) {
        Box(Modifier.background(purpleDark).fillMaxSize())
        Box(Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .preferredHeight(200.dp)
                .background(Brush.radialGradient(
                        0.0f to Color.White,
                        0.7f to Color.Transparent,
                        center = Offset(Float.POSITIVE_INFINITY, 100.dp.toPx() / 2),
                        radius = 200.dp.toPx(),
                )))

        Box(Modifier
                .padding(top = 400.dp)
                .fillMaxWidth()
                .preferredHeight(200.dp)
                .background(Brush.radialGradient(
                        0.0f to Color.White,
                        0.7f to Color.Transparent,
                        center = Offset(0f, 100.dp.toPx() / 2),
                        radius = 200.dp.toPx(),
                )))
        ScrollableColumn() {

            CardGradient(purpleMedium) {
                Column(
                        Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 20.dp, top = 24.dp, bottom = 48.dp)
                ) {
                    Text(
                            text = "Minha Conta",
                            style = MaterialTheme.typography.body1.copy(
                                    color = onPurple,
                                    fontWeight = FontWeight.Light,
                            ),
                    )
                    Text(
                            text = "R$ 38.042,93",
                            style = MaterialTheme.typography.h5.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                            ),
                    )
                }
            }

            val colors = listOf(
                    red600,
                    pink400,
                    purple200,
                    deepPurple400,
                    indigo400,
                    blue400,
                    lightBlue400,
                    cyan400,
                    teal400,
                    green400,
                    lightGreen400,
                    lime400,
                    yellow400,
                    amber400,
                    orange400,
                    deepOrange400
            )

            colors.forEachIndexed { index, color ->
                CardGradient(color) {
                    Text(
                            text = "R$ $index,93",
                            style = MaterialTheme.typography.h5.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(bottom = 32.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CardGradient(
    accentColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
){
    val margin = remember { 8.dp }

    val (width, updateWidth) = remember { mutableStateOf(10) }
    val (height, updateHeight) = remember { mutableStateOf(10) }

    val backgroundColor = purpleDark

    Box(modifier) {
        Canvas(Modifier
                .preferredWidth(width
                        .fromPx()
                        .plus(margin * 2))
                .preferredHeight(height
                        .fromPx()
                        .plus(margin * 2))
        ){

            drawRect(
                    brush = Brush.horizontalGradient(
                            0.0f to accentColor.copy(alpha = 1f),
                            0.2f to backgroundColor,
                            0.8f to backgroundColor,
                            1f to accentColor.copy(alpha = 1f)
                    ),
                    blendMode = BlendMode.Darken
            )
            drawRoundRect(
                    color = backgroundColor,
                    cornerRadius = CornerRadius(30f, 30f),
                    style = Stroke(width = margin.toPx()),
            )
            drawRect(
                    color = backgroundColor,
                    style = Stroke(width = margin.toPx() / 2f),
            )
        }

        Box(Modifier
                .padding(horizontal = margin)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(backgroundColor)
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

@Preview(showBackground = true, backgroundColor = 0XFF000000)
@Composable
fun DefaultPreview() {
    CryptoWalletAppTheme(darkTheme = true) {
            Greeting()
    }
}