package ru.stplab.gityoungclient2.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import ru.stplab.gityoungclient2.mvp.model.entity.room.RoomGitUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.RoomRepositoryUser
import ru.stplab.gityoungclient2.mvp.model.entity.room.dao.RepositoryUserDao
import ru.stplab.gityoungclient2.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomGitUser::class, RoomRepositoryUser::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryUserDao: RepositoryUserDao

    companion object {
        const val DB_NAME = "database.db"
        // TODO: 13.12.2020  в даггер
//        private var instance: Database? = null
//        fun getInstance() = instance ?: throw RuntimeException("Database не создана!!")
//        fun create(context: Context) = instance ?: let {
//                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
//            }

    }
}