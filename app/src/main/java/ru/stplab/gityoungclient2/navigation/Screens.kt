package ru.stplab.gityoungclient2.navigation

import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.ui.fragment.UserRepositoriesFragment
import ru.stplab.gityoungclient2.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class RepositoriesUserScreen(private val user: GitUser) : SupportAppScreen() {
        override fun getFragment() = UserRepositoriesFragment.newInstance(user)
    }
}