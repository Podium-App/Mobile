package xyz.thisisjames.boulevard.android.podium.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity


class Shared(private val activity: FragmentActivity) {

    val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
    }

    fun changeStatus(loggedin: Boolean) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Status", loggedin)
        editor.apply()
    }


    fun retrieveStatus(): Boolean {
        return sharedPreferences.getBoolean("Status", false)
    }

}