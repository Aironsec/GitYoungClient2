package ru.stplab.gityoungclient2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.stplab.gityoungclient2.R
import ru.stplab.gityoungclient2.mvp.model.image.IImageLoader
import ru.stplab.gityoungclient2.mvp.presenter.UsersPresenter
import ru.stplab.gityoungclient2.mvp.view.UsersView
import ru.stplab.gityoungclient2.ui.App
import ru.stplab.gityoungclient2.ui.BackButtonListener
import ru.stplab.gityoungclient2.ui.adapter.UsersRvAdapter
import ru.stplab.gityoungclient2.ui.image.GlideImageLoader
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    private val presenter by moxyPresenter {
        UsersPresenter()
            .apply {
                App.instance.appComponent.inject(this)
            }
    }

    private val adapter by lazy {
        UsersRvAdapter(presenter.usersListPresenter, imageLoader)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        App.instance.appComponent.inject(this)
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}