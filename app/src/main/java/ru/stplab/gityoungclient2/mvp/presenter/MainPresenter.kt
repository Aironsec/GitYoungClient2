package ru.stplab.gityoungclient2.mvp.presenter

import moxy.MvpPresenter
import ru.stplab.gityoungclient2.mvp.view.MainView
import ru.stplab.gityoungclient2.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter: MvpPresenter<MainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }
}