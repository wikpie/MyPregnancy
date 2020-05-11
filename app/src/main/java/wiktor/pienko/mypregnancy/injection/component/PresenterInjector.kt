package wiktor.pienko.mypregnancy.injection.component

import dagger.BindsInstance
import dagger.Component
import wiktor.pienko.mypregnancy.MainPresenter
import wiktor.pienko.mypregnancy.base.BaseView
import wiktor.pienko.mypregnancy.injection.module.NetworkModule
import wiktor.pienko.mypregnancy.injection.module.ContextModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {

    fun inject(mainPresenter: MainPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}