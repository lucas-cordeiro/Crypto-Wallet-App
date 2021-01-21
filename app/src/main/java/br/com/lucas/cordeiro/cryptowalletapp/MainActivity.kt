package br.com.lucas.cordeiro.cryptowalletapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorStop
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import br.com.lucas.cordeiro.cryptowalletapp.ui.*
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
        WithConstraints() {
            val width = this.maxWidth
            Column {
                Box(Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Brush.linearGradient(
                        0.0f to purpleMediumLight,
                        6.0f to purpleMedium,
                        start = Offset(width.toPx()/2, 0f),
                        end = Offset(width.toPx()/2.5f, Float.POSITIVE_INFINITY),
                    )).fillMaxWidth()) {
                    Column(Modifier.align(Alignment.CenterStart).padding(start = 20.dp, top = 24.dp, bottom = 48.dp)) {
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
            }
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