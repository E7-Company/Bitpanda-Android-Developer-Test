package com.bitpanda.developertest.model

class Metal (
    name: String,
    symbol: String,
    id: String,
    logo: String,
    price: Double
): Asset(name, symbol, id, logo, price) {
    override var precision: Int = 3
}