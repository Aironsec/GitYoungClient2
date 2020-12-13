package ru.stplab.gityoungclient2.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.repo.IGitUsersRepo
import ru.stplab.gityoungclient2.mvp.presenter.list.IUserListPresenter
import ru.stplab.gityoungclient2.mvp.view.UsersView
import ru.stplab.gityoungclient2.mvp.view.list.UserItemView
import ru.stplab.gityoungclient2.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject lateinit var retrofitUsersRepo: IGitUsersRepo
    @Inject lateinit var router: Router
    @Inject lateinit var uiScheduler: Scheduler


    class UsersListPresenter : IUserListPresenter {
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GitUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatarUrl?.let { view.loadImage(it) }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()
    var disposableRxUsers: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(Screens.RepositoriesUserScreen(user))
        }
    }

    private fun loadData() {
        disposableRxUsers = retrofitUsersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe { users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableRxUsers?.dispose()
    }
}