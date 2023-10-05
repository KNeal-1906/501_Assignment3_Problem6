package com.bignerdranch.android.flashcardmath


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

val SIZE = 9
val NEG_SYMBOL = "0"
val POS_SYMBOL = "1"
var currentNumber: Int = 0
var topOperandArray: IntArray = IntArray(SIZE)
var bottomOperandArray:IntArray = IntArray(SIZE)
var operationsArray:IntArray = IntArray(SIZE)
var problemsCorrect:Int = 0
var correctAnswer:Int = 0


private lateinit var  firstOperand:TextView
private lateinit var secondOperand: TextView
private lateinit var operation:TextView
private lateinit var inputBox: EditText
private lateinit var submit: Button
private lateinit var generateNum: Button
class FlashcardActivity: AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        //Intent intent = getIntent()
        val intent = intent
        val str = intent.getStringExtra("message_key")

        Toast.makeText(this, "Welcome, $str",Toast.LENGTH_SHORT).show()


        setContentView(R.layout.flashcard_main)
        firstOperand = findViewById(R.id.firstOperand)
        secondOperand = findViewById(R.id.secondOperand)
        operation = findViewById(R.id.operation)
        inputBox = findViewById(R.id.editTextNumberSigned)
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

        for (i in 0 until SIZE) {

            topOperandArray[i] = Random.nextInt(1, 100)
            bottomOperandArray[i] = Random.nextInt(1, 21)
            operationsArray[i] = Random.nextInt(0, 2)


        }
        firstOperand.text = topOperandArray[currentNumber].toString()
        secondOperand.text = bottomOperandArray[currentNumber].toString()

        if (operationsArray[currentNumber].toString() == POS_SYMBOL) {
            operation.text = "+"
        } else {
            operation.text = "-"
        }
        generateNum.isEnabled = false





    }

    private fun handleSubmit() {

        // Adding
        if (operationsArray[currentNumber].toString() == POS_SYMBOL) {

            correctAnswer = topOperandArray[currentNumber] + bottomOperandArray[currentNumber]
            if (correctAnswer.toString() == inputBox.text.toString()) {
                problemsCorrect++
                Toast.makeText(this,"You got the problem correct",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"You got the problem incorrect",Toast.LENGTH_SHORT).show()
            }

            // Subtracting
        } else {


            correctAnswer = topOperandArray[currentNumber] - bottomOperandArray[currentNumber]
            if (correctAnswer.toString() == inputBox.text.toString()) {
                problemsCorrect++
                Toast.makeText(this,"You got the problem correct",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"You got the problem incorrect",Toast.LENGTH_SHORT).show()
            }


        }


        currentNumber++
        if (currentNumber >= SIZE) {
            Toast.makeText(this,"You got " + problemsCorrect.toString() + "/10 correct!",Toast.LENGTH_LONG).show()
            generateNum.isEnabled = true
            currentNumber = 0
            problemsCorrect = 0
            operation.text = ""
            firstOperand.text = ""
            secondOperand.text = ""


        } else {
            firstOperand.text = topOperandArray[currentNumber].toString()
            secondOperand.text = bottomOperandArray[currentNumber].toString()

            if(operationsArray[currentNumber] == 0) {
                operation.text = "-"
            } else {
                operation.text = "+"
            }
            inputBox.text.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val topOperandArray_save = topOperandArray
        val bottomOperandArray_save = bottomOperandArray
        val operationsArray_save = operationsArray
        val problemsCorrect_save = problemsCorrect
        val currentNumber_save = currentNumber

        outState.putInt("savedCurrentNumber", currentNumber)
        outState.putInt("savedProblemsCorrect", problemsCorrect)
        outState.putIntArray("savedBottomOperandArray", bottomOperandArray)
        outState.putIntArray("savedTopOperandArray", topOperandArray)
        outState.putIntArray("savedOperationsArray", operationsArray)


    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val userTopArray = savedInstanceState.getIntArray("savedTopOperandArray")
        val userBottomArray = savedInstanceState.getIntArray("savedBottomOperandArray")
        val userOperandArray = savedInstanceState.getIntArray("savedOperationsArray")
        val userScore = savedInstanceState.getInt("savedProblemsCorrect")
        val userCurrentProblem = savedInstanceState.getInt("savedCurrentNumber")

        currentNumber= userCurrentProblem

        if (userTopArray != null) {
            topOperandArray = userTopArray
        }
        if (userBottomArray != null) {
            bottomOperandArray = userBottomArray
        }
        if (userOperandArray != null) {
            operationsArray = userOperandArray
        }
        problemsCorrect = userScore

        firstOperand.text = topOperandArray[userCurrentProblem].toString()
        secondOperand.text = bottomOperandArray[userCurrentProblem].toString()

        if (operationsArray[userCurrentProblem].toString() == POS_SYMBOL) {
            operation.text = "+"
        } else {
            operation.text = "-"
        }
        generateNum.isEnabled = false

    }
}



