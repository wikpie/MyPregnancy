package wiktor.pienko.mypregnancy.fragments.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import wiktor.pienko.mypregnancy.R

class WorkoutFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_workout, container, false)
        root.findViewById<Button>(R.id.button_do)?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_workout_to_workoutDosFragment)
        }
        root.findViewById<Button>(R.id.button_dont)?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_workout_to_workoutDontsFragment)
        }
        root.findViewById<Button>(R.id.button_example_exercise)?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_workout_to_workoutSampleFragment)
        }
        return root
    }
}
