package ru.stplab.gityoungclient2.mvp.presenter

import moxy.MvpPresenter
import ru.stplab.gityoungclient2.mvp.view.MainView
import ru.stplab.gityoungclient2.navigation.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }
}