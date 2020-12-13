package ru.stplab.gityoungclient2.di.modules

import dagger.Module
import dagger.Provides
import ru.stplab.gityoungclient2.mvp.model.api.IDataSource
import ru.stplab.gityoungclient2.mvp.model.network.INetworkState
import ru.stplab.gityoungclient2.mvp.model.repo.IGitUsersRepo
import ru.stplab.gityoungclient2.mvp.model.repo.IRepositoriesUserRepo
import ru.stplab.gityoungclient2.mvp.model.repo.RetrofitGitUsersRepo
import ru.stplab.gityoungclient2.mvp.model.repo.RetrofitRepositoriesUserRepo
import ru.stplab.gityoungclient2.mvp.model.repo.cache.ICacheGitUsersRepo
import ru.stplab.gityoungclient2.mvp.model.repo.cache.ICacheRepositoriesUserRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        userCache: ICacheGitUsersRepo,
        networkStatus: INetworkState
    ): IGitUsersRepo = RetrofitGitUsersRepo(api, networkStatus, userCache)

    @Singleton
    @Provides
    fun reposRepo(
        api: IDataSource,
        repoCache: ICacheRepositoriesUserRepo,
        networkStatus: INetworkState
    ): IRepositoriesUserRepo = RetrofitRepositoriesUserRepo(api, networkStatus, repoCache)
}