package xyz.thisisjames.boulevard.android.podium.destinations.reviews.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentSwipeReviewBinding
import xyz.thisisjames.boulevard.android.podium.destinations.test.TestViewModel
import xyz.thisisjames.boulevard.android.podium.listmanagers.OptionsRecyclerAdapter
import xyz.thisisjames.boulevard.android.podium.listmanagers.OptionsReviewAdapter
import xyz.thisisjames.boulevard.android.podium.models.Options
import xyz.thisisjames.boulevard.android.podium.models.userResponse
import javax.inject.Inject


@AndroidEntryPoint
class PlaceholderFragment : Fragment() {

    private var pos : Int = 0 ;

    private lateinit var viewModel: PageViewModel

    private var _binding: FragmentSwipeReviewBinding? = null

    private val binding get() = _binding!!

    private lateinit var ur:userResponse

    @Inject
    lateinit var testViewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pos = arguments?.getInt(ARG_SECTION_NUMBER) ?: 0
        ur  = testViewModel.testEvaluationHelper.sessionResponses.get(pos)


        viewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex( ur )
        }

    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSwipeReviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.testQuestion.text = viewModel.question.invoke()


        binding.testIllustration.text = viewModel.illustrator.invoke()
        binding.testIllustration.visibility = if(viewModel.illustrator.invoke().isNotEmpty())  View.VISIBLE else View.GONE

        showAnswers.invoke()
    }


    private val showAnswers : () -> Unit = {
        val or_adapter = OptionsReviewAdapter(ur.question.options, ur.rightIndex ,ur.responseIndex)

        binding.testOptions.apply {
            adapter = or_adapter
        }

    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}