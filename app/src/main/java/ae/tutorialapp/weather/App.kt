package ae.tutorialapp.weather

import ae.tutorialapp.weather.depI.dataModule
import ae.tutorialapp.weather.depI.vmModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(vmModule, dataModule))
        }
    }
}