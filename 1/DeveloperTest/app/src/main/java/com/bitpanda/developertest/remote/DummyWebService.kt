package com.bitpanda.developertest.remote

import com.bitpanda.developertest.model.*

class DummyWebService {

    // Get wallets by Crytpocoin type
    fun getCryptoWallets(): List<Wallet> {
        return DummyData.wallets.filter { it.currency is Cryptocoin }
    }

    // Get wallets by Metal type
    fun getMetalWallets(): List<Wallet> {
        return DummyData.wallets.filter { it.currency is Metal }
    }

    // Get wallets by Fiat type
    fun getFiatWallets(): List<Wallet> {
        return DummyData.wallets.filter { it.currency is Fiat }
    }

    // Get currencies of Crytocoin type
    fun getCryptocoins(): List<Currency> {
        return DummyData.currencies.filterIsInstance<Cryptocoin>()
    }

    // Get currencies of Metal type
    fun getMetals(): List<Currency> {
        return DummyData.currencies.filterIsInstance<Metal>()
    }

    // Get currencies of Fiat type
    fun getFiats(): List<Currency> {
        return DummyData.currencies.filterIsInstance<Fiat>()
    }

    // Get currencies of Asset type
    fun getAssets(): List<Currency> {
        return DummyData.currencies.filterIsInstance<Asset>()
    }

    // Get all currencies
    fun getCurrencies(): List<Currency> {
        return DummyData.currencies
    }

    // Get all wallets
    fun getWallets(): List<Wallet> {
        return DummyData.wallets
    }

}