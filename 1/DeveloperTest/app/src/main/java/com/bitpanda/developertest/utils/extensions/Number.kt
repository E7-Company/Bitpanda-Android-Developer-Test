package com.bitpanda.developertest.utils.extensions

fun Double.format(decimals: Int = 2): String {
    return "%.${decimals}f".format(this)
}