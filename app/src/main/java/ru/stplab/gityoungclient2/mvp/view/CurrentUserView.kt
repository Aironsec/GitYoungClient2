package ru.stplab.gityoungclient2.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CurrentUserView: MvpView {

    fun setCurrentUser(user: String)
}