package com.example.yogyakarta

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.yogyakarta.login.LoginActivity
import com.example.yogyakarta.main.MainActivity
import com.orhanobut.hawk.Hawk

class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Hawk.contains("email")){
            mDelayHandler = Handler()
            mDelayHandler!!.postDelayed(mRunnable2, SPLASH_DELAY)
        }else{
            mDelayHandler = Handler()
            mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
        }


    }


    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
            mDelayHandler!!.removeCallbacks(mRunnable2)

        }

        super.onDestroy()
    }

    internal val mRunnable2: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }




}
