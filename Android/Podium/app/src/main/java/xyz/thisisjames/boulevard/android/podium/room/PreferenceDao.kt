package xyz.thisisjames.boulevard.android.podium.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface PreferenceDao {
    @Query("SELECT * FROM preference_table order by id ASC")
    fun getUserStatus(): LiveData<List<Preferences>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(status: Preferences)

    @Update
    suspend fun update(status: Preferences)


    @Query("DELETE FROM preference_table")
    suspend fun deleteAll()

}