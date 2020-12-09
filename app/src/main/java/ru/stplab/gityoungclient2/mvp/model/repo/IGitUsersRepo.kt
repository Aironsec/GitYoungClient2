package ru.stplab.gityoungclient2.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser

interface IGitUsersRepo {
    fun getUsers(): Single<List<GitUser>>
}