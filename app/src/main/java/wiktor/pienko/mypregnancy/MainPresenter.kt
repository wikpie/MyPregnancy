package wiktor.pienko.mypregnancy

import wiktor.pienko.mypregnancy.base.BasePresenter
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {


    fun welcomeUser( name : String, pregnancyWeek : Int){

    }

    fun findNearestHospital( latitude : Double, longitude : Double){

    }

    fun alarmCall( phoneNumber : Int){

    }
    fun showWeeklyInfo( pregnancyWeek : Int){

    }
}