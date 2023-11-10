package xyz.thisisjames.boulevard.android.podium.listmanagers.viewholders

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.podium.databinding.SubjectItemBinding

class subjectsViewHolders (binding:SubjectItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val questions: AppCompatTextView = binding.siQuestions;
    val testLength: AppCompatTextView = binding.siLength;

    val subject : AppCompatTextView = binding.siSubject;
    val category: AppCompatTextView = binding.siCategory;



}