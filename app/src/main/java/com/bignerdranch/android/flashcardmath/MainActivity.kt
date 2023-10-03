package com.bignerdranch.android.flashcardmath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var login_button: Button
    lateinit var username_input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button = findViewById(R.id.login_button)
        username_input = findViewById(R.id.username_input)

        login_button.setOnClickListener {
            val str = username_input.text.toString()
            val intent = Intent(applicationContext, FlashcardActivity::class.java)

            intent.putExtra("message_key", str)
            startActivity(intent)
        }
    }
}