package ru.stplab.gityoungclient2.mvp.model.repo

import ru.stplab.gityoungclient2.mvp.model.entity.GitUser

class GitUsersRepo {
    private val repo = listOf(
        GitUser("User1"),
        GitUser("User2"),
        GitUser("User3"),
        GitUser("User4"),
        GitUser("User5")
    )

    fun getUsers(): List<GitUser> {
        return repo
    }
}