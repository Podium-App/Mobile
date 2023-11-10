package xyz.thisisjames.boulevard.android.podium.destinations.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.R
import xyz.thisisjames.boulevard.android.podium.databinding.ActivityTestBinding
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import xyz.thisisjames.boulevard.android.podium.destinations.connectivity.ConnectivityActivity
import xyz.thisisjames.boulevard.android.podium.destinations.home.HomeActivity
import xyz.thisisjames.boulevard.android.podium.models.ExamRequest
import javax.inject.Inject

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {


    private lateinit var binding: ActivityTestBinding

    @Inject lateinit var testViewModel:TestViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        WindowCompat.setDecorFitsSystemWindows(window,false);
        setContentView(binding.root)


        val decorView = window.decorView
        val uioptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uioptions

    }


    override fun onBackPressed() {
        val mySnackbar = Snackbar.make(binding.root,
            getString(R.string.are_you_sure_you_want_to_leave), Snackbar.LENGTH_SHORT)
        mySnackbar.setAction(getString(R.string.yes), { wrapUp.invoke() })
        mySnackbar.show()
    }


    val wrapUp : ()->Unit = {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}