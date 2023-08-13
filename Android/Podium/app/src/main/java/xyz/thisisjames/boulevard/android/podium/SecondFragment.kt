package xyz.thisisjames.boulevard.android.podium

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.thisisjames.boulevard.android.podium.data.Shared
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentSecondBinding
import xyz.thisisjames.boulevard.android.podium.home.HomeActivity

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatNext.setOnClickListener {
            var shared = activity?.let { it1 -> Shared(it1) };
            shared?.changeStatus(true);

            var intent: Intent = Intent(context,HomeActivity::class.java);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}