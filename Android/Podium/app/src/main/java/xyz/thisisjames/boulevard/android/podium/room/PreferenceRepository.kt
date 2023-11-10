package xyz.thisisjames.boulevard.android.podium.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class PreferenceRepository(private val prefDao:PreferenceDao) {
    val userStatus : LiveData<List<Preferences>> = prefDao.getUserStatus()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(status: Preferences) {
        prefDao.update(status)
    }
}