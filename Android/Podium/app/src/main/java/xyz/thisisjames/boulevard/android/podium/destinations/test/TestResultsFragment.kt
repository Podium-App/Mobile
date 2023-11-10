package xyz.thisisjames.boulevard.android.podium.destinations.test

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentTestResultsBinding
import xyz.thisisjames.boulevard.android.podium.destinations.connectivity.ConnectivityActivity
import xyz.thisisjames.boulevard.android.podium.destinations.reviews.SwipeReviewActivity
import xyz.thisisjames.boulevard.android.podium.destinations.test.TestViewModel
import javax.inject.Inject

@AndroidEntryPoint
class TestResultsFragment : Fragment() {


    @Inject
    lateinit var testViewModel: TestViewModel

    private var _binding: FragmentTestResultsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTestResultsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showResults()

        setListeners()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showResults() {

        binding.dsubject.text = testViewModel.subjectname

        binding.testresultScore.text = " ${testViewModel.testEvaluationHelper.userResults.Score} %"

        val circularProgress : CircularProgressIndicator = binding.circularProgress

        circularProgress.setProgress(testViewModel.testEvaluationHelper.userResults.Score.toDouble(), 100.0);


        binding.resultsAvtime.text = "${(testViewModel.testEvaluationHelper.userResults.averageTime)} seconds per question."

        binding.resultsAttempted.text = "${testViewModel.testEvaluationHelper.userResults.attemptedQuestions} of " +
                "${testViewModel.testEvaluationHelper.userResults.examLength} questions attempted."

        binding.resultsErrors.text = "${testViewModel.testEvaluationHelper.userResults.errors} of ${testViewModel.testEvaluationHelper.userResults.examLength} questions missed."
    }

    private fun setListeners(){
        binding.fab.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.reviewButton.setOnClickListener {
            val intent : Intent = Intent(context, SwipeReviewActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}