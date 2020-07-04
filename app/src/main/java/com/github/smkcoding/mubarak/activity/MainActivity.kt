package com.github.smkcoding.mubarak.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val menuTeks = arrayOf("Home","Al-Quran","Profil")
    val menuIcons = arrayOf(
        R.drawable.ic_home,
        R.drawable.ic_book,
        R.drawable.ic_icn_account
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ViewPagerAdapter(this)

        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy {
                tab, position ->
            tab.text = menuTeks[position]
            tab.icon = ResourcesCompat.getDrawable(resources,menuIcons[position], null)
        }).attach()
    }
}
