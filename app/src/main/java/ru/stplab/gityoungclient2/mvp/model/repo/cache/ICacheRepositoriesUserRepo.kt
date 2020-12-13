package ru.stplab.gityoungclient2.mvp.model.repo.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.RepositoryUser

interface ICacheRepositoriesUserRepo {

    fun putRepositories(user: GitUser, repos: List<RepositoryUser>): Completable
    fun getRepositories(user: GitUser): Single<List<RepositoryUser>>
}