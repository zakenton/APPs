package com.example.fractal001

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_login : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        dbHelper = DatabaseHelper(this)

        val userLogin: EditText = findViewById(R.id.user_login_logIn)
        val userPussword: EditText = findViewById(R.id.user_password_logIn)
        val buttonLogin: Button = findViewById(R.id.button_logIn)
        val linkToSingIn: TextView = findViewById(R.id.linkToSingIn)

        linkToSingIn.setOnClickListener {
            val intent = Intent(this, activity_singin::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val login = userLogin.text.toString()
            val pass = userPussword.text.toString()

            if (login.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fields are not filled", Toast.LENGTH_LONG).show()
            } else {
                if (dbHelper.checkUser(login, pass)) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent1 = Intent(this, HomeActivity::class.java)
                    startActivity(intent1)
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}