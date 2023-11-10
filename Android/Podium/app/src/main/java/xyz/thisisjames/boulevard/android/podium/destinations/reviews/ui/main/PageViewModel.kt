package xyz.thisisjames.boulevard.android.podium.destinations.reviews.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.thisisjames.boulevard.android.podium.models.Options
import xyz.thisisjames.boulevard.android.podium.models.userResponse

class PageViewModel : ViewModel() {

    private val _response = MutableLiveData<userResponse>()
    val response: LiveData<userResponse> = _response

    fun setIndex(index: userResponse) {
        _response.value = index
    }


    val status : ()->Boolean = {
        response.value?.passed ?: false
    }

    val question : ()->String = {
        response.value?.question?.question ?: ""
    }

    val options : () -> List<Options> = {
        response.value!!.question.options
    }

    val illustrator : () -> String ={
        response.value!!.question.illustrator
    }



}