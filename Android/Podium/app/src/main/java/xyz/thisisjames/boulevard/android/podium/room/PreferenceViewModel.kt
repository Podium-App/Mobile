package xyz.thisisjames.boulevard.android.podium.room

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PreferenceViewModel (private val  application: Application) : AndroidViewModel(application) {


    val status : String
    val repository : PreferenceRepository

    init {
        val dao = PreferenceRoomDataBase.getDatabase(application).preferenceDao()
        repository = PreferenceRepository(dao)
        status = repository.userStatus.value?.get(0)?.prefvalue ?: "Unknown"
    }

    fun update(status:Preferences) = viewModelScope.launch {
        repository.update(status)
    }

}

