package com.github.smkcoding.mubarak.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.smkcoding.mubarak.activity.MasjidActivity

import com.github.smkcoding.mubarak.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class homeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btMasjid.setOnClickListener {
            val intent = Intent(context, MasjidActivity::class.java)
            //intent.putExtra("year",btYear.text.toString())
            startActivity(intent)
        }
    }

}
