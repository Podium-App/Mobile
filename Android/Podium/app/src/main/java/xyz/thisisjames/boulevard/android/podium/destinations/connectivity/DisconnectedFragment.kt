package xyz.thisisjames.boulevard.android.podium.destinations.connectivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentDisconnectedBinding
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import javax.inject.Inject


@AndroidEntryPoint
class DisconnectedFragment : Fragment() {

    private lateinit var binding: FragmentDisconnectedBinding

    @Inject
    lateinit var baseViewModel: BaseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDisconnectedBinding.inflate(layoutInflater)

        binding.trybutton.setOnClickListener{
            baseViewModel.TIMECLOCK = 0
            baseViewModel.getAllSubjects()
            findNavController().navigate(R.id.action_disconnectedFragment_to_connectFragment)
        }

         return binding.root
    }


}