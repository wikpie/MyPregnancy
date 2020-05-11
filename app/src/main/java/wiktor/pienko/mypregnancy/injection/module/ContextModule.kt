package wiktor.pienko.mypregnancy.injection.module

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import wiktor.pienko.mypregnancy.R
import wiktor.pienko.mypregnancy.base.BaseView

@Module
    @Suppress("unused")
    object ContextModule {
        @Provides
        @JvmStatic
        internal fun provideContext(baseView: BaseView): Context {
            return baseView.getContext()
        }

        @Provides
        @JvmStatic
        internal fun provideApplication(baseView: BaseView): Activity {
            return baseView.getActivity()
        }

    }
