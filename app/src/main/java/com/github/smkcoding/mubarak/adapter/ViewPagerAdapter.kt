package com.github.smkcoding.mubarak.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.smkcoding.mubarak.fragment.articleFragment
import com.github.smkcoding.mubarak.fragment.bookFragment
import com.github.smkcoding.mubarak.fragment.homeFragment
import com.github.smkcoding.mubarak.fragment.profileFragment

class ViewPagerAdapter(fragmentActivity : FragmentActivity): FragmentStateAdapter(fragmentActivity){

    private val JumlahMenu = 4

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {return homeFragment()}
            1 -> {return articleFragment()}
            2 -> {return bookFragment()}
            3 -> {return  profileFragment()}
            else -> {
                return articleFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return  JumlahMenu
    }

}