package com.bitpanda.developertest.utils

import com.bitpanda.developertest.model.Cryptocoin
import com.bitpanda.developertest.model.Fiat
import com.bitpanda.developertest.model.Metal

object Constants {
    const val euro = "€"
    val types = listOf(Pair(null, "All"), Pair(Cryptocoin::class, "Cryptocoins"), Pair(Fiat::class, "Fiats"), Pair(Metal::class, "Metals"))
}