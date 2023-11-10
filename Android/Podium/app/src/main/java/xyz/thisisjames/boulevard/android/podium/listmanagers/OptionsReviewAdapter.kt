package xyz.thisisjames.boulevard.android.podium.listmanagers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.OptionsItemReviewBinding
import xyz.thisisjames.boulevard.android.podium.listmanagers.viewholders.OptionsReviewViewHolder
import xyz.thisisjames.boulevard.android.podium.models.Options

class OptionsReviewAdapter (private val values: List<Options>, private val rightAnswer:Int,
                            private val userAnswer:Int ) : RecyclerView.Adapter<OptionsReviewViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsReviewViewHolder {
        context = parent.context

        return OptionsReviewViewHolder(
            OptionsItemReviewBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: OptionsReviewViewHolder, position: Int) {
        val item = values[position]
        holder.textView.text = item.text


        holder.tag.text = when(position){
            0 -> "A"
            1-> "B"
            2-> "C"
            else -> "D"
        }

        if (position ==  rightAnswer){
            holder.image.setImageDrawable(context.getDrawable(R.drawable.luna_valid))
        }else{
            holder.image.setImageDrawable(context.getDrawable(R.drawable.luna_invalid))
        }

        holder.you.visibility = if (position == userAnswer) View.VISIBLE else View.GONE

    }
}