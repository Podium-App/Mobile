package xyz.thisisjames.boulevard.android.podium.destinations.test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentTestBinding
import xyz.thisisjames.boulevard.android.podium.listmanagers.OptionsRecyclerAdapter
import xyz.thisisjames.boulevard.android.podium.models.Options
import xyz.thisisjames.boulevard.android.podium.models.Question
import xyz.thisisjames.boulevard.android.podium.models.userResponse
import javax.inject.Inject

@AndroidEntryPoint
class TestFragment : Fragment() {

    @Inject
    lateinit var viewModel: TestViewModel

    private var _binding:FragmentTestBinding ? =null ;

    private lateinit var or_adapter : OptionsRecyclerAdapter ;

    private val binding get() = _binding !!

    private var currentCheck = -1 ;

    private var validOption = -2;

    private lateinit var qst:Question


    private var mHandler = Handler()

    private var mRunnable = object :  Runnable {
        override fun run() {
            val resetQuestion = viewModel.testTimeHelper.updateQuestionTime.invoke()
            mHandler.postDelayed(this,1000);
            if (!resetQuestion){
                submitResponse()
                getQuestion.invoke()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getQuestion.invoke()
        mHandler.postDelayed(mRunnable,1000);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //manage time settings
        viewModel.testTimeHelper.QuestionTime.observe(viewLifecycleOwner, Observer {
            binding.testProgress.text =
                "${viewModel.testNavigationHelper.QuestionNumber.value} of ${viewModel.length} | ${viewModel.testTimeHelper.timeState.invoke(it)} Left"
        })

        binding.testSubject.text = viewModel.subjectname+" Assessment"

        viewModel.testNavigationHelper.CurrentQuestion.observe(viewLifecycleOwner, Observer {
            binding.testQuestion.text = it.question
            binding.testIllustration.setMarkDownText(it.illustrator)
            binding.testIllustration.visibility = if(it.illustrator.isNotEmpty())  View.VISIBLE else View.GONE
            currentCheck = -1
            showOptions.invoke(it.options)
            qst = it
        })


        binding.testSubmit.setOnClickListener {
            submitResponse()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onCheckBoxClicked  (pos:Int, status:Boolean) :Unit {
        currentCheck = pos
        showOptions.invoke(viewModel.testNavigationHelper.CurrentQuestion.value!!.options)
     }

    private val showOptions : (options:List<Options>) -> Unit = {
        or_adapter = OptionsRecyclerAdapter(it, currentCheck){ p,b -> onCheckBoxClicked(p,b) }

        correctIndex.invoke(it)

        binding.testOptions.apply {
            adapter = or_adapter
        }

        binding.testSubmit.isEnabled = currentCheck != -1
    }

    private val getQuestion : () -> Unit = {
        val test = viewModel.nextQuestion()
        if (test){
            moveToResults()
        }
    }

    private  fun submitResponse (){

        val _response :userResponse = userResponse(
            qst, currentCheck != -1, validOption == currentCheck, currentCheck,
            viewModel.testEvaluationHelper.computeResponseTime(viewModel.testTimeHelper.questionTime.value!!)
            ,validOption
        )

       viewModel.submitResponse(_response)

        getQuestion.invoke()

    }

    private fun moveToResults(){
        mHandler.removeCallbacks(mRunnable)
        viewModel.moveToResults()
        findNavController().navigate(R.id.action_testFragment_to_testResultsFragment)
    }

    private val correctIndex : (options:List<Options>) -> Unit = {
        val inx = it.mapIndexed { index, item -> if (item.correct) index else null }.filterNotNull().toList()
        validOption = if (inx.size == 0) -2 else inx[0]
    }

}