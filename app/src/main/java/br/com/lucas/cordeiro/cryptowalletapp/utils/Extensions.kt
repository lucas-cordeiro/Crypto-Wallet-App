package br.com.lucas.cordeiro.cryptowalletapp.utils

import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.toPx() : Float{
    val dp = this.value
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        AmbientContext.current.resources.displayMetrics,
    )
}