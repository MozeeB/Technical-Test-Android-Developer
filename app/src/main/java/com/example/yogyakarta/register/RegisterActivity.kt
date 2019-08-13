package com.example.yogyakarta.register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.yogyakarta.R
import com.example.yogyakarta.login.LoginActivity
import dmax.dialog.SpotsDialog
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContruct.View, View.OnClickListener {


    lateinit var presenter: RegisterPresenter

    var dialog:android.app.AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        cvSignup.setBackgroundResource(R.drawable.cardtoponly)

        presenter = RegisterPresenter(this)
        btnSignup.setOnClickListener(this)

        dialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Loading...")
            .setCancelable(false)
            .build()

    }

    override fun onClick(v: View?) {
        presenter.doRegister(edtNameSignup.text.toString(), edtEmailSignUp.text.toString(), edtPassSignUp.text.toString(), edtCPassSignUp.text.toString())
    }



    override fun onSuccess(message: String) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onFailed(message: String) {
        Toasty.error(this, "Failed to sign up", Toasty.LENGTH_SHORT).show()
    }

    override fun showToastValid() {
        Toasty.error(this, "Field cannot be empty or confirm password is not same", Toasty.LENGTH_LONG).show()
    }

    override fun showProgress() {
        dialog?.show()
    }

    override fun showLogging() {
        Toasty.success(this, "Login Successfully!", Toasty.LENGTH_LONG).show()
    }

    override fun hideProgress() {
        dialog?.dismiss()
    }
}
