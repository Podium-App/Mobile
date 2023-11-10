package xyz.thisisjames.boulevard.android.podium.destinations.onboarding.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import xyz.thisisjames.boulevard.android.podium.R

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = _index.map {
        getText(it)
    }

    val heading: LiveData<String> = _index.map {
            getHeader(it)
    }


    val image:LiveData<Int> = _index.map {
        getImage(it)
    }

    fun setIndex(index: Int) {
        _index.value = index
    }


    fun getHeader(index:Int):String {
        when(index ){
            1 -> return "Get ready for your technical interviews!"
            2 -> return "Standout in your job search on LinkedIn!"
            3 -> return "Double check how much you have learnt!"
            else-> return "Be on the podium for your next test !"
        }
    }

    fun getText(index:Int):String{
        when(index){
            1 -> return "Use podium to enhance your readiness for your technical interviews, you are not ready till you pass out test."
            2 -> return "Optimize your LinkedIn profile with verified skills, take similar skills assessment test on podium to prep for this."
            3 -> return "Learning something new on your own ? Use Podium to check how much you have learnt."
            else-> return "We have curated hundreds of prep test to help you prepare for your next skills assessment test. We replicate the test and test conditions, so you can get one step ahead."
        }
    }

    fun getImage(index:Int):Int{
        when(index  ){
            1 -> return R.drawable.onboarding1
            2 -> return R.drawable.onboarding2
            3 -> return R.drawable.onboarding3
            else-> return R.drawable.onboarding4
        }
    }


}