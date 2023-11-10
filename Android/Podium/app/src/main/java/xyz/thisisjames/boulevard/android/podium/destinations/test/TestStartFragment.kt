package xyz.thisisjames.boulevard.android.podium.destinations.test

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentTestStartBinding
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import javax.inject.Inject


@AndroidEntryPoint
class TestStartFragment : Fragment() {

    @Inject
    lateinit var testViewModel: TestViewModel

    private var _binding: FragmentTestStartBinding? = null

    private val binding get() = _binding !!



    private var mHandler : Handler = Handler()
    private var count = 0 ;
    private var mRunnable = Runnable{
        if (testViewModel.testIsReady){
            findNavController().navigate(R.id.action_testStartFragment_to_testFragment)
        }else if (testViewModel.downloadTimer < 30000){
            count+=1;
            testViewModel.downloadTimer +=7500
            mHandler.postDelayed(getRunnable(),7500)
            if (count<4){
                binding.startStatus.text = testViewModel.downloadStatus[count]
            }
        }else{
            binding.loaderView.visibility = View.GONE
            binding.retryView.visibility = View.VISIBLE
        }
    }

    private fun getRunnable() : Runnable{
        return mRunnable
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentTestStartBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testViewModel.getSession()
        mHandler.postDelayed(getRunnable(),7500)

        binding.trybutton.setOnClickListener{
            binding.loaderView.visibility = View.VISIBLE
            binding.retryView.visibility = View.GONE
            count = 0;
            testViewModel.downloadTimer = 1000
            recreate()
        }
    }


    private fun recreate(){
        val tent = requireActivity().intent

        var request: ExamRequest = ExamRequest(
            tent.getStringExtra("Subjectname")!!,
            tent.getIntExtra("Length",0)
        )

        testViewModel.apply {
            createSession(request) ;
        }

        mHandler.postDelayed(getRunnable(),7500)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}