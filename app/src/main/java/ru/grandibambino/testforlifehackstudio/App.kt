package ru.grandibambino.testforlifehackstudio

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import ru.grandibambino.testforlifehackstudio.di.appModules
import ru.grandibambino.testforlifehackstudio.di.viewModelModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModules, viewModelModules))
        }
    }

}