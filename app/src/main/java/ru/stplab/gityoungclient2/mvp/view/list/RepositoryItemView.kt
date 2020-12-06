package ru.stplab.gityoungclient2.mvp.view.list

interface RepositoryItemView: IItemView {

    fun setNameRepository(repoName: String)
    fun setCountForks(forksCount: Int)
}