package com.example.fractal001

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
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

class HomeActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        dbHelper = DatabaseHelper(this)

        val buttonUserIcon: Button = findViewById(R.id.button_userIcon)
        val creditAmount: TextView = findViewById(R.id.textView_credits)
        val buttonHome: Button = findViewById(R.id.button_home)
        val buttonTasks: Button = findViewById(R.id.button_tasks)
        val buttonTexts: Button = findViewById(R.id.button_texts)

        buttonUserIcon.setOnClickListener {
            showInputDialog()
        }

        buttonHome.setOnClickListener {
            val intent1 = Intent(this, HomeActivity::class.java)
            startActivity(intent1)
        }

        buttonTasks.setOnClickListener {
            val intent1 = Intent(this, HomeActivity::class.java)
            startActivity(intent1)
        }

        buttonTasks.setOnClickListener {
            val intent1 = Intent(this, HomeActivity::class.java)
            startActivity(intent1)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showInputDialog() {

    }
}