package br.com.lucas.cordeiro.cryptowalletapp.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucas.cordeiro.cryptowalletapp.domain.helper.Result
import br.com.lucas.cordeiro.cryptowalletapp.domain.model.Coin
import br.com.lucas.cordeiro.cryptowalletapp.domain.usecase.GetCoinUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val getCoinUseCase: GetCoinUseCase,
) : ViewModel() {

    var coins: List<Coin> by mutableStateOf(emptyList())

    init {
        collectCoin()
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