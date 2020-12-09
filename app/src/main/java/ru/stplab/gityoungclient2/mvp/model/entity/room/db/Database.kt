package ru.stplab.gityoungclient2.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.stplab.gityoungclient2.mvp.model.entity.room.RoomGitUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.RoomRepositoryUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.dao.RepositoryUserDao
import ru.stplab.gityoungclient2.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomGitUser::class, RoomRepositoryUser::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryUserDao: RepositoryUserDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database не создана!!")
        fun create(context: Context) = instance ?: let {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
            }

    }
}