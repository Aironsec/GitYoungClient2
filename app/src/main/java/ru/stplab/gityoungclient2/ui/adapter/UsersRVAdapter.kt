package ru.stplab.gityoungclient2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*
import ru.stplab.gityoungclient2.R
import ru.stplab.gityoungclient2.mvp.model.image.IImageLoader
import ru.stplab.gityoungclient2.mvp.presenter.list.IUserListPresenter
import ru.stplab.gityoungclient2.mvp.view.list.UserItemView

class UsersRvAdapter(
    private val presenter: IUserListPresenter,
    val loadAvatar: IImageLoader<ImageView>
) : RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        ).apply {
            containerView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        UserItemView, LayoutContainer {
        override var pos = -1

        override fun setLogin(login: String) {
            tv_login.text = login
        }

        override fun loadImage(url: String) {
            loadAvatar.loadAvatar(url, iv_avatar)
        }
    }

}