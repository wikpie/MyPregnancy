package wiktor.pienko.mypregnancy.fragments.diet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import wiktor.pienko.mypregnancy.R

class DietFragment : Fragment(){

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_diet, container, false)
        root.findViewById<Button>(R.id.button_products)?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_diet_to_dietProductsFragment)
        }
        root.findViewById<Button>(R.id.button_nutrients)?.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_navigation_diet_to_dietNutrientsFragment)
        }
        return root
    }


}
