package wiktor.pienko.mypregnancy

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login_dialog.view.*
import wiktor.pienko.mypregnancy.fragments.home.HomeFragment
import kotlin.math.roundToInt

class MainActivity  :  AppCompatActivity() {

    private val locationPolanki=Location(hospitalPolanki)
    private val locationDebinki=Location(hospitalDebinki)
    private val locationPolanki2=Location(hospitalPolanki2)
    private val locationSmoluchowskiego=Location(hospitalSmoluchowskiego)
    private val locationWilenska=Location(hospitalWilenska)
    private val locationOgrody=Location(hospitalOgrody)
    private val locationKartuska=Location(hospitalKartuska)
    private val locations=ArrayList<Location>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissions()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val sharedPreferencesPregnancy = this.getSharedPreferences(getString(R.string.pregnancy_time_pref), Context.MODE_PRIVATE)
        val sharedPreferencesTime=this.getSharedPreferences(getString(R.string.time_preferences), Context.MODE_PRIVATE)
        locationPolanki.latitude= polankiLat
        locationPolanki.longitude= polankiLong
        locationDebinki.latitude= debinkiLat
        locationDebinki.longitude= debinkiLong
        locationKartuska.latitude= kartuskaLat
        locationKartuska.longitude= kartuskaLong
        locationOgrody.latitude= ogrodyLat
        locationOgrody.longitude= ogrodyLong
        locationPolanki2.latitude= polanki2Lat
        locationPolanki2.longitude= polanki2Long
        locationSmoluchowskiego.latitude= smoluchowskiegoLat
        locationSmoluchowskiego.longitude= smoluchowskiegoLong
        locationWilenska.latitude= wilenskaLat
        locationWilenska.longitude= wilenskaLong
        locations.add(locationWilenska)
        locations.add(locationSmoluchowskiego)
        locations.add(locationPolanki2)
        locations.add(locationPolanki)
        locations.add(locationOgrody)
        locations.add(locationKartuska)
        locations.add(locationDebinki)
        getLocation()
        checkIfFirstOpen()
        checkDay(sharedPreferencesTime.getLong(getString(R.string.time_preferences), 0), sharedPreferencesPregnancy)
    }

    private fun writeShared(sharedPreferences: SharedPreferences, type: Any, name:String, value: String){
        with (sharedPreferences.edit()) {
            when(type){
                Long->putLong(name, value.toLong())
                Boolean->putBoolean(name,value.toBoolean())
                Int->putInt(name,value.toInt())
                String->putString(name,value)
            }
            commit()
        }
    }

    private fun checkDay(time: Long, sharedPreferences: SharedPreferences){
        val now = System.currentTimeMillis()
        if ((now-time)>=60*60*1000*24*7){
            val week : Int = sharedPreferences.getInt(getString(R.string.pregnancy_time_pref),0)
            writeShared(sharedPreferences,Int,getString(R.string.pregnancy_time_pref),(week+1).toString())
        }
    }

    private fun setupNavigation(extra:String){
        val bundle=Bundle()
        bundle.putString("hospital", extra)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navController.setGraph(R.navigation.mobile_navigation, bundle)
        navView.setupWithNavController(navController)
    }

    private fun checkIfFirstOpen(){
        val sharedPreferencesName = this.getSharedPreferences(getString(R.string.name), Context.MODE_PRIVATE)
        val sharedPreferencesNumber = this.getSharedPreferences(getString(R.string.alarm_number_preferences), Context.MODE_PRIVATE)
        val sharedPreferencesFirst=this.getSharedPreferences(getString(R.string.first_preferences), Context.MODE_PRIVATE)
        val sharedPreferencesPregnancy = this.getSharedPreferences(getString(R.string.pregnancy_time_pref), Context.MODE_PRIVATE)
        val sharedPreferencesTime=this.getSharedPreferences(getString(R.string.time_preferences), Context.MODE_PRIVATE)
        if(sharedPreferencesFirst.getBoolean(getString(R.string.first_preferences), true)){
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val  mAlertDialog = mBuilder.show()
            val textLayoutName = mAlertDialog.findViewById<TextInputLayout>(R.id.add_name)
            val textLayoutNumber = mAlertDialog.findViewById<TextInputLayout>(R.id.add_number)
            val textLayoutPregnancyWeek = mAlertDialog.findViewById<TextInputLayout>(R.id.add_pregnancy)
            mDialogView.button_save.setOnClickListener {
                mAlertDialog.dismiss()
                if (textLayoutName?.editText?.text.toString() == "" || textLayoutNumber?.editText?.text.toString() == "" || textLayoutPregnancyWeek?.editText?.text.toString() == "") {
                    Toast.makeText(this, "Błąd przy tworzeniu", Toast.LENGTH_LONG).show()
                } else {
                    writeShared(sharedPreferencesTime, Long, getString(R.string.time_preferences), System.currentTimeMillis().toString())
                    writeShared(sharedPreferencesName, String,getString(R.string.name),textLayoutName?.editText?.text.toString())
                    writeShared(sharedPreferencesNumber, Int,getString(R.string.alarm_number_preferences),textLayoutNumber?.editText?.text.toString())
                    writeShared(sharedPreferencesPregnancy, Int,getString(R.string.pregnancy_time_pref),textLayoutPregnancyWeek?.editText?.text.toString())
                    writeShared(sharedPreferencesFirst,Boolean,getString(R.string.first_preferences),"false")
                }
            }
        }
    }

    private fun getLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                var nowLocation= location!!
                Log.d("hospital",findNearestHospital(nowLocation))
                setupNavigation(findNearestHospital(nowLocation))
            }
    }

    private fun findNearestHospital(myLocation: Location) : String{
        var closestLocation= Location("")
        var smallestDistance = 50000000
        for (location in locations) {
            val distance = location.distanceTo(myLocation)
            Log.d("hospitalDistance", distance.toString())
            Log.d("hospitalLocation", location.toString())
            if(distance<smallestDistance){
                smallestDistance=distance.roundToInt()
                closestLocation=location
            }
        }
        return closestLocation.provider
    }

    private fun checkPermissions(){
        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.CALL_PHONE)
        ActivityCompat.requestPermissions(this, permissions,0)
    }
}
