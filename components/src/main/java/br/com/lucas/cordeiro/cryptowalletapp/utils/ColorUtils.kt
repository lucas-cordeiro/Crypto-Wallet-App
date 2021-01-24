package br.com.lucas.cordeiro.cryptowalletapp.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import timber.log.Timber
import kotlin.math.roundToInt

class ColorUtils {
    companion object {
        fun Color.toHEX(): String {
            return try{
                String.format("#%02x%02x%02x", (red * 255).roundToInt(), (green * 255).roundToInt(), (blue * 255).roundToInt())
            }catch (e: Exception){
                "#000000"
            }
        }

        fun String.toColor(): Color {
            return try{
                Color(android.graphics.Color.parseColor(this))
            }catch (e: Exception){
                Color.Black
            }
        }
    }
}