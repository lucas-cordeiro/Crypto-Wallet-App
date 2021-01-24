package br.com.lucas.cordeiro.cryptowalletapp.domain.di.module

import br.com.lucas.cordeiro.cryptowalletapp.domain.usecase.GetCoinUseCase
import br.com.lucas.cordeiro.cryptowalletapp.domain.usecase.GetCoinUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCoinUseCaseImpl(get(), get()) as GetCoinUseCase }
}