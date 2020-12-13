package ru.stplab.gityoungclient2.mvp.model.network

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface INetworkState {
    fun isOnlineSingle(): Single<Boolean>
    fun isOnline(): Observable<Boolean>
}