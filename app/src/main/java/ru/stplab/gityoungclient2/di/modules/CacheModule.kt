package ru.stplab.gityoungclient2.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.stplab.gityoungclient2.mvp.model.entity.room.db.Database
import ru.stplab.gityoungclient2.mvp.model.entity.room.db.Database.Companion.DB_NAME
import ru.stplab.gityoungclient2.mvp.model.repo.cache.CacheGitUsersRepo
import ru.stplab.gityoungclient2.mvp.model.repo.cache.CacheRepositoriesUserRepo
import ru.stplab.gityoungclient2.mvp.model.repo.cache.ICacheGitUsersRepo
import ru.stplab.gityoungclient2.mvp.model.repo.cache.ICacheRepositoriesUserRepo
import ru.stplab.gityoungclient2.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun userCache(database: Database): ICacheGitUsersRepo = CacheGitUsersRepo(database)

    @Singleton
    @Provides
    fun repoCache(database: Database): ICacheRepositoriesUserRepo = CacheRepositoriesUserRepo(database)
}