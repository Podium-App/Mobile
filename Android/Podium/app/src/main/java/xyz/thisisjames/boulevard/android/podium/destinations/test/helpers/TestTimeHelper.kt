package xyz.thisisjames.boulevard.android.podium.destinations.test.helpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.thisisjames.boulevard.android.podium.destinations.test.TestViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TestTimeHelper {

    var QuestionTime = MutableLiveData<Long>();
    val questionTime: LiveData<Long> get() = QuestionTime

    var QuestionMaxTime : Long = 50000;


    init {
        QuestionTime.value = QuestionMaxTime
    }

    val timeState : (long:Long)->String = {
        val date = Date(it)
        val format = SimpleDateFormat("mm:ss", Locale.ENGLISH);
        format.format(date)
    }


    val resetQuestionTime: ()->Unit ={
        QuestionTime.value = QuestionMaxTime
    }

    val updateQuestionTime : () -> Boolean = {
        if (questionTime.value == 0.toLong()){
             resetQuestionTime.invoke()
             false
        }else{
            QuestionTime.value = QuestionTime.value?.minus(1000)
            true
        }
    }


}