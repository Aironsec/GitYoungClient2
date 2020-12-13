package ru.stplab.gityoungclient2.ui.activity

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.stplab.gityoungclient2.R
import ru.stplab.gityoungclient2.mvp.presenter.MainPresenter
import ru.stplab.gityoungclient2.mvp.view.MainView
import ru.stplab.gityoungclient2.ui.App
import ru.stplab.gityoungclient2.ui.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClick()
    }

}