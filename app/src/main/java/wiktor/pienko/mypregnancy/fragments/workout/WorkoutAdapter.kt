package wiktor.pienko.mypregnancy.fragments.workout

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recycler_workout_item.view.*
import wiktor.pienko.mypregnancy.R

class WorkoutAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workoutExerciseList: List<WorkoutSample> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WorkoutViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_workout_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return workoutExerciseList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is WorkoutViewHolder ->{
                holder.bind(workoutExerciseList[position])
            }
        }
    }
    fun submitList(workoutList: List<WorkoutSample>){
        workoutExerciseList=workoutList
    }
    inner class WorkoutViewHolder constructor(
        itemView : View
    ):RecyclerView.ViewHolder(itemView){
        private val exerciseText: TextView=itemView.exerciseText
        private val exerciseImage: ImageView =itemView.exerciseImage
        fun bind(sample: WorkoutSample){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(sample.image)
                .into(exerciseImage)
            exerciseText.text = sample.name
            Log.d("listname", sample.toString())
        }

    }
}
data class WorkoutSample(var name: String,var image: String)