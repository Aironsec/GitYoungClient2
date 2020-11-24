package ru.stplab.gityoungclient2.mvp.view.list

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IItemView: MvpView {
    var pos: Int
}