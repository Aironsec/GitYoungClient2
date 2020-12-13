package ru.stplab.gityoungclient2.ui

import android.app.Application
import ru.stplab.gityoungclient2.di.AppComponent
import ru.stplab.gityoungclient2.di.DaggerAppComponent
import ru.stplab.gityoungclient2.di.modules.AppModule

class App: Application() {

    companion object{
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}