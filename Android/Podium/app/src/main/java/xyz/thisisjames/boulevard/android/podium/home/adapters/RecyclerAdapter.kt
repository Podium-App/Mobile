package xyz.thisisjames.boulevard.android.podium.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.podium.data.models.Subject
import xyz.thisisjames.boulevard.android.podium.databinding.SubjectItemCardBinding
import xyz.thisisjames.boulevard.android.podium.home.viewholders.SubjectsViewHolder

class RecyclerAdapter(private val values: LiveData<ArrayList<Subject>>)
    :RecyclerView.Adapter<SubjectsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        return SubjectsViewHolder(
            SubjectItemCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 30 // values.size
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {

    }
}