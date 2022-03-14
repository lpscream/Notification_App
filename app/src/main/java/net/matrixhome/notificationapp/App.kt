package net.matrixhome.notificationapp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import net.matrixhome.notificationapp.api.PageDataBase
import net.matrixhome.notificationapp.api.PageRoomApi

const val NOTIFICATION_ID = 101
const val NOTIFICATION_REQUEST_CODE = 0
const val CHANNEL_ID = "net.matrixhome.notification.channel_id"
const val FRAGMENT_ID = "fr_number"


class App: Application() {
    val dataBase by lazy {PageDataBase.getDataBase(this, CoroutineScope(SupervisorJob()))}
    val repo by lazy {PageRoomApi(dataBase.pageDao())}
}