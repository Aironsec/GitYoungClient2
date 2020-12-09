package ru.stplab.gityoungclient2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.stplab.gityoungclient2.R
import ru.stplab.gityoungclient2.mvp.model.api.ApiHolder
import ru.stplab.gityoungclient2.mvp.model.entity.room.db.Database
import ru.stplab.gityoungclient2.mvp.model.repo.CacheGitUsersRepo
import ru.stplab.gityoungclient2.mvp.model.repo.RetrofitGitUsersRepo
import ru.stplab.gityoungclient2.mvp.presenter.UsersPresenter
import ru.stplab.gityoungclient2.mvp.view.UsersView
import ru.stplab.gityoungclient2.ui.App
import ru.stplab.gityoungclient2.ui.BackButtonListener
import ru.stplab.gityoungclient2.ui.adapter.UsersRvAdapter
import ru.stplab.gityoungclient2.ui.image.GlideImageLoader
import ru.stplab.gityoungclient2.ui.network.AndroidNetworkStatus

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(App.instance.router, RetrofitGitUsersRepo(ApiHolder.api, AndroidNetworkStatus(requireContext()), CacheGitUsersRepo(Database.getInstance())), AndroidSchedulers.mainThread())
    }

    private val adapter by lazy {
        UsersRvAdapter(presenter.usersListPresenter, GlideImageLoader())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}