package br.com.lucas.cordeiro.cryptowalletapp.domain.helper

import br.com.lucas.cordeiro.cryptowalletapp.domain.error.ErrorEntity


sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val error: ErrorEntity) : Result<T>()
}