package xyz.thisisjames.boulevard.android.podium.dependencies

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import xyz.thisisjames.boulevard.android.podium.Podium
import xyz.thisisjames.boulevard.android.podium.destinations.home.HomeViewModel
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import xyz.thisisjames.boulevard.android.podium.models.Session
import xyz.thisisjames.boulevard.android.podium.models.Subject
import xyz.thisisjames.boulevard.android.podium.network.PodiumApi
import xyz.thisisjames.boulevard.android.podium.room.PreferenceRepository
import xyz.thisisjames.boulevard.android.podium.room.PreferenceRoomDataBase
import xyz.thisisjames.boulevard.android.podium.room.PreferenceViewModel
import xyz.thisisjames.boulevard.android.podium.room.Preferences
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BaseViewModel @Inject constructor() : ViewModel() {

    val preferenceKey = "xyz.thisisjames.boulevard.android.podium.dependencies.User"

    val _allSubjects = MutableLiveData<List<Subject>?>()


    val DOWNLOADSTATUS = MutableLiveData<Boolean>()


    var  subjects: LiveData<List<Subject>?> =  _allSubjects
    var TIMECLOCK : Long = 3000

    var downloadStatus :LiveData<Boolean> = DOWNLOADSTATUS


    var currentUser : Boolean = false


    lateinit var  status : String
    lateinit var repository : PreferenceRepository


    val homeViewModel = HomeViewModel()


    init {
        getAllSubjects();
    }


    fun searchSubjects(string:String){
        homeViewModel.setSearchString.invoke(string)

        viewModelScope.launch {
            homeViewModel.setSubjects.invoke(
                subjects.value!!.filter{
                    it.subjectname.contains(string, true) or it.scope.contains(string, true)
                }
            )

        }
    }


    fun changeStatus( application: Application ) {

        val sharedPref = application.applicationContext.getSharedPreferences(preferenceKey,Context.MODE_PRIVATE) ?: return
        val editor = sharedPref.edit()
        editor.putBoolean("KnownUser", true)
        editor.apply()
        editor.commit()
    }

    fun retrieveStatus( application: Application ): Unit {
        val sharedPref = application.applicationContext.getSharedPreferences(preferenceKey,Context.MODE_PRIVATE) ?: return
        currentUser =  sharedPref.getBoolean("KnownUser",false)
    }

    // network calls
    fun getAllSubjects() {
        viewModelScope.launch {
            try {
                var responseAS = PodiumApi.retrofitService.getAllSubjects();
                var response = responseAS.response
                _allSubjects.value = response.result.resultData
                DOWNLOADSTATUS.value = _allSubjects.value != null
            }catch (e: Exception){
                Log.d("Error retrieving data", e.message.toString())
            }
        }
    }


}