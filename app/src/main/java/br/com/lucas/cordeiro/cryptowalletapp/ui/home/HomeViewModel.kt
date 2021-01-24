package br.com.lucas.cordeiro.cryptowalletapp.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.cordeiro.cryptowalletapp.domain.helper.Result
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Coin
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Payment
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.PaymentType
import br.com.lucas.cordeiro.cryptowalletapp.domain.usecase.GetCoinUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val getCoinUseCase: GetCoinUseCase,
) : ViewModel() {

    var coins: List<Coin> by mutableStateOf(emptyList())
    var payments: List<Payment> by mutableStateOf(emptyList())

    init {
        collectCoin()
        payments = listOf(
            Payment(
                description = "Vale Sul",
                createdDate = 1611532800,
                type = PaymentType.PARKING,
                credit = false,
                amount = 15.3
            ),
            Payment(
                description = "Starbucks",
                createdDate = System.currentTimeMillis() / 1000,
                type = PaymentType.FOOD,
                credit = false,
                amount = 27.5
            ),
            Payment(
                description = "Mc Donals",
                createdDate = 1611360000,
                type = PaymentType.FOOD,
                credit = false,
                amount = 33.89
            ),
            Payment(
                description = "Alura",
                createdDate = 1609459200,
                type = PaymentType.EDUCATION,
                credit = false,
                amount = 1200.0
            ),
            Payment(
                description = "iFood",
                createdDate = 1608854400,
                type = PaymentType.FOOD,
                credit = false,
                amount = 85.1
            )
        )
    }

    private fun collectCoin(){
        viewModelScope.launch {
            getCoinUseCase.getCoin().collect { result ->
                when(result){
                    is Result.Success -> {
                        Timber.tag("BUG").d("Result: ${result.data}")
                        coins = result.data
                    }
                    is Result.Error -> {

                    }
                }
            }
        }
    }
}