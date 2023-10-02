package com.bignerdranch.android.flashcardmath


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

val SIZE = 10
var currentNumber: Int = 0
var topOperandArray: IntArray = IntArray(SIZE)
var bottomOperandArray:IntArray = IntArray(SIZE)
var operationsArray:IntArray = IntArray(SIZE)
var problemsCorrect:Int = 0
var correctAnswer:Int = 0


private lateinit var  firstOperand:TextView
private lateinit var secondOperand: TextView
private lateinit var operation:TextView
private lateinit var inputBox: TextInputEditText
private lateinit var submit: Button
private lateinit var generateNum: Button
class FlashcardActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flashcard_main)
        firstOperand = findViewById(R.id.firstOperand)
        secondOperand = findViewById(R.id.secondOperand)
        operation = findViewById(R.id.operation)
        inputBox = findViewById(R.id.inputBox)
        submit = findViewById(R.id.submitButton)
        generateNum = findViewById(R.id.generateNum)

        generateNum.setOnClickListener { view: View ->
            updateData()
        }

        submit.setOnClickListener { view: View ->
            handleSubmit()
        }

    }




    private fun updateData() {

        for (i in 0..SIZE) {

            topOperandArray[i] = Random.nextInt(1, 100)
            bottomOperandArray[i] = Random.nextInt(1, 21)
            operationsArray[i] = Random.nextInt(0, 2)


        }
        firstOperand.text = topOperandArray[currentNumber].toString()
        secondOperand.text = bottomOperandArray[currentNumber].toString()

        if (operationsArray[currentNumber].toString() == "1") {
            operation.text = "+"
        } else {
            operation.text = "-"
        }
        generateNum.isEnabled = false





    }

        private fun handleSubmit() {

            // Adding
            if (operationsArray[currentNumber].toString() == "1") {

                correctAnswer = topOperandArray[currentNumber] + bottomOperandArray[currentNumber]
                if (correctAnswer.toString() == inputBox.text.toString()) {
                    problemsCorrect++
                    Toast.makeText(this,"You got the problem correct",Toast.LENGTH_SHORT)
                } else {
                    Toast.makeText(this,"You got the problem incorrect",Toast.LENGTH_SHORT)
                }

            // Subtracting
            } else {


                correctAnswer = topOperandArray[currentNumber] - bottomOperandArray[currentNumber]
                if (correctAnswer.toString() == inputBox.text.toString()) {
                    problemsCorrect++
                    Toast.makeText(this,"You got the problem correct",Toast.LENGTH_SHORT)
                } else {
                    Toast.makeText(this,"You got the problem incorrect",Toast.LENGTH_SHORT)
                }


            }
            currentNumber++
            if (currentNumber > 9) {


                Toast.makeText(this,"You got " + problemsCorrect.toString() + "/10 correct!",Toast.LENGTH_SHORT)
                generateNum.isEnabled = true
                currentNumber = 0
                problemsCorrect = 0
            }



    }



}

