package xyz.thisisjames.boulevard.android.podium.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseAS (
    @Json(name = "response") val response:AllSubjects,
)

// response model for allsubjects endpoint
@JsonClass(generateAdapter = true)
data class AllSubjects (
    val code:String,
    val message:String,
    val moreInfo:String,
    val lang:String,
    val status:Int,
    @Json(name = "result")  val result:ResultAllSubjects
)



// response model for one subject endpoint
@JsonClass(generateAdapter = true)
data class OneSubject (
    val code:String,
    val message:String,
    val moreInfo:String,
    val lang:String,
    val status:Int,
    @Json(name = "result")  val result:ResultsOneSubject
)

// response model for getsession endpoint
@JsonClass(generateAdapter = true)
data class Session (
    val code:String,
    val message:String,
    val moreInfo:String,
    val lang:String,
    val status:Int,
    @Json(name = "result")  val result:ResultSession
)

@JsonClass(generateAdapter = true)
data class ResultAllSubjects(
    val responseFlag:String,
    val responseMessage:String,
    @Json(name = "resultData") val resultData: List<Subject>
)
@JsonClass(generateAdapter = true)
data class ResultsOneSubject(
    val responseFlag:String,
    val responseMessage:String,
    @Json(name = "resultData") val resultData: SubjectData
)

@JsonClass(generateAdapter = true)
data class ResultSession(
    val responseFlag:String,
    val responseMessage:String,
    @Json(name = "resultData") val resultData : sessionResultData
)

@JsonClass(generateAdapter = true)
data class SubjectData (
    val _id:String,
    val subjectname:String,
    @Json(name = "questions") val questions: List<Question>?
)

@JsonClass(generateAdapter = true)
data class Question(
    val question:String,
    @Json(name="options") val options: List<Options>,
    val illustrator: String,
)


@JsonClass(generateAdapter = true)
data class Options(
    var text: String, var correct: Boolean
)



@JsonClass(generateAdapter = true)
data class Subject(
    var subjectname : String, var counts:Int, var scope:String, var _id:String
)

@JsonClass(generateAdapter = true)
data class Record(
    var subject : String,
    var date : String,
    var start : String,
    var end : String,
    var score : String
)



@JsonClass(generateAdapter = true)
data class Category(
    val name :String,
    val icon : Int,
    val topics : String?
)

@JsonClass(generateAdapter = true)
data class ExamRequest(
    val subjectname:String ,
    val length:Int
)

@JsonClass(generateAdapter = true)
data class sessionResultData(
    @Json(name = "MaxLength") val MaxLength:Int,
    @Json(name = "sequence") val sequence:List<Int>,
    @Json(name = "exam") val exam : List<Question>
)



data class testResults(
    var examLength :Int = 0 ,
    var Score : Int = 0,
    var attemptedQuestions: Int = 0,
    var averageTime : Long = 0,
    var errors :Int = 0,
)

data class userResponse(
    val question:Question,
    val attempted : Boolean,
    val passed : Boolean ,
    val responseIndex : Int,
    val timetoResponse : Long,
    val rightIndex :Int =0
)