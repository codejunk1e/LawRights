package io.github.codejunk1e.lawrights.datasource.network

import io.github.codejunk1e.lawrights.datasource.network.requests.LoginRequestBodyDTO
import io.github.codejunk1e.lawrights.datasource.network.responses.AllRightsResponseDTO
import io.github.codejunk1e.lawrights.datasource.network.responses.LoginResponseBodyDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LawRightsService {

    @POST("client_login")
    suspend fun loginUser(@Body body : LoginRequestBodyDTO) : LoginResponseBodyDTO

    @GET("rights")
    suspend fun getAllRights(
        @Query("page") page :Int = 1,
        @Query("records_per_page") records :Int = 38
    ) : AllRightsResponseDTO
}
