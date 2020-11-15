package com.bitpanda.developertest.model

open class Asset (
    name: String,
    symbol: String,
    id: String,
    logo: String,
    var price: Double = 0.0
): Currency(name, symbol, id, logo)
