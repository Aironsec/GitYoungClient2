package ru.stplab.gityoungclient2.navigation

import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.ui.fragment.CurrentUserFragment
import ru.stplab.gityoungclient2.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class CurrentUserScreen(private val user: GitUser) : SupportAppScreen() {
        override fun getFragment() = CurrentUserFragment.newInstance(user)
    }
}