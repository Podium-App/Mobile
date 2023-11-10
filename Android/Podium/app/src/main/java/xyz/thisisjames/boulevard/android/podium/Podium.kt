package xyz.thisisjames.boulevard.android.podium

import android.app.Application
import android.net.ConnectivityManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import xyz.thisisjames.boulevard.android.podium.room.PreferenceRepository
import xyz.thisisjames.boulevard.android.podium.room.PreferenceRoomDataBase


@HiltAndroidApp
class Podium : Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts



}