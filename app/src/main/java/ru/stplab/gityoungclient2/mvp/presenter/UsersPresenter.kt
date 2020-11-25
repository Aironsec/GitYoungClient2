package ru.stplab.gityoungclient2.mvp.presenter

import moxy.MvpPresenter
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.repo.GitUsersRepo
import ru.stplab.gityoungclient2.mvp.presenter.list.IUserListPresenter
import ru.stplab.gityoungclient2.mvp.view.UsersView
import ru.stplab.gityoungclient2.mvp.view.list.UserItemView
import ru.stplab.gityoungclient2.navigation.Screens
import ru.terrakok.cicerone.Router

class UsersPresenter(private val router: Router, private val usersRepo: GitUsersRepo) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GitUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(Screens.CurrentUserScreen(user))
        }
    }

    private fun loadData() {
        val usersRx = usersRepo.getUsers()
        usersListPresenter.users.clear()
        usersRx.toList()
            .subscribe { users ->
                usersListPresenter.users.addAll(users)
            }
        viewState.updateList()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}