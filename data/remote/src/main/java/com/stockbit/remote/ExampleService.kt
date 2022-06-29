package com.stockbit.remote

import com.stockbit.model.ExampleModel
import com.stockbit.model.LoadDataCryptoModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ExampleService {

    @GET("data/top/totaltoptiervolfull")
    fun loadData(
        @Query("limit") limit : Int,
        @Query("tsym") tsym : String
    ): Observable<LoadDataCryptoModel>

}