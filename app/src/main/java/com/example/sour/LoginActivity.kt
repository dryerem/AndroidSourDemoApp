package com.example.sour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    public fun pushMainView(view: View) {
        val mainView = Intent(this, MainActivity::class.java)
        startActivity(mainView)
    }

    public fun pushWelcomeView(view: View) {
        val welcomeView = Intent(this, Welcome::class.java)
        startActivity(welcomeView)
    }
}