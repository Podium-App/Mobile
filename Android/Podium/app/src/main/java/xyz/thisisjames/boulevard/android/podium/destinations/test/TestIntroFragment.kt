package xyz.thisisjames.boulevard.android.podium.destinations.test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentTestintroBinding
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import javax.inject.Inject
import kotlin.math.floor

@AndroidEntryPoint
class TestIntroFragment : Fragment() {

    private var _binding: FragmentTestintroBinding? = null

    @Inject lateinit var testViewModel: TestViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTestintroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tent = requireActivity().intent

        var request: ExamRequest = ExamRequest(
            tent.getStringExtra("Subjectname")!!,
            tent.getIntExtra("Length",0)
        )

        testViewModel.apply {
            createSession(request) ;
        }

        binding.introLength.text = "${request.length} Multiple choice questions"
        binding.introTiming.text = "50 seconds per question"

        binding.introSubject.text = request.subjectname+" Assessment";

        binding.buttonStart.setOnClickListener{
            navigate()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun navigate(){
        if (!testViewModel.testIsReady){
            testViewModel.getSession()
            findNavController().navigate(R.id.action_testIntroFragment_to_testStartFragment)
        }else{
            findNavController().navigate(R.id.action_testIntroFragment_to_testFragment)
        }
    }

}