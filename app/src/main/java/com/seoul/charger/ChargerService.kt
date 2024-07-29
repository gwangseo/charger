package com.seoul.charger

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChargerService {
    @GET("5251584a527061723937616173646f/json/citydata/1/5/{location}")
    suspend fun getChargers(@Path("location") location: String = "광화문·덕수궁"): Response<ChargerResponse>
}
