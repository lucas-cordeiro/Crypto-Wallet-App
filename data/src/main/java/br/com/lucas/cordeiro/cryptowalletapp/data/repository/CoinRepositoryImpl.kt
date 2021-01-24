package br.com.lucas.cordeiro.cryptowalletapp.data.repository

import br.com.lucas.cordeiro.cryptowalletapp.data.R
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Coin
import br.com.lucas.cordeiro.cryptowalletapp.domain.repository.CoinRepository
import br.com.lucas.cordeiro.cryptowalletapp.theme.*
import br.com.lucas.cordeiro.cryptowalletapp.utils.ColorUtils.Companion.toHEX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CoinRepositoryImpl : CoinRepository {
    override suspend fun getCoins(): Flow<List<Coin>> {
        return flowOf(listOf(
            Coin(
                name = "bitcoin",
                icon = R.drawable.ic_bitcoin,
                code = "BTC",
                balance = 872685.85,
                count = 0.5,
                color = yellow400.toHEX(),
            ),
            Coin(
                name = "ethereum",
                icon = R.drawable.ic_ethereum,
                code = "ETH",
                balance = 6005.061,
                count = 0.9,
                color = red600.toHEX(),
            ),
            Coin(
                name = "ripple",
                icon = R.drawable.ic_xrp,
                code = "XRP",
                balance = 147.0,
                count = 100.0,
                color = blue400.toHEX(),
            ),
            Coin(
                name = "litecoin",
                icon = R.drawable.ic_litecoin,
                code = "LTC",
                balance = 11898.032,
                count = 15.8,
                color = indigo400.toHEX()
            ),
            Coin(
                name = "tether",
                icon = R.drawable.ic_tether,
                code = "USDT",
                balance = 1627.56,
                count = 297.0,
                color = green400.toHEX()
            ),
            Coin(
                name = "dash",
                icon = R.drawable.ic_dash,
                code = "DASH",
                balance = 53130.168,
                count = 93.6,
                color = lightBlue400.toHEX()
            ),
            Coin(
                name = "dogecoin",
                icon = R.drawable.ic_dash,
                code = "DOGE",
                balance = 717.74,
                count = 15432.0,
                color = lime400.toHEX()
            ),
        ))
    }
}