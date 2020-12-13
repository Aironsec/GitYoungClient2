package ru.stplab.gityoungclient2.mvp.model.repo.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser

interface ICacheGitUsersRepo {

    fun putUsers(users: List<GitUser>): Completable
    fun getUsers(): Single<List<GitUser>>
}