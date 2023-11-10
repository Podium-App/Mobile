package xyz.thisisjames.boulevard.android.podium.destinations.test.helpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.thisisjames.boulevard.android.podium.destinations.test.TestViewModel
import xyz.thisisjames.boulevard.android.podium.models.Question
import xyz.thisisjames.boulevard.android.podium.models.Session

class TestNavigationHelper constructor() {


    //indicator for verifying when you have reached the last question
    var testIsFinish = false;

    private var questionNumber = MutableLiveData<Int>()
    var QuestionNumber : LiveData<Int> = questionNumber


    private var currentQuestion = MutableLiveData<Question>()
    val CurrentQuestion : LiveData<Question>  get() =  currentQuestion;


    init {
        questionNumber.value = 0
    }


    fun loadQuestion(exam :List<Question>){
        currentQuestion.value = QuestionNumber.value?.let {
            exam.get( it )
        }
    }

    val moveQuestionUp : (testLength :Int) ->Unit = {
        questionNumber.value = questionNumber.value?.plus(1)
        if (questionNumber.value!! == it-1){
            testIsFinish = true
        }
    }


    val isTestFinished : (testLength :Int) -> Boolean = {
        QuestionNumber.value == it
    }



}