package br.com.lucas.cordeiro.cryptowalletapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import br.com.lucas.cordeiro.cryptowalletapp.theme.*
import br.com.lucas.cordeiro.cryptowalletapp.ui.home.Home
import br.com.lucas.cordeiro.cryptowalletapp.ui.home.HomeViewModel
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ProvideWindowInsets {
                CryptoWalletAppTheme {
                    Home(viewModel = viewModel)
                }
            }
        }
    }
}