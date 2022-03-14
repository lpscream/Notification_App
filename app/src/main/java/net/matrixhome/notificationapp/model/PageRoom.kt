package net.matrixhome.notificationapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PageRoom(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "lastViewed") var lastViewed: Boolean = false
)
