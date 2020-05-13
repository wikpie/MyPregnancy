import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import wiktor.pienko.mypregnancy.R

class WorkoutSampleFragment : Fragment(){

    private lateinit var recycler: RecyclerView
    private lateinit var adapterSenior: WorkoutAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_workout_sample, container, false)

        return root
    }


}
