package xyz.thisisjames.boulevard.android.podium.destinations.test.helpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import xyz.thisisjames.boulevard.android.podium.models.Session
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TestReviewHelper {

    var questionNumberTime  = MutableLiveData<String>()

    var questionStatus = MutableLiveData<String>()



    var qnt : LiveData<String> = questionNumberTime.map { it }

    var qs : LiveData<String> = questionStatus .map { it }



    val time : (ttr:Long) -> String = {

        val date = Date(it*1000)
        val format = SimpleDateFormat("mm:ss", Locale.ENGLISH);
        format.format(date)
    }



    val setNumberAndTime : (pos:Int, length:Int, ttr:Long) -> Unit = { i: Int, ii: Int, ttr:Long ->
       questionNumberTime.value = "${i+1} of ${ii} | ${time.invoke(ttr)}"
    }


    val setStatus : (bol : Boolean) -> Unit = {
        questionStatus.value = if (it) "Passed" else "Failed"
    }


}