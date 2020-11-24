package ru.stplab.gityoungclient2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_current_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.stplab.gityoungclient2.R
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.presenter.CurrentUserPresenter
import ru.stplab.gityoungclient2.mvp.view.CurrentUserView
import ru.stplab.gityoungclient2.ui.App
import ru.stplab.gityoungclient2.ui.BackButtonListener

class CurrentUserFragment : MvpAppCompatFragment(), CurrentUserView, BackButtonListener {

    companion object {
        private const val BUNDLE_USER = "user"

        fun newInstance(user: GitUser) = CurrentUserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_USER, user)
            }
        }
    }

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GitUser>(BUNDLE_USER) as GitUser
        CurrentUserPresenter(App.instance.router, user)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_current_user, null)

    override fun backPressed() = presenter.backClick()

    override fun setCurrentUser(user: String) {
        tv_login.text = user
    }
}