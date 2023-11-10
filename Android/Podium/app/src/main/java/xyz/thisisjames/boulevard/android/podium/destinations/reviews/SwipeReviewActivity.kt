package xyz.thisisjames.boulevard.android.podium.destinations.reviews

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.podium.destinations.reviews.ui.main.SectionsPagerAdapter
import xyz.thisisjames.boulevard.android.podium.databinding.ActivitySwipeReviewBinding
import xyz.thisisjames.boulevard.android.podium.destinations.test.TestViewModel
import javax.inject.Inject


@AndroidEntryPoint
class SwipeReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySwipeReviewBinding

    @Inject
    lateinit var testViewModel: TestViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false);

        binding = ActivitySwipeReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val decorView = window.decorView
        val uioptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uioptions



        val sectionsPagerAdapter = SectionsPagerAdapter(testViewModel.length, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        binding.testSubject.text = testViewModel.subjectname+" Assessment Review"


        viewPager.addOnPageChangeListener(object : OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                testViewModel.setupReviewStats(position)
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


        testViewModel.testReviewHelper.qs.observe(this, Observer {
            binding.reviewStatus.text = it
        })

        testViewModel.testReviewHelper.qnt.observe(this, Observer {
            binding.testProgress.text = it
        })

    }


}

