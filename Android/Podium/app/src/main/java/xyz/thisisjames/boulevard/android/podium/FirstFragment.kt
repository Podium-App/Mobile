package xyz.thisisjames.boulevard.android.podium

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import xyz.thisisjames.boulevard.android.podium.data.Shared
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentFirstBinding
import xyz.thisisjames.boulevard.android.podium.home.HomeActivity
import java.lang.Exception


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!


    private var myHandler: Handler = Handler();
    var myRunnable = Runnable{
        if (!getStatus()){
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }else{
            var intent: Intent = Intent(context, HomeActivity::class.java);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myHandler.postDelayed(myRunnable,3000);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getStatus():Boolean{
        var shared = activity?.let { it1 -> Shared(it1) };
        if (shared != null) {
            shared.retrieveStatus();
        }

        return  false;
    }


}