package xyz.thisisjames.boulevard.android.podium.listmanagers.viewholders

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.podium.databinding.OptionsItemBinding
import xyz.thisisjames.boulevard.android.podium.databinding.OptionsItemReviewBinding

class OptionsReviewViewHolder  (binding: OptionsItemReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val image : AppCompatImageView = binding.optionsChoice

    val tag : AppCompatTextView = binding.optionsTag

    val textView : AppCompatTextView = binding.optionsText

    val you : AppCompatTextView = binding.reviewYourchoice

}