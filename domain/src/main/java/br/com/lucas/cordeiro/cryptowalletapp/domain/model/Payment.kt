package br.com.lucas.cordeiro.cryptowalletapp.domain.model

data class Payment(
    var description: String? = null,
    var amount: Double? = null,
    var type: PaymentType = PaymentType.GENERIC,
    var credit: Boolean = false,
    var createdDate: Long? = null,
)
