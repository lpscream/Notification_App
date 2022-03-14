package net.matrixhome.notificationapp.api

import kotlinx.coroutines.flow.Flow
import net.matrixhome.notificationapp.model.PageRoom

class PageRoomApi(private val pageDao: PageDao) {

    private val TAG = "PageRoomApi_log"

    val allPages: Flow<MutableList<PageRoom>> = pageDao.getAll()


    suspend fun insert(page: PageRoom){
        pageDao.insertPage(pageroom = page)
    }

    suspend fun delete(page: PageRoom){
        pageDao.delete(pageroom = page)
    }





}