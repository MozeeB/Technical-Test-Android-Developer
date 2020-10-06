package com.example.yogyakarta.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.yogyakarta.main.MainActivity
import com.example.yogyakarta.R
import com.example.yogyakarta.register.RegisterActivity
import com.orhanobut.hawk.Hawk
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContruct.View, View.OnClickListener {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        cardView.setBackgroundResource(R.drawable.cardtoponly)

        presenter = LoginPresenter(this)
        btnSigninLogin.setOnClickListener(this)
        tvSignUpLogin.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSigninLogin->{
                presenter.doLogin(edtNameLogin.text.toString(), edtPassLogin.text.toString())
            }
            R.id.tvSignUpLogin->{
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }
    override fun getDataUser(name: String, email: String) {
        Hawk.put("name", name)
        Hawk.put("email", email)
    }

    override fun gettoken(token: String?) {
        Hawk.put("token", token)
    }

    override fun onSuccess(messaeg: String) {
        val inten = Intent(this, MainActivity::class.java)
        startActivity(inten)
        finish()
    }

    override fun onFailed(messaeg: String) {
        Toasty.error(this, "Login failed!", Toasty.LENGTH_SHORT).show()
    }

    override fun showLogging() {
        Toasty.success(this, "Login Successfully!", Toasty.LENGTH_LONG).show()
    }

    override fun showToastValidation() {
        Toasty.error(this, "Email and Password cannot be empty!", Toasty.LENGTH_LONG).show()
    }

    override fun showLoading() {
        linear.visibility = View.GONE
        constainProgress.visibility = View.VISIBLE
        indicator.show()
    }

    override fun hidelLoading() {
        indicator.hide()
        linear.visibility = View.VISIBLE
        constainProgress.visibility = View.GONE
    }

    override fun onBackPressed() {
//halo pakde
    }
}
