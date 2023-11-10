package xyz.thisisjames.boulevard.android.podium.destinations.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentSearchBinding
import xyz.thisisjames.boulevard.android.podium.databinding.FragmentTestBinding
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import xyz.thisisjames.boulevard.android.podium.destinations.test.TestActivity
import xyz.thisisjames.boulevard.android.podium.listmanagers.RecyclerAdapter
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import xyz.thisisjames.boulevard.android.podium.models.Subject
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? =null


    @Inject
    lateinit var baseViewModel: BaseViewModel

    private val binding get() = _binding !!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.searchEdittext.setOnEditorActionListener { v, actionId, event -> runSearch() }

        binding.searchSUbmitButton.setOnClickListener {
            var search = binding.searchEdittext.getText().toString().trim()

            if (search != null) {
                if (search.isNotEmpty()){
                    baseViewModel.searchSubjects(search)
                }

            }
        }

        baseViewModel.homeViewModel.searchedSubjects.observe(viewLifecycleOwner, Observer {

            showResults(it!!)

        })

        baseViewModel.homeViewModel.searchedSubjects.value?.let { showResults(it)}

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
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }


    fun runSearch():Boolean{
        var search = binding.searchEdittext.getText().toString().trim()
        if (search != null) {
            if (search.isNotEmpty()){
                baseViewModel.searchSubjects(search)
            }

        }
        return true
    }

    fun showResults(list:List<Subject>){
        binding.resultSummary.text = "${list?.size} instances found."
        binding.subjectList.apply {
            adapter  = RecyclerAdapter(list){pos->listItemClicked(pos)}
        }
    }

}