package xyz.thisisjames.boulevard.android.podium.destinations.connectivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import javax.inject.Inject


@AndroidEntryPoint
class ConnectivityActivity : AppCompatActivity() {

    @Inject
    lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContentView(R.layout.activity_connectivity)

        val decorView = window.decorView
        val uioptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uioptions

        baseViewModel.getAllSubjects()
    }


    override fun onBackPressed() {

    }
}

