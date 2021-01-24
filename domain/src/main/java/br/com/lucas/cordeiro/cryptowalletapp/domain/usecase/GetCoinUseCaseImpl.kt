package br.com.lucas.cordeiro.cryptowalletapp.domain.usecase

import br.com.lucas.cordeiro.cryptowalletapp.domain.error.ErrorHandler
import br.com.lucas.cordeiro.cryptowalletapp.domain.helper.Result
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Coin
import br.com.lucas.cordeiro.cryptowalletapp.domain.repository.CoinRepository
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.handleResult
import kotlinx.coroutines.flow.Flow

class GetCoinUseCaseImpl(
    private val coinRepository: CoinRepository,
    private val errorHandler: ErrorHandler,
) : GetCoinUseCase {
    override suspend fun getCoin(): Flow<Result<List<Coin>>> {
        return coinRepository.getCoins().handleResult(errorHandler)
    }
}