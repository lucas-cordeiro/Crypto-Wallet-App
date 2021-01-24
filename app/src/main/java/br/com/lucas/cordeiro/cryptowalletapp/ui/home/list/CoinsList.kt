package br.com.lucas.cordeiro.cryptowalletapp.ui.home.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Coin
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.currency
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.format
import br.com.lucas.cordeiro.cryptowalletapp.theme.onPurple
import br.com.lucas.cordeiro.cryptowalletapp.theme.purpleMedium
import br.com.lucas.cordeiro.cryptowalletapp.theme.purpleMediumLight
import br.com.lucas.cordeiro.cryptowalletapp.ui.home.CardGradient
import br.com.lucas.cordeiro.cryptowalletapp.utils.ColorUtils.Companion.toColor
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*

@Composable
fun CoinList(
    coins: List<Coin>,
    modifier: Modifier = Modifier
){
    ConstraintLayout(
        modifier
            .background(purpleMedium)
            .padding(bottom = 8.dp)
    ) {

        val (gradientTop, gradientBottom, pointLightTop, pointLightBottom, ) = createRefs()

        Canvas(
            Modifier
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

        Canvas(
            Modifier
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
            Spacer(modifier = Modifier
                .preferredHeight(24.dp)
                .fillMaxWidth()
                .background(purpleMedium))
            coins.forEach {
                CoinCard(coin = it)
            }
            Spacer(modifier = Modifier
                .preferredHeight(24.dp)
                .fillMaxWidth()
                .background(purpleMedium))
        }


        Canvas(
            Modifier
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

        Canvas(
            Modifier
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
}

@Composable
private fun CoinCard(
    coin: Coin,
    modifier: Modifier = Modifier
){
    val coinColor = remember { coin.color.toColor() }
    CardGradient(
        accentColor = coinColor,
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
                    colorFilter = ColorFilter.tint(coinColor),
                    modifier = Modifier.preferredSize(24.dp)
                )
                Text(
                    text = coin.code.toUpperCase(Locale.getDefault()),
                    style = MaterialTheme.typography.body1.copy(
                        color = onPurple,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .weight(1f)
                        .fillMaxWidth()
                )
                Text(
                    text = "${coin.count.format(1)} ${coin.code}",
                    style = MaterialTheme.typography.body1.copy(
                        color = onPurple,
                        fontWeight = FontWeight.Medium
                    ),
                )
            }
        }
    }
}
