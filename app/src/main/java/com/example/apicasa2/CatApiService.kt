package com.example.apicasa2

import retrofit2.http.GET

interface CatApiService {

    @GET("breeds")
    suspend fun getListadoRazas():List<RazaGato>

}