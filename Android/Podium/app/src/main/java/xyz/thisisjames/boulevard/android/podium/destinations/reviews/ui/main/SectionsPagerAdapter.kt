package xyz.thisisjames.boulevard.android.podium.destinations.reviews.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import xyz.thisisjames.boulevard.android.podium.R



class SectionsPagerAdapter(private val count: Int, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(position)
    }



    override fun getCount(): Int {
        return count
    }
}