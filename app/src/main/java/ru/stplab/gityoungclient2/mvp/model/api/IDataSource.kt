package ru.stplab.gityoungclient2.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.stplab.gityoungclient2.mvp.model.entity.GitUser
import ru.stplab.gityoungclient2.mvp.model.entity.RepositoryUser

interface IDataSource {

    @GET("users")
    fun getUsers(): Single<List<GitUser>>

//    @GET("users/{login}")
//    fun getUser(@Path("login") login: String): Single<GitUser>
//
    @GET
    fun getUserRepos(@Url url: String): Single<List<RepositoryUser>>
}