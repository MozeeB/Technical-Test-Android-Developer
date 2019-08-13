package com.example.yogyakarta

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.example.yogyakarta.login.LoginActivity
import com.example.yogyakarta.maps.MapsActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        cvPro.setBackgroundResource(R.drawable.cardtoponly)

        val name:String = Hawk.get("name","")
        val email:String = Hawk.get("email", "")
        tvNameUser.setText(name)
        tvEmailUser.setText(email)

        btnSignOut.setOnClickListener(this)
        btnLocation.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLocation->{
                startActivity(Intent(applicationContext, MapsActivity::class.java))
            }
            R.id.btnSignOut->{
                AlertDialog.Builder(this)
                    // Judul
                    .setTitle("Sign out")
                    // Pesan yang di tamopilkan
                    .setMessage("Do you want to leave the account?")
                    .setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
                        Hawk.deleteAll()
                        startActivity(Intent(applicationContext, LoginActivity::class.java))
                        finish()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                    })
                    .show()

            }
        }

    }
}
