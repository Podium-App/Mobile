package xyz.thisisjames.boulevard.android.podium.destinations.connectivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentConnectBinding
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import xyz.thisisjames.boulevard.android.podium.destinations.home.HomeActivity
import javax.inject.Inject

@AndroidEntryPoint
class ConnectFragment : Fragment() {

    private lateinit var binding: FragmentConnectBinding

    @Inject
    lateinit var baseViewModel: BaseViewModel


    private var myHandler: Handler = Handler();
    private var myRunnable = Runnable{
        var ready = baseViewModel.subjects.value?.size

        if(ready != null) {
            val intent = Intent(activity,HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }else if (baseViewModel.TIMECLOCK < 30000) {
            baseViewModel.TIMECLOCK += 1000
            /*binding.text.apply {
                text = "subjects length = ${baseViewModel._allSubjects.value?.size}\n " +
                        "Download status = ${baseViewModel.downloadStatus}\n" +
                        "Time Clock Value = ${baseViewModel.TIMECLOCK}"
            }*/
            myHandler.postDelayed(getRunnable(),1000);
        }else{
            findNavController().navigate(R.id.action_connectFragment_to_disconnectedFragment)
        }
    }

    private fun getRunnable(): Runnable {
        return myRunnable;
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConnectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myHandler.postDelayed(myRunnable,1000)
    }



}