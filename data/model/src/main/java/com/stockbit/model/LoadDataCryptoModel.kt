package com.stockbit.model

import com.google.gson.annotations.SerializedName

class LoadDataCryptoModel {

    @SerializedName("Type")
    val Type: Int? = null

    @SerializedName("Message")
    val Message: String? = null

    @SerializedName("Data")
    val Data: List<ListDataCoins>? = null

}