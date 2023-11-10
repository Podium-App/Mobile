package xyz.thisisjames.boulevard.android.podium.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Preferences::class), version = 1, exportSchema = false)
abstract  class PreferenceRoomDataBase : RoomDatabase() {

    abstract fun preferenceDao() : PreferenceDao

    companion object {

        @Volatile
        private var INSTANCE : PreferenceRoomDataBase? = null


        fun getDatabase(context : Context ): PreferenceRoomDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PreferenceRoomDataBase::class.java,
                    "podium_database"
                    ).build()
                INSTANCE = instance

                instance
            }
        }


    }

}

