package com.bitpanda.developertest.model

open class Currency (
    var name: String = "",
    var symbol: String = "",
    var id: String = "",
    var logo: String = "",
    open var precision : Int = 0
)