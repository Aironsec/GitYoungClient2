package ru.stplab.gityoungclient2.mvp.model.entity

import com.google.gson.annotations.Expose

data class RepositoryUser(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val forks: Int
)
