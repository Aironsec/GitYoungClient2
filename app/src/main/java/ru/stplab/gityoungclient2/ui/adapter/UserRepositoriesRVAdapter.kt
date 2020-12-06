package ru.stplab.gityoungclient2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.android.synthetic.main.item_user_repositories.*
import ru.stplab.gityoungclient2.R
import ru.stplab.gityoungclient2.mvp.model.image.IImageLoader
import ru.stplab.gityoungclient2.mvp.presenter.list.IRepositoryListPresenter
import ru.stplab.gityoungclient2.mvp.presenter.list.IUserListPresenter
import ru.stplab.gityoungclient2.mvp.view.list.RepositoryItemView
import ru.stplab.gityoungclient2.mvp.view.list.UserItemView

class UserRepositoriesRvAdapter(
    private val presenter: IRepositoryListPresenter
) : RecyclerView.Adapter<UserRepositoriesRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_repositories, parent, false)
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
        RepositoryItemView, LayoutContainer {
        override var pos = -1

        override fun setNameRepository(repoName: String) {
            tv_repo_name.text = repoName
        }

        override fun setCountForks(forksCount: Int) {
            tv_repo_forks.text = forksCount.toString()
        }

    }

}