package com.bitpanda.developertest.model

class Cryptocoin(
    name: String,
    symbol: String,
    id: String,
    logo: String,
    price: Double
): Asset(name, symbol, id, logo, price) {
    override var precision: Int = 4
}