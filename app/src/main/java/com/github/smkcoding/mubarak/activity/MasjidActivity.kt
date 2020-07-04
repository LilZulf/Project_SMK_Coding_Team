package com.github.smkcoding.mubarak.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.adapter.MasjidAdapter
import com.github.smkcoding.mubarak.model.MasjidModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_masjid.*
import kotlinx.android.synthetic.main.components_basic_actionbar.*

class MasjidActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var dataTarget : ArrayList<MasjidModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masjid)
        ref = FirebaseDatabase.getInstance().reference
        action_bar_title.text = "Masjid"
        getMasjidFirebase()

    }
    private fun getMasjidFirebase(){

        ref
            .child("Masjid")
            .addValueEventListener( object :
                ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(this@MasjidActivity ,"Database Error yaa..." ,
                        Toast. LENGTH_SHORT ).show()
                }
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataTarget = java.util.ArrayList()
                    for (snapshot in dataSnapshot. children ) {
                            //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                        val target = snapshot.getValue(MasjidModel:: class . java )
                        target?.key = snapshot.key!!
                        dataTarget.add(target!!)
                    }
                    rcMasjid.layoutManager = LinearLayoutManager(this@MasjidActivity)
                    rcMasjid.adapter = MasjidAdapter(this@MasjidActivity,dataTarget){}
                }
            })
    }
}
