package ru.stplab.gityoungclient2.mvp.model.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.RoomGitUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.db.Database

class CacheGitUsersRepo(private val db: Database) : ICacheGitUsersRepo {

    override fun putUsers(users: List<GitUser>): Completable =
        Completable.create { emitter ->
            val roomUsers = users.map { users ->
                RoomGitUser(
                    users.id,
                    users.login,
                    users.avatarUrl ?: "",
                    users.reposUrl ?: ""
                )
            }
            db.userDao.insert(roomUsers)
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())

    override fun getUsers(): Single<List<GitUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GitUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }.subscribeOn(Schedulers.io())


}