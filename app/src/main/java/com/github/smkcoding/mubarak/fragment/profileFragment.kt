package com.github.smkcoding.mubarak.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.adapter.BookmarkAdapter
import com.github.smkcoding.mubarak.adapter.SurahAdapter
import com.github.smkcoding.mubarak.viewmodel.BookmarkViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.components_basic_actionbar.*
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class profileFragment : Fragment() {
    private val viewModel by viewModels<BookmarkViewModel>()
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_bookmark.layoutManager = LinearLayoutManager(context)
        auth = FirebaseAuth.getInstance()
        viewModel.init(requireContext())
        setProfile()
        viewModel.allMyFriends.observe(viewLifecycleOwner, Observer {myBookmark ->
        // Update the cached copy of the words in the adapter.
            myBookmark?.let {
                rv_bookmark.adapter = BookmarkAdapter(requireContext(),it){}
            }

        })
        viewProps()

    }

    private fun viewProps() {
        action_bar_title.text = "Profile"
        action_gear.visibility = View.VISIBLE
    }
    private fun setProfile(){

        val username = auth.currentUser!!.displayName

        if(username != null){
            profile_name.text = username
            profile_email.text = auth.currentUser!!.email
            Glide.with(requireContext())
                .load(auth.currentUser!!.photoUrl)
                .into(profile_image)

        }
        else{
            profile_name.text = auth.currentUser!!.email
            profile_email.visibility = View.GONE
        }

    }

}
