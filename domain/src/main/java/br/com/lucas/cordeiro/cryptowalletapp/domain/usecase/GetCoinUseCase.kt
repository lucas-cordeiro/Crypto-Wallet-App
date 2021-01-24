package br.com.lucas.cordeiro.cryptowalletapp.domain.usecase

import br.com.lucas.cordeiro.cryptowalletapp.domain.helper.Result
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface GetCoinUseCase {
    suspend fun getCoin(): Flow<Result<List<Coin>>>
}