package xyz.thisisjames.boulevard.android.podium.destinations.home

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentHomeBinding
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentTestResultsBinding
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import xyz.thisisjames.boulevard.android.podium.destinations.test.TestActivity
import xyz.thisisjames.boulevard.android.podium.listmanagers.RecyclerAdapter
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject lateinit var baseViewModel: BaseViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }


    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var search = binding.searchEdittext

        search.setOnEditorActionListener { v, actionId, event -> runSearch()  }

        binding.searchSUbmitButton.setOnClickListener {
            runSearch()
        }
    }

    override fun onStart() {
        super.onStart()

        binding.subjectList.apply {
            adapter  = RecyclerAdapter(baseViewModel.subjects.value!!){pos->listItemClicked(pos)}
        }
    }

    fun listItemClicked(position: ExamRequest) : Unit{
        var intent = Intent(context, TestActivity::class.java)
        intent.putExtra("Subjectname",position.subjectname)
        intent.putExtra("Length", position.length)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }

    fun runSearch():Boolean{
        var search = binding.searchEdittext.getText().toString().trim()
        if (search != null) {
            if (search.isNotEmpty()){
                baseViewModel.searchSubjects(search)

                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }

        }
       return true
    }
}