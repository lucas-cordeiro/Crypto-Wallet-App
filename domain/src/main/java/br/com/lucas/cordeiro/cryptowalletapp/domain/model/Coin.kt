package br.com.lucas.cordeiro.cryptowalletapp.domain.model

data class Coin(
    var name: String,
    var code: String,
    var icon: Any,
    var balance: Double,
    var count: Double,
    var color: String,
)