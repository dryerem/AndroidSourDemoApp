package com.example.sour

import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private lateinit var mDBHelper : DatabaseHelper
    private lateinit var mDb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

    fun pushMainView(view: View) {
        val mainView = Intent(this, MainActivity::class.java)
        startActivity(mainView)
    }

    fun pushWelcomeView(view: View) {

        val loginEditText: EditText = findViewById(R.id.activity_login_email_field)
        val passwordEditText: EditText = findViewById(R.id.activity_login_password_field)

        val login: String = loginEditText.text.toString()
        val password: String = passwordEditText.text.toString()

        if (login.isEmpty()) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        var access: Boolean = false

        val cursor: Cursor = mDb.rawQuery("SELECT Login, Password FROM user WHERE Login = '$login' AND Password = '$password'", null)
        if (cursor.count != 0) {
            access = true
        }
        cursor.close()

        if (access) {
            val welcomeView = Intent(this, Welcome::class.java)
            startActivity(welcomeView)
        }
        else {
            Toast.makeText(this, "Check that your login and password are correct", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}