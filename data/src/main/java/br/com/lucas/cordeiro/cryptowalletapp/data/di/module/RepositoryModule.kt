package br.com.lucas.cordeiro.cryptowalletapp.data.di.module

import br.com.lucas.cordeiro.cryptowalletapp.data.repository.CoinRepositoryImpl
import br.com.lucas.cordeiro.cryptowalletapp.domain.repository.CoinRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CoinRepositoryImpl() as CoinRepository }
}