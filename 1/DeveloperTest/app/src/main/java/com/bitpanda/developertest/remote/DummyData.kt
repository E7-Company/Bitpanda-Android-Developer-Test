package com.bitpanda.developertest.remote

import com.bitpanda.developertest.model.*

class DummyData {

    companion object {

        private val bitcoin = Cryptocoin(
            name = "Bitcoin",
            symbol = "BTC",
            id = "1",
            price = 9000.0,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/btc.svg"
        )

        private val bitpanda = Cryptocoin(
            name = "Bitpanda Ecosystem Token",
            symbol = "BEST",
            id = "2",
            price = 0.03,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/best.svg"
        )

        private val ripple = Cryptocoin(
            name = "Ripple",
            symbol = "XRP",
            id = "3",
            price = 0.2119,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xrp.svg"
        )

        private val euro = Fiat(
            name = "Euro",
            symbol = "EUR",
            id = "1",
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/fiat/usd.svg"
        )

        private val franc = Fiat(
            name = "Swiss Franc",
            symbol = "CHF",
            id = "2",
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/fiat/chf.svg"
        )

        private val gold: Metal = Metal(
            name = "Gold",
            symbol = "XAU",
            id = "4",
            price = 45.14,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xau.svg",
        )

        private val palladium = Metal(
            name = "Palladium",
            symbol = "XPD",
            id = "5",
            price = 70.40,
            logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xpd.svg"
        )

        val currencies = listOf(
            bitcoin, bitpanda, ripple, euro, franc, gold, palladium
        )

        val wallets = listOf(
            Wallet(
                id = "1",
                name = "Gold Wallet 1",
                balance = 133.729,
                is_default = true,
                deleted = false,
                currency = gold
            ),
            Wallet(
                id = "2",
                name = "Gold Wallet 2",
                balance = 2043.4340,
                is_default = false,
                deleted = false,
                currency = gold
            ),
            Wallet(
                id = "2",
                name = "Test Palladium Wallet",
                balance = 200.0,
                is_default = false,
                deleted = false,
                currency = palladium
            ),
            Wallet(
                id = "1",
                name = "Test BTC Wallet",
                balance = 133.7,
                is_default = false,
                deleted = false,
                currency = bitcoin
            ),
            Wallet(
                id = "2",
                name = "Test BTC Wallet 2",
                balance = 0.0,
                is_default = false,
                deleted = true,
                currency = bitcoin
            ),
            Wallet(
                id = "3",
                name = "Test BEST Wallet",
                balance = 1032.23,
                is_default = false,
                deleted = false,
                currency = bitpanda
            ),
            Wallet(
                id = "4",
                name = "Test Ripple Wallet",
                balance = 2304.04,
                is_default = false,
                deleted = false,
                currency = ripple
            ),
            Wallet(
                id = "1",
                name = "EUR Wallet",
                balance = 400.0,
                is_default = false,
                deleted = false,
                currency = euro
            ),
            Wallet(
                id = "2",
                name = "CHF Wallet",
                balance = 0.0,
                is_default = false,
                deleted = false,
                currency = franc
            )
        )

    }

}