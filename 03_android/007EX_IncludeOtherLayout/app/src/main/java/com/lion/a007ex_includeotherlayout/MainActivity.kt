package com.lion.a007ex_includeotherlayout

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a007ex_includeotherlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        activityMainBinding.apply {
            // 각 버튼에 리스너를 설정해준다.
            val buttonPlusListener = ButtonPlusListener()
            include3.buttonPlus.setOnClickListener(buttonPlusListener)

            val buttonMinusListener = ButtonMinusListener()
            include3.buttonMinus.setOnClickListener(buttonMinusListener)

            val buttonMultiListener = ButtonMultiListener()
            include3.buttonMulti.setOnClickListener(buttonMultiListener)

            val buttonSubListener = ButtonSubListener()
            include3.buttonSub.setOnClickListener(buttonSubListener)
        }
    }

    // 각 버튼에 설정할 리스너
    inner class ButtonPlusListener : OnClickListener{
        override fun onClick(v: View?) {
            activityMainBinding.apply {
                // 입력한 숫자를 가져온다.
                val a1 = include1.editTextNumber1.text.toString().toInt()
                val a2 = include1.editTextNumber2.text.toString().toInt()
                // 연산한다.
                val a3 = a1 + a2
                // 출력한다.
                include2.textViewResult.text = "$a1 + $a2 = $a3"
            }
        }
    }

    inner class ButtonMinusListener : OnClickListener{
        override fun onClick(v: View?) {
            activityMainBinding.apply {
                // 입력한 숫자를 가져온다.
                val a1 = include1.editTextNumber1.text.toString().toInt()
                val a2 = include1.editTextNumber2.text.toString().toInt()
                // 연산한다.
                val a3 = a1 - a2
                // 출력한다.
                include2.textViewResult.text = "$a1 - $a2 = $a3"
            }
        }
    }

    inner class ButtonMultiListener : OnClickListener{
        override fun onClick(v: View?) {
            activityMainBinding.apply {
                // 입력한 숫자를 가져온다.
                val a1 = include1.editTextNumber1.text.toString().toInt()
                val a2 = include1.editTextNumber2.text.toString().toInt()
                // 연산한다.
                val a3 = a1 * a2
                // 출력한다.
                include2.textViewResult.text = "$a1 * $a2 = $a3"
            }
        }
    }

    inner class ButtonSubListener : OnClickListener{
        override fun onClick(v: View?) {
            activityMainBinding.apply {
                // 입력한 숫자를 가져온다.
                val a1 = include1.editTextNumber1.text.toString().toInt()
                val a2 = include1.editTextNumber2.text.toString().toInt()
                // 연산한다.
                val a3 = a1 / a2
                // 출력한다.
                include2.textViewResult.text = "$a1 / $a2 = $a3"
            }
        }
    }
}