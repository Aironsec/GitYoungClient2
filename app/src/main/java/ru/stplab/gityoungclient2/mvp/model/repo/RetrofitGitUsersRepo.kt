package ru.stplab.gityoungclient2.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.stplab.gityoungclient2.mvp.model.api.IDataSource
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.network.INetworkState

class RetrofitGitUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkState,
    private val cache: ICacheGitUsersRepo
) : IGitUsersRepo {

    override fun getUsers(): Single<List<GitUser>> = networkStatus.isOnlineSingle().flatMap { status ->
        if (status) {
            api.getUsers().flatMap { users ->
                cache.putUsers(users).andThen(Single.just(users))
            }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}