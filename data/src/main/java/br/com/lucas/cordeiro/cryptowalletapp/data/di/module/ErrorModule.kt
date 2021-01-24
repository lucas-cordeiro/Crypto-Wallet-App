package br.com.lucas.cordeiro.cryptowalletapp.data.di.module

import br.com.lucas.cordeiro.cryptowalletapp.data.helper.ErrorHandlerImpl
import br.com.lucas.cordeiro.cryptowalletapp.domain.error.ErrorHandler
import org.koin.dsl.module

val errorModule = module {
    factory { ErrorHandlerImpl() as ErrorHandler}
}