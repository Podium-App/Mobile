package xyz.thisisjames.boulevard.android.podium.destinations.test

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import xyz.thisisjames.boulevard.android.podium.destinations.test.helpers.TestEvaluationHelper
import xyz.thisisjames.boulevard.android.podium.destinations.test.helpers.TestNavigationHelper
import xyz.thisisjames.boulevard.android.podium.destinations.test.helpers.TestReviewHelper
import xyz.thisisjames.boulevard.android.podium.destinations.test.helpers.TestTimeHelper
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import xyz.thisisjames.boulevard.android.podium.models.Question
import xyz.thisisjames.boulevard.android.podium.models.Session
import xyz.thisisjames.boulevard.android.podium.models.testResults
import xyz.thisisjames.boulevard.android.podium.models.userResponse
import xyz.thisisjames.boulevard.android.podium.network.PodiumApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.Delegates


@Singleton
open class TestViewModel @Inject constructor() : ViewModel() {


    lateinit var subjectname: String

    var length:Int  = 0

    var _testSession  = MutableLiveData<Session>()

    var testSession :LiveData<Session> = _testSession.apply {
        value
    }

    var downloadTimer:Long = 1000;

    var downloadStatus = arrayOf("fetching test data","configuring timer","caching test data","Hmmm! your internet is not giving.")

    var testIsReady = false



    var testTimeHelper = TestTimeHelper()

    var testNavigationHelper = TestNavigationHelper()

    var testEvaluationHelper = TestEvaluationHelper()

    var testReviewHelper = TestReviewHelper()

    fun setupReviewStats (pos:Int){
        val ur = testEvaluationHelper.sessionResponses[pos]

        testReviewHelper.setNumberAndTime.invoke(pos,length,ur.timetoResponse)
        testReviewHelper.setStatus(ur.passed)
    }


    fun submitResponse(response:userResponse) : Unit {
        testEvaluationHelper.submitResponse(response);
        testTimeHelper.resetQuestionTime.invoke()
    }


    fun nextQuestion():Boolean{
        if (testNavigationHelper.isTestFinished.invoke(length)){
            return true
        }
        testNavigationHelper.loadQuestion(testSession.value!!.result.resultData.exam)
        testNavigationHelper.moveQuestionUp.invoke(length)
        return false
    }


    fun moveToResults():Unit{
        viewModelScope.launch {
            testEvaluationHelper.compileResults(testSession.value!!.result.resultData.exam)
        }
    }

    //initialize all that needs to be initialized
    fun createSession(choice:ExamRequest):Unit{

        subjectname = choice.subjectname
        length = choice.length

        testTimeHelper = TestTimeHelper()

        testNavigationHelper = TestNavigationHelper()

        testEvaluationHelper = TestEvaluationHelper()


        getSession();


    }


    //retrieve questions from the server
    fun getSession() {
        viewModelScope.launch {
            try{
                val response = PodiumApi.
                                retrofitService.
                                getSession(
                                    ExamRequest(
                                     subjectname , length
                                    )
                                )

                testIsReady = response.status == 200
                _testSession.value = response

            }catch (e:Exception){
                //Log.d("Response",e.message.toString())
                downloadStatus[3] = e.message.toString()
            }

        }

    }





}