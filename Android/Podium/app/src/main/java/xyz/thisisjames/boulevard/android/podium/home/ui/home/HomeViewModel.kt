package xyz.thisisjames.boulevard.android.podium.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.thisisjames.boulevard.android.podium.data.models.Subject

class HomeViewModel : ViewModel() {

    private val _subjects = MutableLiveData<ArrayList<Subject>>().apply {

    }
    val subjects: LiveData<ArrayList<Subject>> = _subjects
}