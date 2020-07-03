package com.github.smkcoding.mubarak.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.smkcoding.mubarak.R
import kotlinx.android.synthetic.main.components_basic_actionbar.*

/**
 * A simple [Fragment] subclass.
 */
class profileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

        viewProps()
    }

    private fun viewProps() {
        action_bar_title.setText("Profile")
        action_gear.visibility = View.VISIBLE
    }

}
