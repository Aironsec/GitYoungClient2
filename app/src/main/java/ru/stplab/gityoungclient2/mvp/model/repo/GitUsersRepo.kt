package ru.stplab.gityoungclient2.mvp.model.repo

import io.reactivex.rxjava3.core.Observable
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser

class GitUsersRepo {
    private val repo = listOf(
        GitUser("User1"),
        GitUser("User2"),
        GitUser("User3"),
        GitUser("User4"),
        GitUser("User5")
    )

//    fun getUsers(): Observable<GitUser> = Observable.just(repo).flatMap {
//        return@flatMap  Observable.fromIterable(it)
//    }

    fun getUsers(): Observable<GitUser> = Observable.fromIterable(repo).flatMap {
        return@flatMap  Observable.just(it)
    }
}