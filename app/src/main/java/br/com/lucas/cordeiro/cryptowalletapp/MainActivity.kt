package br.com.lucas.cordeiro.cryptowalletapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollableColumn
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import br.com.lucas.cordeiro.cryptowalletapp.ui.*
import br.com.lucas.cordeiro.cryptowalletapp.utils.currency
import br.com.lucas.cordeiro.cryptowalletapp.utils.format
import br.com.lucas.cordeiro.cryptowalletapp.utils.fromPx
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import java.util.*

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
    val coins =
        listOf(
                Coin(
                        name = "bitcoin",
                        icon = R.drawable.ic_bitcoin,
                        code = "BTC",
                        balance = 872685.85,
                        amount = 0.5,
                        color = yellow400,
                ),
                Coin(
                        name = "ethereum",
                        icon = R.drawable.ic_ethereum,
                        code = "ETH",
                        balance = 6005.061,
                        amount = 0.9,
                        color = red600,
                ),
                Coin(
                        name = "ripple",
                        icon = R.drawable.ic_xrp,
                        code = "XRP",
                        balance = 147.0,
                        amount = 100.0,
                        color = blue400,
                ),
                Coin(
                        name = "litecoin",
                        icon = R.drawable.ic_litecoin,
                            code = "LTC",
                        balance = 11898.032,
                        amount = 15.8,
                        color = indigo400
                ),
                Coin(
                        name = "tether",
                        icon = R.drawable.ic_tether,
                        code = "USDT",
                        balance = 1627.56,
                        amount = 297.0,
                        color = green400
                ),
                Coin(
                        name = "dash",
                        icon = R.drawable.ic_dash,
                        code = "DASH",
                        balance = 53130.168,
                        amount = 93.6,
                        color = lightBlue400
                ),
                Coin(
                        name = "dogecoin",
                        icon = R.drawable.ic_dash,
                        code = "DOGE",
                        balance = 717.74,
                        amount = 15432.0,
                        color = lime400
                ),
        )

    Column() {
        Header(
                balance = coins.sumByDouble { it.balance }
        )
        ConstraintLayout(Modifier
                .fillMaxWidth()
                .weight(1f)
                .fillMaxHeight(1f)
                .background(purpleMedium)
        ) {

            val (gradientTop, gradientBottom, pointLightTop, pointLightBottom, ) = createRefs()

            Canvas(Modifier
                    .preferredSize(150.dp)
                    .constrainAs(pointLightTop) {
                        top.linkTo(parent.top, 24.dp)
                        start.linkTo(parent.start)
                    }
            ){
                drawCircle(
                        brush = Brush.radialGradient(
                                0.0f to Color.White,
                                1f to Color.Transparent,
                                center = Offset(0f, size.height/2),
                                radius = size.height/2,
                        ),
                        center = Offset(0f, size.height/2)
                )
            }

            Canvas(Modifier
                    .preferredSize(150.dp)
                    .constrainAs(pointLightBottom) {
                        bottom.linkTo(parent.bottom, 24.dp)
                        end.linkTo(parent.end)
                    }
            ){
                drawCircle(
                        brush = Brush.radialGradient(
                                0.0f to Color.White,
                                1f to Color.Transparent,
                                center = Offset(size.width, size.height/2),
                                radius = size.height/2,
                        ),
                        center = Offset(size.width, size.height/2)
                )
            }

            ScrollableColumn(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.preferredHeight(24.dp).fillMaxWidth().background(purpleMedium))
                coins.forEach {
                    CoinCard(coin = it)
                }
                Spacer(modifier = Modifier.preferredHeight(24.dp).fillMaxWidth().background(purpleMedium))
            }


            Canvas(Modifier
                    .fillMaxWidth()
                    .preferredHeight(24.dp)
                    .constrainAs(gradientTop) {
                        top.linkTo(parent.top)
                    }){
                drawRect(brush = Brush.verticalGradient(
                        0.0f to purpleMedium,
                        1.0f to purpleMedium.copy(alpha = 0f),
                ))
            }

            Canvas(Modifier
                    .fillMaxWidth()
                    .preferredHeight(24.dp)
                    .constrainAs(gradientBottom) {
                        bottom.linkTo(parent.bottom)
                    }){
                drawRect(brush = Brush.verticalGradient(
                        0.0f to purpleMedium.copy(alpha = 0f),
                        1.0f to purpleMedium,
                ))
            }
        }

        Column(Modifier
                .fillMaxWidth()
                .background(
                        brush = Brush.verticalGradient(
                                0.0f to purpleMedium,
                                1.0f to purpleDark,
                        )
                )
                .preferredHeight(200.dp)
        )
        {
            Text(
                    text = "Atividades",
                    style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Medium,
                    ),
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
        }
    }
}

@Composable
private fun Header(
        balance: Double,
        modifier: Modifier = Modifier
){
    ConstraintLayout(modifier
            .fillMaxWidth()
            .background(
                    brush = Brush.verticalGradient(
                            0.0f to purpleLight,
                            0.9f to purpleMedium,
                    )
            )
            .statusBarsPadding()
    ) {

        val (cardHeader, lightPoint) = createRefs()

        val (height, updateHeight) = remember { mutableStateOf(10) }

        Canvas(Modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .preferredHeight(height.fromPx())
                .constrainAs(lightPoint) {
                    centerTo(cardHeader)
                }
        ){
            drawRect(
                    brush = Brush.radialGradient(
                            0.0f to Color.White,
                            0.7f to Color.Transparent,
                            center = Offset(size.width, size.height),
                            radius = size.height,
                    ),
                    blendMode = BlendMode.Softlight
            )
        }

        CardGradient(
                accentColor = purpleLight,
                modifier = Modifier
                        .padding(16.dp)
                        .constrainAs(cardHeader) {
                            top.linkTo(parent.top)
                        }
                        .onGloballyPositioned { layoutCoordinates ->
                            if (height != layoutCoordinates.size.height)
                                updateHeight(layoutCoordinates.size.height)
                        }
        ) {
            Column(
                    Modifier
                            .fillMaxWidth()
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
                        text = balance.currency(),
                        style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold
                        ),
                )
            }
        }


    }
}

@Composable
private fun CardGradient(
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
        Canvas(Modifier
                .preferredWidth(width
                        .fromPx()
                        .plus(margin * 2))
                .preferredHeight(height
                        .fromPx()
                        .plus(margin * 2))
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

@Composable
private fun CoinCard(
        coin: Coin,
        modifier: Modifier = Modifier
){
    CardGradient(
            accentColor = coin.color,
            drawBorder = true,
            contentBrush = Brush.horizontalGradient(
                    0.0f to purpleMediumLight,
                    0.7f to purpleMediumLight,
                    1.0f to purpleMedium
            ),
            modifier = modifier
    ) {
        Column( Modifier.padding(16.dp)) {
            Row() {
                Text(
                        text = coin.name.capitalize(Locale.getDefault()),
                        style = MaterialTheme.typography.h6.copy(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                )
                Text(
                        text = coin.balance.currency(),
                        style = MaterialTheme.typography.h6.copy(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Medium
                        ),
                )
            }
            Row(Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                CoilImage(
                        data = coin.icon,
                        contentScale = ContentScale.Inside,
                        colorFilter = ColorFilter.tint(coin.color),
                        modifier = Modifier.preferredSize(24.dp)
                )
                Text(
                        text = coin.code.toUpperCase(Locale.getDefault()),
                        style = MaterialTheme.typography.body1.copy(
                                color = onPurple,
                                fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                                .padding(start = 4.dp)
                                .weight(1f)
                                .fillMaxWidth()
                )
                Text(
                        text = "${coin.amount.format(1)} ${coin.code}",
                        style = MaterialTheme.typography.body1.copy(
                                color = onPurple,
                                fontWeight = FontWeight.Medium
                        ),
                )
            }
        }
    }
}

data class Coin(
        var name: String,
        var code: String,
        var icon: Any,
        var balance: Double,
        var amount: Double,
        var color: Color,
)

@Preview(showBackground = true, backgroundColor = 0XFF000000)
@Composable
fun DefaultPreview() {
    CryptoWalletAppTheme(darkTheme = true) {
            Greeting()
    }
}