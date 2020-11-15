package com.bitpanda.developertest.repository

import androidx.lifecycle.MutableLiveData
import com.bitpanda.developertest.model.*
import com.bitpanda.developertest.remote.DummyWebService
import javax.inject.Singleton
import javax.inject.Inject
import kotlin.reflect.KClass

@Singleton
class Repository @Inject constructor() {
    private val webservice = DummyWebService()

    // Get Currency by Symbol
    fun findBySymbol(s: String): Currency? {
        return getCurrencies().find { it.symbol == s }
    }

    // Get Currency by Name
    fun findByName(name: String): Currency? {
        return getCurrencies().find { it.name == name }
    }

    // Get all currencies
    private fun getCurrencies(): List<Currency> {
        return webservice.getCurrencies()
    }

    // Get Wallets sort by Balance and no show Deleted
    private fun getWallets(): List<Wallet> {
        return webservice.getWallets().filter { !it.deleted }.sortedByDescending { it.balance }
    }

    fun getWalletsFlow(): MutableLiveData<List<Wallet>> {
        val walletList: MutableLiveData<List<Wallet>> = MutableLiveData()
        walletList.value = walletSortByType()
        return walletList
    }

    // Sort Wallets by Currency type
    private fun walletSortByType(): List<Wallet>{
        return getWallets().sortedWith(compareBy {
            when (it.currency) {
                is Metal -> 1
                is Cryptocoin -> 0
                is Fiat -> -1
                else -> Integer.MAX_VALUE
            }
        })
    }

    // Get Wallets by Currency Type
    fun <T: Any> getWalletsByCurrency(currency: KClass<T>?): List<Wallet> {
        // Filter by Type
        return when(currency) {
            Cryptocoin::class -> getWallets().filter { it.currency is Cryptocoin }
            Fiat::class -> getWallets().filter { it.currency is Fiat }
            Metal::class -> getWallets().filter { it.currency is Metal }
            else -> walletSortByType()
        }
    }

}