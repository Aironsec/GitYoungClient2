package ru.stplab.gityoungclient2.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.stplab.gityoungclient2.mvp.model.api.IDataSource
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.RepositoryUser
import ru.stplab.gityoungclient2.mvp.model.network.INetworkState

class RetrofitRepositoriesUserRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkState,
    private val cache: ICacheRepositoriesUserRepo
) : IRepositoriesUserRepo {

    override fun getRepositories(user: GitUser): Single<List<RepositoryUser>> = networkStatus.isOnlineSingle().flatMap { status ->

        if (status) {
            user.reposUrl?.let { url ->
                api.getUserRepos(url).flatMap { repos ->
                    cache.putRepositories(user, repos).andThen(Single.just(repos))
                }
            } ?: Single.error<List<RepositoryUser>>(RuntimeException("У пользователя нет репозиториев"))
                    .subscribeOn(Schedulers.io())
        } else {
            cache.getRepositories(user)
        }
    }.subscribeOn(Schedulers.io())
}