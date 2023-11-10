package xyz.thisisjames.boulevard.android.podium.destinations.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import xyz.thisisjames.boulevard.android.podium.models.Subject
import javax.inject.Inject
import javax.inject.Singleton



class HomeViewModel {

    var _searchString  = MutableLiveData<String>()

    var searchString :LiveData<String> = _searchString.map { it }

    private val _searchedSubjects = MutableLiveData<List<Subject>?>()

    var  searchedSubjects: LiveData<List<Subject>?> =  _searchedSubjects



    val setSearchString : (sString:String) -> Unit = {
        _searchString.value = it
    }

    val setSubjects: (list : List<Subject>) ->Unit = {
        _searchedSubjects.value = it
    }


    fun getSearchString():String?{
        return searchString.value
    }

}