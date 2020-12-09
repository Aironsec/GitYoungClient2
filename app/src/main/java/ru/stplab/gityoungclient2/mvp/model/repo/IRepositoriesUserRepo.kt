package ru.stplab.gityoungclient2.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.RepositoryUser

interface IRepositoriesUserRepo {
    fun getRepositories(user: GitUser): Single<List<RepositoryUser>>
}