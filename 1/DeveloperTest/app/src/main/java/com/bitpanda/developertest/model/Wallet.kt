package com.bitpanda.developertest.model

class Wallet (
    var id: String = "",
    var name: String = "",
    var balance: Double = 0.0,
    var is_default: Boolean = false,
    var deleted: Boolean = false,
    var currency: Currency
) {

    fun reduceBalance(amount: Double) {
        this.balance -= amount
    }


    fun addBalance(amount: Double) {
        this.balance += amount
    }
}