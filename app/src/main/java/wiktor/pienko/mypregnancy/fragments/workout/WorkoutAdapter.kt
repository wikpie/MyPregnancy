package wiktor.pienko.mypregnancy.fragments.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
        val exerciseText=itemView.exercise_text
        val exerciseImage=itemView.senior_image
        fun bind(sample: WorkoutSample){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(sample.image)
                .into(exerciseImage)
            exerciseText.setText(sample.name)
        }
    }
}
data class WorkoutSample(var name: String,var image: String)