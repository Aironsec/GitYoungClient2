package ru.stplab.gityoungclient2.ui

import android.app.Application
import ru.stplab.gityoungclient2.mvp.model.entity.room.db.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App: Application() {
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }

    val router
        get() = cicerone.router

    val navigatorHolder
        get() = cicerone.navigatorHolder
}