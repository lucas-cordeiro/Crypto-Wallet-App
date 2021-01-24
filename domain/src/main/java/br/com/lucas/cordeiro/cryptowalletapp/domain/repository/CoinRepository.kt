package br.com.lucas.cordeiro.cryptowalletapp.domain.repository

import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getCoins(): Flow<List<Coin>>
}