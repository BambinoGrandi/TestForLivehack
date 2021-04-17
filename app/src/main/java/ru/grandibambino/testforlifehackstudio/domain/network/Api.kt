package ru.grandibambino.testforlifehackstudio.domain.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("test.php")
    fun getAllCompanyAsync(): Deferred<Response<JsonArray>>

    @GET("test.php")
    fun getCompanyByIdAsync(
        @Query("id") id: String
    ): Deferred<Response<JsonArray>>

}