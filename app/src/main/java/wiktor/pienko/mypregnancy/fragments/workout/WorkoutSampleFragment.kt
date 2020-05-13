import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wiktor.pienko.mypregnancy.R
import wiktor.pienko.mypregnancy.TopSpacingItemDecoration
import wiktor.pienko.mypregnancy.fragments.workout.WorkoutAdapter
import wiktor.pienko.mypregnancy.fragments.workout.WorkoutSample

class WorkoutSampleFragment : Fragment(){

    private lateinit var recycler: RecyclerView
    private lateinit var adapterWorkout: WorkoutAdapter
    private val workoutStrings= ArrayList<String>()
    private val workoutImages= ArrayList<String>()
    private val list= ArrayList<WorkoutSample>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_workout_sample, container, false)
        recycler = root.findViewById(R.id.my_recycler_view) as RecyclerView
        recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            adapterWorkout = WorkoutAdapter()
            adapter=adapterWorkout
        }
        addData()
        Log.d("listttt",list.toString())

        return root



}
    private fun addData(){
        for(i in 1..16){
            var resImageId=this.resources.getIdentifier("exerciseImage$i", "string", activity?.packageName)
            var resTextId=this.resources.getIdentifier("exerciseString$i", "string", activity?.packageName)
            workoutImages.add(getString(resImageId))
            workoutStrings.add(getString(resTextId))
            list.add(WorkoutSample(workoutStrings[i-1],workoutImages[i-1]))
            adapterWorkout.submitList(list)
            adapterWorkout.notifyDataSetChanged()
        }
    }
}
