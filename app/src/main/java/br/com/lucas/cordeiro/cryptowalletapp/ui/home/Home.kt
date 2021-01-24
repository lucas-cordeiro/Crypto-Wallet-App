package br.com.lucas.cordeiro.cryptowalletapp.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.currency
import br.com.lucas.cordeiro.cryptowalletapp.theme.*
import br.com.lucas.cordeiro.cryptowalletapp.ui.home.list.CoinList
import br.com.lucas.cordeiro.cryptowalletapp.ui.home.list.PaymentsList
import br.com.lucas.cordeiro.cryptowalletapp.utils.fromPx
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun Home(
    viewModel: HomeViewModel,
    modifier : Modifier = Modifier,
){
    val coins = viewModel.coins
    val payments = viewModel.payments

    Column(modifier) {
        Header(
            balance = coins.sumByDouble { it.balance }
        )
        CoinList(
            coins,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .fillMaxHeight(1f)
        )

        PaymentsList(
            payments,
            Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun Header(
    balance: Double,
    modifier: Modifier = Modifier
){
    ConstraintLayout(
        modifier
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

        Canvas(
            Modifier
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
