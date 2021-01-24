package br.com.lucas.cordeiro.cryptowalletapp.domain.error

interface ErrorHandler {
    suspend fun getError(throwable: Throwable): ErrorEntity
}