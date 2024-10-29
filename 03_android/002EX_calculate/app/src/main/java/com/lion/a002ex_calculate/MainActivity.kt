package com.lion.a002ex_calculate

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var inputnumber1: EditText
    lateinit var inputnumber2: EditText
    lateinit var result : TextView
    lateinit var plus_button : Button
    lateinit var minus_button : Button
    lateinit var multi : Button
    lateinit var devi : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputnumber1 = findViewById(R.id.inputNumber1)
        inputnumber2 = findViewById(R.id.inputNumber2)
        result = findViewById(R.id.result)
        plus_button = findViewById(R.id.plus_button)
        minus_button = findViewById(R.id.minus_button)
        multi = findViewById(R.id.multi)
        devi = findViewById(R.id.devi)

        val buttonListner1 = buttonListner1()
        val buttonListner2 = buttonListner2()
        val buttonListner3 = buttonListner3()
        val buttonListner4 = buttonListner4()

        plus_button.setOnClickListener(buttonListner1)
        minus_button.setOnClickListener(buttonListner2)
        multi.setOnClickListener(buttonListner3)
        devi.setOnClickListener(buttonListner4)
    }

    inner class buttonListner1 : OnClickListener{
        override fun onClick(v: View?) {
            var number1 = inputnumber1.text.toString().toInt()
            var number2 = inputnumber2.text.toString().toInt()
            var number3 = number1 + number2
            result.text = number3.toString()
        }
    }

    inner class buttonListner2 : OnClickListener{
        override fun onClick(v: View?) {
            var number1 = inputnumber1.text.toString().toInt()
            var number2 = inputnumber2.text.toString().toInt()
            var number3 = number1 - number2
            result.text = number3.toString()
        }
    }

    inner class buttonListner3 : OnClickListener{
        override fun onClick(v: View?) {
            var number1 = inputnumber1.text.toString().toInt()
            var number2 = inputnumber2.text.toString().toInt()
            var number3 = number1 * number2
            result.text = number3.toString()
        }
    }

    inner class buttonListner4 : OnClickListener{
        override fun onClick(v: View?) {
            var number1 = inputnumber1.text.toString().toInt()
            var number2 = inputnumber2.text.toString().toInt()
            var number3 = number1 / number2
            result.text = number3.toString()
        }
    }
}