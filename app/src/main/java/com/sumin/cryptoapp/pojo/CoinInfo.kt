package com.sumin.cryptoapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CoinInfo(
    @SerializedName("Name")
    @Expose
    val name: String? = null,
) {
    override fun toString(): String {
        return "CoinInfo(name=$name)"
    }
}