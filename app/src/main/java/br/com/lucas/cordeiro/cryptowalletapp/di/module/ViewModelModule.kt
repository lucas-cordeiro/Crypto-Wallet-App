package br.com.lucas.cordeiro.cryptowalletapp.di.module

import br.com.lucas.cordeiro.cryptowalletapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}