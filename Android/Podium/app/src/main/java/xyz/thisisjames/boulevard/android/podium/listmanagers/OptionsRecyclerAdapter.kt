package xyz.thisisjames.boulevard.android.podium.listmanagers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.R.color.dark_primary
import xyz.thisisjames.boulevard.android.podium.databinding.OptionsItemBinding
import xyz.thisisjames.boulevard.android.podium.listmanagers.viewholders.OptionsViewHolder
import xyz.thisisjames.boulevard.android.podium.models.Options

class OptionsRecyclerAdapter (private val values: List<Options>, private val currentAnswer:Int,
    private val onItemClicked: (Int,Boolean)->Unit) : RecyclerView.Adapter<OptionsViewHolder>() {

    private lateinit var context:Context;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        context = parent.context

         return OptionsViewHolder(
             OptionsItemBinding.inflate(
                 LayoutInflater.from(parent.context),
                 parent,
                 false
             )
         )
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        val item = values[position]
        holder.textView.text = item.text

        holder.radioButton.isChecked = position == currentAnswer

        holder.radioButton.setOnCheckedChangeListener {
                buttonView, isChecked ->
                    onItemClicked(position, isChecked)
        }

        holder.itemView.setOnClickListener{
            onItemClicked(position, true)
        }

        if (position == currentAnswer){
            holder.itemView.background = context.getDrawable(R.drawable.option_selected)
            holder.tag.setTextColor(context.getColor(dark_primary) )
            holder.textView.setTextColor(context.getColor(dark_primary) )
        }

        holder.tag.text = when(position){
            0 -> "A"
            1-> "B"
            2-> "C"
            else -> "D"
        }


    }
}