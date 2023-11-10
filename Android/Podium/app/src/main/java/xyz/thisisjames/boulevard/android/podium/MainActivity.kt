package xyz.thisisjames.boulevard.android.podium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.destinations.connectivity.ConnectivityActivity
import xyz.thisisjames.boulevard.android.podium.databinding.ActivityMainBinding
import xyz.thisisjames.boulevard.android.podium.destinations.onboarding.OnboardingActivity
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import xyz.thisisjames.boulevard.android.podium.room.PreferenceViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var splashScreen: androidx.core.splashscreen.SplashScreen

    private val baseViewModel: BaseViewModel by viewModels()

    private lateinit var preferences : PreferenceViewModel



    private var myHandler: Handler = Handler();
        var myRunnable = Runnable{

            var intent : Intent;

            intent = if( baseViewModel.currentUser) {
                Intent(this, ConnectivityActivity::class.java)
            }else {
                Intent(this, OnboardingActivity::class.java)
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen();

        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val decorView = window.decorView
        val uioptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uioptions

        preferences = ViewModelProvider (this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[PreferenceViewModel::class.java]

        baseViewModel.retrieveStatus(application)
        myHandler.postDelayed(myRunnable, baseViewModel.TIMECLOCK)


    }


    override fun onDestroy() {
        super.onDestroy()
        myHandler.removeCallbacks(myRunnable);
    }



    override fun onBackPressed() {
         finish();
    }



}