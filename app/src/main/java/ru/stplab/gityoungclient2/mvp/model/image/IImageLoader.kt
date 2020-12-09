package ru.stplab.gityoungclient2.mvp.model.image

interface IImageLoader<T> {
    fun loadAvatar(url: String, container: T)
}