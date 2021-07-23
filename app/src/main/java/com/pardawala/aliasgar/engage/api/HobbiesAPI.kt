package com.pardawala.aliasgar.engage.api

import com.pardawala.aliasgar.engage.data.Hobby
import retrofit2.Response
import retrofit2.http.POST

interface HobbiesAPI {

    @POST("engageservice/api/v1/users/hobbylist")
    suspend fun getHobbies(): Response<List<Hobby>>
}