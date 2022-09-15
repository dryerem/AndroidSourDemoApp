package com.example.sour

import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.IOException

class CreateNewUser : AppCompatActivity() {
    private lateinit var mDBHelper : DatabaseHelper
    private lateinit var mDb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_user)

        mDBHelper = DatabaseHelper(this)

        try {
            mDBHelper.updateDataBase()
        } catch (mIOException: IOException) {
            throw Error("UnableToUpdateDatabase")
        }

        try {
            mDb = mDBHelper.writableDatabase
        } catch (mSQLException: SQLException) {
            throw mSQLException
        }
    }

    fun onBackButtonClick(view: View) {
        val mainView = Intent(this, MainActivity::class.java)
        startActivity(mainView)
    }

    fun onSignUpButtonClick(view: View) {
        val loginEditText: EditText = findViewById(R.id.activity_create_new_user_email_field)
        val passwordEditText: EditText = findViewById(R.id.activity_create_new_user_password_field)
        val repeatPasswordEditText: EditText =
            findViewById(R.id.activity_create_new_user_password_repeat_field)

        val login: String = loginEditText.text.toString()
        val password: String = passwordEditText.text.toString()
        val repeatPassword: String = repeatPasswordEditText.text.toString()

        if (login.isEmpty()) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (repeatPassword.isEmpty()) {
            Toast.makeText(this, "You did not enter a repeat password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (repeatPassword != password) {
            Toast.makeText(this, "You password don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        val cursor: Cursor = mDb.rawQuery("SELECT Login FROM user WHERE Login = '$login'", null)
        if (cursor.count != 0) {
            Toast.makeText(this, "The user already exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            val query: String = "INSERT INTO user (Login, Password) VALUES ('$login', '$password')"
            mDb.execSQL(query)

            val welcomeView = Intent(this, Welcome::class.java)
            startActivity(welcomeView)
        }
    }
}