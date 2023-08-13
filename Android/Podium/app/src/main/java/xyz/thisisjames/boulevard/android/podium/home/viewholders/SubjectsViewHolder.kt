package xyz.thisisjames.boulevard.android.podium.home.viewholders

import android.view.View
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.podium.databinding.SubjectItemCardBinding
import xyz.thisisjames.boulevard.android.podium.home.adapters.RecyclerAdapter

class SubjectsViewHolder(binding:SubjectItemCardBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    val medianScore :AppCompatTextView  = binding.sbjMedianScore;
    val questions:AppCompatTextView = binding.sbjQuestions;
    val testLength: AppCompatTextView = binding.sbjLength;

    val subject : AppCompatTextView = binding.sbjTitle;
    val category: AppCompatTextView = binding.sbjCategory;

    val button: AppCompatButton = binding.btnTakeTest;


    init {
        binding.root.setOnClickListener(this);
    }

    override fun onClick(view: View?) {

    }
}