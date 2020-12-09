package ru.stplab.gityoungclient2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_repositories_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.stplab.gityoungclient2.R
import ru.stplab.gityoungclient2.mvp.model.api.ApiHolder
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.db.Database
import ru.stplab.gityoungclient2.mvp.model.repo.CacheRepositoriesUserRepo
import ru.stplab.gityoungclient2.mvp.model.repo.RetrofitRepositoriesUserRepo
import ru.stplab.gityoungclient2.mvp.presenter.RepositoriesUserPresenter
import ru.stplab.gityoungclient2.mvp.view.RepositoriesUserView
import ru.stplab.gityoungclient2.ui.App
import ru.stplab.gityoungclient2.ui.BackButtonListener
import ru.stplab.gityoungclient2.ui.adapter.UserRepositoriesRvAdapter
import ru.stplab.gityoungclient2.ui.network.AndroidNetworkStatus

class UserRepositoriesFragment : MvpAppCompatFragment(), RepositoriesUserView, BackButtonListener {

    companion object {
        private const val BUNDLE_USER = "user"

        fun newInstance(user: GitUser) = UserRepositoriesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_USER, user)
            }
        }
    }

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GitUser>(BUNDLE_USER) as GitUser
        RepositoriesUserPresenter(App.instance.router, user, RetrofitRepositoriesUserRepo(ApiHolder.api, AndroidNetworkStatus(requireContext()), CacheRepositoriesUserRepo(Database.getInstance())), AndroidSchedulers.mainThread())
    }

    private val adapter by lazy {
        UserRepositoriesRvAdapter(presenter.repoUserPresenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_repositories_user, null)

    override fun backPressed() = presenter.backClick()

    override fun init() {
        rv_repositories_user.layoutManager = LinearLayoutManager(requireContext())
        rv_repositories_user.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }
}