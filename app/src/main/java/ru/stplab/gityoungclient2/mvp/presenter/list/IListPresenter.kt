package ru.stplab.gityoungclient2.mvp.presenter.list

import ru.stplab.gityoungclient2.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}