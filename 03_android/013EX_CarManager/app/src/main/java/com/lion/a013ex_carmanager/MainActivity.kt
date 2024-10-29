package com.lion.a013ex_carmanager

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a013ex_carmanager.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val carList = mutableListOf<CarClass>()
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
            showKeyBoard(textFieldName.editText)

            buttonSave.setOnClickListener {
                saveData()
                resetInput()
            }

            textFieldDistance.editText?.setOnEditorActionListener { v, actionId, event ->
                saveData()
                resetInput()
                true
            }

            buttonShow.setOnClickListener {
                showCarInfo()
            }
        }
    }

    fun showKeyBoard(view: View?){
        view?.requestFocus()

        thread {
            Thread.sleep(1000)
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun saveData(){
        activityMainBinding.apply {
            val name = textFieldName.editText?.text.toString()
            val type = textFieldType.editText?.text.toString()
            val distance = textFieldDistance.editText?.text.toString().toInt()

            val carClass = CarClass(name, type, distance)
            carList.add(carClass)
        }
    }

    fun resetInput(){
        activityMainBinding.apply {
            textFieldName.editText?.setText("")
            textFieldType.editText?.setText("")
            textFieldDistance.editText?.setText("")

            textFieldName.editText?.requestFocus()
        }
    }

    fun showCarInfo(){
        carList.forEach {
            it.showCarInfo()
        }
    }
}