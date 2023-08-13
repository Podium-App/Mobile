package xyz.thisisjames.boulevard.android.podium

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import xyz.thisisjames.boulevard.android.podium.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var splashScreen: androidx.core.splashscreen.SplashScreen

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen();
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)

        val decorView = window.decorView
        val uioptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uioptions

    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish();
    }

}