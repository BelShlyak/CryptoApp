package com.sumin.cryptoapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null


) {
    override fun toString(): String {
        return "Datum(coinInfo=$coinInfo)"
    }
}