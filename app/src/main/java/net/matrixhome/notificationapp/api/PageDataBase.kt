package net.matrixhome.notificationapp.api

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.matrixhome.notificationapp.model.PageRoom

private val TAG = "PageDataBase_log"

@Database(entities = [PageRoom::class], version = 1)
abstract class PageDataBase: RoomDatabase() {
    private val TAG = "PageDataBase_log"
    abstract fun pageDao(): PageDao


    companion object{
        @Volatile
        private var INSTANCE: PageDataBase? = null

        fun getDataBase(context: Context, scope: CoroutineScope): PageDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PageDataBase::class.java,
                    "page_database"
                ).addCallback(PageDataBaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class PageDataBaseCallback(
        private val scope: CoroutineScope)
        : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    Log.d(TAG, "onCreate: callback")
                    checkDatabase(database.pageDao())
                }
            }
        }

        suspend fun checkDatabase(pageDao: PageDao){
            pageDao.insertPage(PageRoom(0, false))
        }
    }
}




