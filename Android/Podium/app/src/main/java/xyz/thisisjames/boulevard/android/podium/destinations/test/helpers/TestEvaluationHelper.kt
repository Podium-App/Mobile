package xyz.thisisjames.boulevard.android.podium.destinations.test.helpers

import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import xyz.thisisjames.boulevard.android.podium.models.Question
import xyz.thisisjames.boulevard.android.podium.models.testResults
import xyz.thisisjames.boulevard.android.podium.models.userResponse
import kotlin.math.floor

class TestEvaluationHelper   {

    lateinit var userResults : testResults;

    var sessionResponses = arrayListOf<userResponse>()

    fun compileResults(exam : List<Question>){

        val examLength =sessionResponses.size
        val attempts = countAttempts()
        val avgtime =computeAverageTime()
        val errors = countErrors()


        val score  = 100 * (examLength-errors)/examLength

        userResults = testResults(examLength,score,attempts,avgtime, errors)
    }


    fun submitResponse (response : userResponse) {
        sessionResponses.add(response)
    }

    fun computeResponseTime(time:Long) : Long{
        //TODO : if we move to make custom times possible, you need to remember to edit this
        return (50000 - time)/1000
    }


    fun countAttempts() : Int  {
        return sessionResponses.filter { it.attempted }.size
    }

    fun computeAverageTime () :Long  {
        var totalTime = sessionResponses.map { it.timetoResponse }.sum()
        return totalTime /sessionResponses.size
    }

    fun countErrors () : Int{
       return sessionResponses.filter { !it.passed }.size
    }

    fun getCount () : Int {
        return  sessionResponses.size
    }
}