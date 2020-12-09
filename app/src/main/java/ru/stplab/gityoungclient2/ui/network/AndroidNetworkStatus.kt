package ru.stplab.gityoungclient2.ui.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.stplab.gityoungclient2.mvp.model.network.INetworkState

class AndroidNetworkStatus(context: Context): INetworkState {

    private val statusSubject = BehaviorSubject.createDefault(false)

    init {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val request = NetworkRequest.Builder().build()

        connectivityManager.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusSubject.onNext(true)
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                }
            })
    }

    override fun isOnlineSingle(): Single<Boolean> = statusSubject.first(false)

    override fun isOnline(): BehaviorSubject<Boolean> = statusSubject
}