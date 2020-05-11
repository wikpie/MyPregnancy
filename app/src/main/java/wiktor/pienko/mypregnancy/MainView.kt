package wiktor.pienko.mypregnancy

import android.widget.Toast
import wiktor.pienko.mypregnancy.base.BaseView


interface MainView : BaseView {

    fun showError(error: String){
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show()
    }

    fun showHospital(hospitalName: String, hospitalAddress: String){

    }

    fun showInfo(info: String){

    }

}