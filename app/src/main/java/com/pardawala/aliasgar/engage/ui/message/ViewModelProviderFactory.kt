package com.pardawala.aliasgar.engage.ui.message

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pardawala.aliasgar.engage.repository.HobbyRepository

class ViewModelProviderFactory(
    val app: Application,
    val hobbyRepository: HobbyRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessageViewModel(app, hobbyRepository) as T
    }
}