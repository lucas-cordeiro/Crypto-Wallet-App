package br.com.lucas.cordeiro.cryptowalletapp.ui.home.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Payment
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.PaymentType
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.getDayFormatted
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.getHourFormatted
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.currency
import br.com.lucas.cordeiro.cryptowalletapp.theme.onPurple
import br.com.lucas.cordeiro.cryptowalletapp.theme.purpleDark
import br.com.lucas.cordeiro.cryptowalletapp.theme.purpleLight
import br.com.lucas.cordeiro.cryptowalletapp.theme.purpleMedium
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun PaymentsList(
    payments: List<Payment>,
    modifier: Modifier = Modifier,
){
    Column(
        modifier
            .background(
                brush = Brush.verticalGradient(
                    0.0f to purpleMedium,
                    0.4f to purpleMedium,
                    1.0f to purpleDark,
                )
            )
            .navigationBarsPadding()
            .preferredHeight(150.dp)) {
        Text(
            text = "Atividades",
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )

        Box(Modifier.padding(top = 4.dp)) {
            ScrollableColumn() {
                Spacer(modifier = Modifier.preferredSize(12.dp))
                payments.forEach { payment ->
                    PaymentCard(payment = payment, modifier = Modifier.padding(bottom = 24.dp))
                }
                Spacer(modifier = Modifier.preferredSize(12.dp))
            }

            Canvas(
                Modifier
                    .fillMaxWidth()
                    .preferredHeight(12.dp)
                    .align(Alignment.TopCenter)){
                drawRect(brush = Brush.verticalGradient(
                    0.0f to purpleMedium,
                    1.0f to purpleMedium.copy(alpha = 0f),
                ))
            }

            Canvas(
                Modifier
                    .fillMaxWidth()
                    .preferredHeight(12.dp)
                    .align(Alignment.BottomCenter)){
                drawRect(brush = Brush.verticalGradient(
                    0.0f to purpleDark.copy(alpha = 0f),
                    1.0f to purpleDark,
                ))
            }
        }
    }

}

@Composable
private fun PaymentCard(
    payment: Payment,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(horizontal = 16.dp)) {
        Text(
            text = payment.createdDate.getDayFormatted(),
            style = MaterialTheme.typography.body2.copy(
                color = onPurple,
                fontWeight = FontWeight.Medium,
            ),
        )

        Row(Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                imageVector = when (payment.type) {
                    PaymentType.FOOD -> Icons.Rounded.Fastfood
                    PaymentType.EDUCATION -> Icons.Rounded.School
                    PaymentType.GENERIC -> Icons.Rounded.ShoppingCart
                    PaymentType.PARKING -> Icons.Rounded.LocalParking
                    PaymentType.CREDIT -> Icons.Rounded.Payment
                },
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(onPurple),
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        brush = Brush.verticalGradient(
                            0.0f to purpleDark,
                            0.3f to purpleMedium,
                            1.0f to purpleLight,
                        )
                    )
                    .padding(8.dp)
                    .preferredSize(20.dp)
            )
            Column(
                Modifier
                    .padding(horizontal = 24.dp)
                    .weight(1f)
                    .fillMaxWidth()) {
                Text(
                    text = payment.description?:if(payment.credit)"Crédito" else "Débito",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Medium,
                    )
                )
                Text(
                    text = when (payment.type) {
                        PaymentType.FOOD -> "Alimentação"
                        PaymentType.EDUCATION -> "Educação"
                        PaymentType.PARKING -> "Estacionamento"
                        PaymentType.GENERIC -> "Compra"
                        PaymentType.CREDIT -> "Pagamento"
                    },
                    style = MaterialTheme.typography.body2.copy(
                        color = onPurple,
                    )
                )
            }
            Text(
                text = "${if (payment.credit) "+" else "-"} ${payment.amount.currency()}",
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}