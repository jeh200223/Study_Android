package com.lion.a017ex_animalmanager

import AnimalClass
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.checkbox.MaterialCheckBox
import com.lion.a017ex_animalmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val animalList = mutableListOf<AnimalClass>()
    var like = mutableListOf<String>()
    var type:String = ""
    var gender:String = ""

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
            checkBoxDesert.addOnCheckedStateChangedListener { checkBox, state ->
                when(state){
                    MaterialCheckBox.STATE_CHECKED -> {
                        checkApple.isChecked = true
                        checkBanana.isChecked = true
                        checkJujube.isChecked = true
                    }

                    MaterialCheckBox.STATE_UNCHECKED -> {
                        checkApple.isChecked = false
                        checkBanana.isChecked = false
                        checkJujube.isChecked = false
                    }
                }
            }

            buttonSave.setOnClickListener {
                type = getAnimalType()
                like = getAnimalLike()
                gender = getAnimalGender()
                saveData(type, like, gender)
                resetInfo()
            }

            buttonShow.setOnClickListener {
                showAnimalInfo()
            }
        }
    }

    fun getAnimalType(): String {
        activityMainBinding.apply {
            type = when {
                radioDog.isChecked -> "강아지"
                radioCat.isChecked -> "고양이"
                radioParrot.isChecked -> "앵무새"
                else -> ""
            }
        }
        return type
    }

    fun getAnimalLike(): MutableList<String> {
        like.clear() // 체크박스를 다시 사용할 때 중복 추가 방지
        activityMainBinding.apply {
            if (checkApple.isChecked) {
                like.add(checkApple.text.toString())
            }
            if (checkBanana.isChecked) {
                like.add(checkBanana.text.toString())
            }
            if (checkJujube.isChecked) {
                like.add(checkJujube.text.toString())
            }
        }
        return like
    }

    fun getAnimalGender(): String {
        activityMainBinding.apply {
            gender = when{
                radioMan.isChecked -> "수컷"
                radioWoman.isChecked -> "암컷"
                else -> ""
            }
        }
        return gender
    }


    fun saveData(type: String, like: List<String>, gender: String){
        activityMainBinding.apply {
            val name = textFieldName.editText?.text.toString()
            val age = textFieldAge.editText?.text.toString().toInt()
            val animalClass = AnimalClass(name, age, type, like, gender)
            animalList.add(animalClass)
        }
    }

    fun showAnimalInfo() {
        // 초기화
        activityMainBinding.textResult.text = ""

        // animalList에 저장된 각 AnimalClass 객체의 showAnimalInfo 호출
        animalList.forEach {
            it.showAnimalInfo(activityMainBinding)
        }
    }

    fun resetInfo(){
        activityMainBinding.apply {
            textFieldName.editText?.text?.clear()
            textFieldAge.editText?.text?.clear()
            radioDog.isChecked = false
            radioCat.isChecked = false
            radioParrot.isChecked = false
            checkBoxDesert.isChecked = false
            checkApple.isChecked = false
            checkBanana.isChecked = false
            checkJujube.isChecked = false
            radioMan.isChecked = false
            radioWoman.isChecked = false

            textFieldName.editText?.requestFocus()
        }
    }
}