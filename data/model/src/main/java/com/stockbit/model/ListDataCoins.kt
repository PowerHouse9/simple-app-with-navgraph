package com.stockbit.model

import com.google.gson.annotations.SerializedName

class ListDataCoins {

    @SerializedName("CoinInfo")
    val CoinInfo: coinInfoData? = null

    @SerializedName("DISPLAY")
    val DISPLAY: displayData? = null

    data class coinInfoData (
        @SerializedName("Name")
        val Name : String,

        @SerializedName("FullName")
        val FullName : String,

        @SerializedName("Internal")
        val Internal : String,

        @SerializedName("Algorithm")
        val Algorithm : String
    )

    data class displayData (
        @SerializedName("USD")
        val USD : usdData

    )

    data class usdData (
        @SerializedName("PRICE")
        val PRICE : String,

        @SerializedName("CHANGE24HOUR")
        val CHANGE24HOUR : String

    )


}