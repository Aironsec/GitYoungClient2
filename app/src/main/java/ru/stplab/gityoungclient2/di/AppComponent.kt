package ru.stplab.gityoungclient2.di

import dagger.Component
import ru.stplab.gityoungclient2.di.modules.*
import ru.stplab.gityoungclient2.mvp.presenter.MainPresenter
import ru.stplab.gityoungclient2.mvp.presenter.RepositoriesUserPresenter
import ru.stplab.gityoungclient2.mvp.presenter.UsersPresenter
import ru.stplab.gityoungclient2.ui.activity.MainActivity
import ru.stplab.gityoungclient2.ui.adapter.UsersRvAdapter
import ru.stplab.gityoungclient2.ui.fragment.UsersFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        NavigationModule::class,
        CacheModule::class,
        RepoModule::class,
        ImageModule::class
    ]
)

interface AppComponent {
    fun inject(repositoriesUserPresenter: RepositoriesUserPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(usersFragment: UsersFragment)
}