package com.github.smkcoding.mubarak.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity
import com.github.smkcoding.mubarak.R
import com.github.smkcoding.mubarak.Util.toast

class SplashScreen : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val cm = this@SplashScreen.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            if(isConnected){
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                toast(this@SplashScreen,"Aplikasi tidak bisa berjalan offline")
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }


}
