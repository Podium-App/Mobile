package xyz.thisisjames.boulevard.android.podium.destinations.onboarding

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.Podium
import xyz.thisisjames.boulevard.android.podium.destinations.connectivity.ConnectivityActivity
import xyz.thisisjames.boulevard.android.podium.destinations.onboarding.ui.main.SectionsPagerAdapter
import xyz.thisisjames.boulevard.android.podium.databinding.ActivityOnboardingBinding
import xyz.thisisjames.boulevard.android.podium.dependencies.BaseViewModel
import xyz.thisisjames.boulevard.android.podium.destinations.home.HomeActivity
import xyz.thisisjames.boulevard.android.podium.room.PreferenceViewModel
import xyz.thisisjames.boulevard.android.podium.room.Preferences
import javax.inject.Inject


@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    @Inject
    lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)

        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContentView(binding.root)

        val decorView = window.decorView
        val uioptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uioptions


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: DotsIndicator = binding.dotsIndicator
        tabs.attachTo(viewPager)

        val fab: FloatingActionButton = binding.fab

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                fab.isVisible = position == 3
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        fab.setOnClickListener{

            val preferences = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[PreferenceViewModel::class.java]
            preferences.update(Preferences(0,"Userstatus","Known"))
            baseViewModel.changeStatus(application)
            destination()
        }

    }


    private fun destination(){
        var intent : Intent;

        if(baseViewModel.subjects.value != null ) {
            intent = Intent(this, HomeActivity::class.java)
        }else {
            intent = Intent(this, ConnectivityActivity::class.java)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


}