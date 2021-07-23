package com.pardawala.aliasgar.engage.repository

import com.pardawala.aliasgar.engage.api.RetrofitInstance

class HobbyRepository {

    suspend fun getHobbies() =
        RetrofitInstance.api.getHobbies()

}