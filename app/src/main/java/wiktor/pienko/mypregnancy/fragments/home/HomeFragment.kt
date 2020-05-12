package wiktor.pienko.mypregnancy.fragments.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import wiktor.pienko.mypregnancy.R


class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textHome: TextView = root.findViewById(R.id.text_home)
        val textWeek: TextView = root.findViewById(R.id.week_text)
        val textInfo: TextView = root.findViewById(R.id.info_textview)
        val sharedPreferencesName= this.activity!!.getSharedPreferences(getString(R.string.name), Context.MODE_PRIVATE)
        val sharedPreferencesPregnancy = this.activity!!.getSharedPreferences(getString(R.string.pregnancy_time_pref), Context.MODE_PRIVATE)
        val sharedPreferencesNumber = this.activity!!.getSharedPreferences(getString(R.string.alarm_number_preferences), Context.MODE_PRIVATE)
        val name=sharedPreferencesName.getString(getString(R.string.name),"x").toString()
        val week=sharedPreferencesPregnancy.getInt(getString(R.string.pregnancy_time_pref),0)
        val number=sharedPreferencesNumber.getInt(getString(R.string.alarm_number_preferences),0)
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        textHome.text=getString(R.string.greeting, name)
        textWeek.text=getString(R.string.week_count, week)
        when {
            week<=13 -> {
                textInfo.text=getString(R.string.trimestr_one)
            }
            week in 14..27 -> {
                textInfo.text=getString(R.string.trimestr_two)
            }
            else -> textInfo.text=getString(R.string.trimestr_three)
        }
        root.alert_button.setOnClickListener{
            startActivity(intent)
        }
        return root
    }
}
