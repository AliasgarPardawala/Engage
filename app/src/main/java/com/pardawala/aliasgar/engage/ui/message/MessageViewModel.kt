package com.pardawala.aliasgar.engage.ui.message

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pardawala.aliasgar.engage.MyApplication
import com.pardawala.aliasgar.engage.data.Hobby
import com.pardawala.aliasgar.engage.repository.HobbyRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MessageViewModel(
    app: Application,
    val hobbyRepository: HobbyRepository
) : AndroidViewModel(app) {

    val hobbies: MutableLiveData<List<Hobby>> = MutableLiveData()

    private val hobbyEventChannel = Channel<HobbyEvent>()
    val hobbyEvent = hobbyEventChannel.receiveAsFlow()

    init {
        getHobbies()
    }

    private fun getHobbies() = viewModelScope.launch {
        safeGetHobbies()
    }

    private suspend fun safeGetHobbies() {
        if(hasInternetConnection()) {
            val response = hobbyRepository.getHobbies()
            if(response.isSuccessful) {
                hobbies.postValue(response.body())
            }
            else {
                hobbyEventChannel.send(HobbyEvent.ResponseError)
            }
        }
        else {
            hobbyEventChannel.send(HobbyEvent.NoInternetConnection)
        }
    }

    fun hasInternetConnection() : Boolean {
        val connectivityManager = getApplication<MyApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return  when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

    sealed class HobbyEvent {
        object ResponseError : HobbyEvent()
        object NoInternetConnection : HobbyEvent()
    }
}