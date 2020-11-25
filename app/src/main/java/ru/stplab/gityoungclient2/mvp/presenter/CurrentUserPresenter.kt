package ru.stplab.gityoungclient2.mvp.presenter

import moxy.MvpPresenter
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.view.CurrentUserView
import ru.stplab.gityoungclient2.navigation.Screens
import ru.terrakok.cicerone.Router

class CurrentUserPresenter(private val router: Router, private val user: GitUser): MvpPresenter<CurrentUserView>(){

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setCurrentUser(user.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}