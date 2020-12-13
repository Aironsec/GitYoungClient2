package ru.stplab.gityoungclient2.mvp.model.repo.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.RepositoryUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.RoomRepositoryUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.db.Database

class CacheRepositoriesUserRepo(private val db: Database) : ICacheRepositoriesUserRepo {

    override fun putRepositories(user: GitUser, repos: List<RepositoryUser>): Completable =
        Completable.create { emitter ->
            val roomUser = db.userDao.findByLogin(user.login)
            val roomRepo = repos.map { repo ->
                RoomRepositoryUser(
                    repo.id,
                    repo.name,
                    repo.forks,
                    roomUser.id
                )
            }
            db.repositoryUserDao.insert(roomRepo)
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())

    override fun getRepositories(user: GitUser): Single<List<RepositoryUser>> =
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login)
            db.repositoryUserDao.findForUser(roomUser.id).map { repo ->
                RepositoryUser(repo.id, repo.name, repo.forks)
            }
        }.subscribeOn(Schedulers.io())


}