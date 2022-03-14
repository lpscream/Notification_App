package net.matrixhome.notificationapp.api

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import net.matrixhome.notificationapp.model.PageRoom

@Dao
interface PageDao {

    @Query("SELECT * FROM pageroom")
    fun getAll(): Flow<MutableList<PageRoom>>

    @Query("SELECT COUNT(*) FROM pageroom")
    fun getCount(): Int

    @Insert
    suspend fun insertPage(pageroom: PageRoom)

    @Delete
    suspend fun delete(pageroom: PageRoom)

    @Update
    suspend fun updatePage(pageroom: PageRoom)
}