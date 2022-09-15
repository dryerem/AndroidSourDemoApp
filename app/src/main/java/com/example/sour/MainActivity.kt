package com.example.sour

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.window.SplashScreen
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSignUpButtonClick(view: View) {
        val signUpIntent = Intent(this, CreateNewUser::class.java)
        startActivity(signUpIntent)
    }

    public fun pushLoginPage(view: View) {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }
}