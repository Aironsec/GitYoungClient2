package ru.stplab.gityoungclient2.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.stplab.gityoungclient2.mvp.model.api.IDataSource
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.RepositoryUser

class RetrofitGitUsersRepo (private val api:IDataSource): IGitUsersRepo {
    override fun getUsers(): Single<List<GitUser>> = api.getUsers().observeOn(Schedulers.io())
    override fun getRepositories(url: String): Single<List<RepositoryUser>> = api.getUserRepos(url).observeOn(Schedulers.io())
}