package com.github.smkcoding.mubarak.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.activity.DoaActivity
import com.github.smkcoding.mubarak.activity.Kajian
import com.github.smkcoding.mubarak.activity.KisahActivity
import com.github.smkcoding.mubarak.activity.MasjidActivity
import com.github.smkcoding.mubarak.adapter.ArticleAdapter
import com.github.smkcoding.mubarak.model.ArticleModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class homeFragment : Fragment() {
    lateinit var ref : DatabaseReference
    lateinit var dataTarget : ArrayList<ArticleModel>
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ref = FirebaseDatabase.getInstance().reference
        getArticleFirebase()
        auth = FirebaseAuth.getInstance()
        tvEmail.text = auth.currentUser!!.email
        btMasjid.setOnClickListener {
            val intent = Intent(context, MasjidActivity::class.java)
            //intent.putExtra("year",btYear.text.toString())
            startActivity(intent)
        }

        btn_kajian.setOnClickListener {
            val intent = Intent(context,Kajian::class.java)
            startActivity(intent)

        }
        btnDoa.setOnClickListener {
            val intent = Intent(context, DoaActivity::class.java)
            startActivity(intent)
        }
        btnKisahNabi.setOnClickListener {
            val intent = Intent(context, KisahActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getArticleFirebase(){

        ref
            .child("Artikel")
            .addValueEventListener( object :
                ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(requireContext() ,"Database Error yaa..." ,
                        Toast. LENGTH_SHORT ).show()
                }
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataTarget = java.util.ArrayList()
                    for (snapshot in dataSnapshot. children ) {
                        //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                        val target = snapshot.getValue(ArticleModel:: class . java )
                        target?.key = snapshot.key!!
                        dataTarget.add(target!!)
                    }
                    val layoutManager = LinearLayoutManager(requireContext())
                    layoutManager.stackFromEnd = true
                    layoutManager.reverseLayout = true
                    rcArticle.layoutManager = layoutManager

                    rcArticle.adapter = ArticleAdapter(requireContext(),dataTarget){}
                }
            })
    }

}
