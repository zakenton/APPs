package com.example.fractal001

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_singin : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var radioGroupSex: RadioGroup
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_singin)

        radioGroupSex = findViewById(R.id.radioGroupSex)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)

        dbHelper = DatabaseHelper(this)

        val userLogin: EditText = findViewById(R.id.user_login_singIn)
        val userEmail: EditText = findViewById(R.id.user_email_singIn)
        val userPassword: EditText = findViewById(R.id.user_password_singIn)
        val buttonSingIn: Button = findViewById(R.id.button_singIn)
        val linkTologIn: TextView = findViewById(R.id.linkToLog)

        linkTologIn.setOnClickListener {
            val intent = Intent(this, activity_login::class.java)
            startActivity(intent)
        }

        buttonSingIn.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPassword.text.toString().trim()
            val sex = when (radioGroupSex.checkedRadioButtonId) {
                R.id.radioMale -> "male"
                R.id.radioFemale -> "female"
                else -> ""
            }
            val credits = 1000
            val level = 1

            if (login.isEmpty() || email.isEmpty() || pass.isEmpty() || sex.isEmpty()) {
                Toast.makeText(this, "Fields are not filled", Toast.LENGTH_LONG).show()
            }
            if (login.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fields are not filled", Toast.LENGTH_LONG).show()
            } else {
                val user = User(login, email, pass, sex, credits, level)
                val result = dbHelper.addUser(user)

                if (result != -1L) {
                    Toast.makeText(this, "User $login registered successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
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