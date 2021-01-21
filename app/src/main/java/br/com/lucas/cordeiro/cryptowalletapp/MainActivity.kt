package br.com.lucas.cordeiro.cryptowalletapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import br.com.lucas.cordeiro.cryptowalletapp.ui.*
import br.com.lucas.cordeiro.cryptowalletapp.utils.fromPx
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
        Column {
            CardGradient(Modifier.padding(top = 16.dp)) {
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


            CardGradient(
                    accentColor = yellowLight,
                    accentPosition = Offset(0f, Float.POSITIVE_INFINITY),
                    modifier = Modifier.padding(top = 16.dp),
            ) {
                Column(
                        Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 20.dp, top = 24.dp, bottom = 48.dp)
                ) {
                    Text(
                            text = "R$ 38.042,93",
                            style = MaterialTheme.typography.h5.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                            ),
                    )
                }
            }


            CardGradient(
                    modifier = Modifier.padding(top = 16.dp),
            ) {
                Column(
                        Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 20.dp, top = 24.dp, bottom = 48.dp)
                ) {
                    Text(
                            text = "R$ 38.042,93",
                            style = MaterialTheme.typography.h5.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                            ),
                    )
                }
            }


            CardGradient(
                    accentColor = tealLight,
                    modifier = Modifier.padding(top = 16.dp),
            ) {
                Column(
                        Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 20.dp, top = 24.dp, bottom = 48.dp)
                ) {
                    Text(
                            text = "R$ 38.042,93",
                            style = MaterialTheme.typography.h5.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                            ),
                    )
                }
            }
        }
    }
}

@Composable
private fun CardGradient(
    modifier: Modifier = Modifier,
    topLight: Boolean = true,
    topColor: Color = purpleMediumLight,
    topPosition: Offset = Offset(Float.POSITIVE_INFINITY, 0f),
    accentLight: Boolean = true,
    accentColor: Color = purpleMediumLight,
    accentPosition: Offset = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
    content: @Composable BoxScope.() -> Unit,
){
    val margin = remember { 8.dp }

    val (width, updateWidth) = remember { mutableStateOf(10) }
    val (height, updateHeight) = remember { mutableStateOf(10) }

    Box(modifier) {
        Box(Modifier
                .padding(margin)
                .clip(RoundedCornerShape(16.dp))
                .background(
                        Brush.radialGradient(
                                0.0f to if (topLight) topColor.copy(alpha = 0.7f) else purpleMedium,
                                0.7f to purpleMedium.copy(alpha = 1f),
                                center = topPosition,
                                radius = height.toFloat(),
                        )
                )
                .fillMaxWidth()
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
        Box(Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                        Brush.radialGradient(
                                0.0f to if (accentLight) accentColor.copy(alpha = 0.4f) else purpleMedium,
                                1f to purpleMedium.copy(alpha = 0f),
                                center = accentPosition,
                                radius = height.toFloat(),
                        )
                )
                .preferredWidth(width.fromPx().plus(margin*2))
                .preferredHeight(height.fromPx().plus(margin*2))
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0XFF000000)
@Composable
fun DefaultPreview() {
    CryptoWalletAppTheme(darkTheme = true) {
            Greeting()
    }
}