package wiktor.pienko.mypregnancy.base

import wiktor.pienko.mypregnancy.injection.component.DaggerPresenterInjector
import wiktor.pienko.mypregnancy.injection.component.PresenterInjector
import wiktor.pienko.mypregnancy.injection.module.NetworkModule
import wiktor.pienko.mypregnancy.injection.module.ContextModule

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    protected val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    /*private fun inject() {
        when (this) {
            is MainPresenter -> injector.inject(this)
        }
    }*/
}