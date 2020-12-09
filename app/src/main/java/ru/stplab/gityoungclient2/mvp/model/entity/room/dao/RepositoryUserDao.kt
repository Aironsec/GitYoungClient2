package ru.stplab.gityoungclient2.mvp.model.entity.room.dao


import androidx.room.*
import ru.stplab.gityoungclient2.mvp.model.entity.room.RoomRepositoryUser

@Dao
interface RepositoryUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<RoomRepositoryUser>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repos: RoomRepositoryUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RoomRepositoryUser)

    @Update
    fun update(repos: List<RoomRepositoryUser>)

    @Update
    fun update(vararg repos: RoomRepositoryUser)

    @Update
    fun update(repo: RoomRepositoryUser)

    @Delete
    fun delete(repos: List<RoomRepositoryUser>)

    @Delete
    fun delete(vararg repos: RoomRepositoryUser)

    @Delete
    fun delete(repo: RoomRepositoryUser)

    @Query("SELECT * FROM RoomRepositoryUser")
    fun getAll(): List<RoomRepositoryUser>

    @Query("SELECT * FROM RoomRepositoryUser WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomRepositoryUser>
    
}