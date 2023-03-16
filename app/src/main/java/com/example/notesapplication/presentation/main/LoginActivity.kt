package com.example.notesapplication.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import com.example.notesapplication.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide();
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        btnLogin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        })


    }
}