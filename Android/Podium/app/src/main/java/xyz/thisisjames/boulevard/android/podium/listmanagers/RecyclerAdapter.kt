package xyz.thisisjames.boulevard.android.podium.listmanagers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import xyz.thisisjames.boulevard.android.podium.databinding.SubjectItemBinding
import xyz.thisisjames.boulevard.android.podium.listmanagers.viewholders.subjectsViewHolders
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import xyz.thisisjames.boulevard.android.podium.models.Subject
import kotlin.math.ceil
import kotlin.math.floor

class RecyclerAdapter(private val values: List<Subject>, private val onItemClicked: (ExamRequest)->Unit)
    : RecyclerView.Adapter<subjectsViewHolders>(){

    private var mValues : List<Subject> = values;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): subjectsViewHolders {

        return subjectsViewHolders(
            SubjectItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )

    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: subjectsViewHolders, position: Int) {

        var item = mValues.get(position)
        var specs = getSpecs(item.counts)

        holder.apply {
            category.text = item.scope
            subject.text = item.subjectname
            questions.text = "${specs[0]} Questions"
            testLength.text = "${specs[1]} minutes"
        }

        holder.itemView.setOnClickListener{
            onItemClicked(ExamRequest(item.subjectname,specs[0]))
        }
    }


    private fun getSpecs(count:Int):Array<Int>{
        if (count> 36 ){
            return arrayOf(18,15)
        }else {
            var time = floor((count*50.0)/60).toInt()
            return arrayOf( count, time)
        }
    }
}