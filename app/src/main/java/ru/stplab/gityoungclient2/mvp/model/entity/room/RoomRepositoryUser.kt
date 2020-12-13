package ru.stplab.gityoungclient2.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomRepositoryUser(
    @PrimaryKey var id: String,
    var name: String,
    var forks: Int,
    var userId: String
)