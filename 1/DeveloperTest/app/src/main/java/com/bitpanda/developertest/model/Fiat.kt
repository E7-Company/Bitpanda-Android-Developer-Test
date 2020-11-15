package com.bitpanda.developertest.model

class Fiat (
    name: String,
    symbol: String,
    id: String,
    logo: String
): Currency(name, symbol, id, logo)  {
    override var precision: Int = 2
}