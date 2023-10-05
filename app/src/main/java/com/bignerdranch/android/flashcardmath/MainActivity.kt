package com.bignerdranch.android.flashcardmath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.login_button)
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton.setOnClickListener { view: View ->

        }

        loginButton.setOnClickListener {
            if (usernameInput.text.toString() == "user" && passwordInput.text.toString() == "pass") {
                Toast.makeText(this, "You entered the correct crendetials", Toast.LENGTH_LONG)
            }
            val str = usernameInput.text.toString()
            val intent = Intent(applicationContext, FlashcardActivity::class.java)

            intent.putExtra("message_key", str)
            startActivity(intent)
        }
    }
}