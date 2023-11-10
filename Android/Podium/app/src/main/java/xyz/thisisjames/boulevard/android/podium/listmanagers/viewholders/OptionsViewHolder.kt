package xyz.thisisjames.boulevard.android.podium.listmanagers.viewholders

import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.podium.databinding.OptionsItemBinding

class OptionsViewHolder(binding:OptionsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

        val radioButton : AppCompatRadioButton = binding.optionsRadio

        val tag : AppCompatTextView = binding.optionsTag

        val textView : AppCompatTextView = binding.optionsText


}