package ru.stplab.gityoungclient2.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.RepositoryUser
import ru.stplab.gityoungclient2.mvp.model.repo.IRepositoriesUserRepo
import ru.stplab.gityoungclient2.mvp.presenter.list.IRepositoryListPresenter
import ru.stplab.gityoungclient2.mvp.view.RepositoriesUserView
import ru.stplab.gityoungclient2.mvp.view.list.RepositoryItemView
import ru.terrakok.cicerone.Router

class RepositoriesUserPresenter(private val router: Router,
                                private val user: GitUser,
                                private val retrofitUsersRepo: IRepositoriesUserRepo,
                                private val uiScheduler: Scheduler
                                ): MvpPresenter<RepositoriesUserView>(){

    class RepositoriesUserPresenter : IRepositoryListPresenter {
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        val repositories = mutableListOf<RepositoryUser>()

        override fun bindView(view: RepositoryItemView) {
            val repo = repositories[view.pos]
            view.setNameRepository(repo.name)
            view.setCountForks(repo.forks)
        }

        override fun getCount() = repositories.size
    }

    val repoUserPresenter = RepositoriesUserPresenter()
    var disposableLoadData: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        disposableLoadData = retrofitUsersRepo.getRepositories(user)
            .observeOn(uiScheduler)
            .subscribe { repos ->
                repoUserPresenter.repositories.clear()
                repoUserPresenter.repositories.addAll(repos)
                viewState.updateList()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableLoadData?.dispose()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}