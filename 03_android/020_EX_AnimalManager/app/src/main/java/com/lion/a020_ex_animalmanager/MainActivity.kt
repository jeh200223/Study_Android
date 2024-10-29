package com.lion.a020_ex_animalmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a020_ex_animalmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    var animalList = mutableListOf<AnimalClass>()

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
            buttonSave.setOnClickListener {
                saveData()
                resetInfo()
            }

            buttonShow.setOnClickListener {
                showData()
            }
        }


    }

    fun getAnimalType(): String {
        activityMainBinding.apply {
            return when (typeGroup.checkedChipId) {
                R.id.checkDog -> "강아지"
                R.id.checkCat -> "고양이"
                R.id.checkParrot -> "앵무새"
                else -> ""
            }
        }
    }



    fun getAnimalAge(): Int {
        activityMainBinding.apply {
            return SilderAge.value.toInt()
        }
    }

    fun getAnimalGender(): String {
        var gender = ""
        activityMainBinding.apply {
            gender = when {
                radioMan.isChecked -> "수컷"
                radioWoman.isChecked -> "암컷"
                else -> ""
            }
        }
        return gender
    }

    fun getAnimalWeight(): List<Int> {
        activityMainBinding.apply {
            val start = RangeSliderWeight.values[0].toInt()
            val end = RangeSliderWeight.values[1].toInt()
            val weightList = listOf(start, end)

            return weightList
        }
    }

    fun getAnimalFavorite(): List<String> {
        val favoriteList = mutableListOf<String>()
        activityMainBinding.apply {
            if (checkApple.isChecked) favoriteList.add("사과")
            if (checkBanana.isChecked) favoriteList.add("바나나")
            if (checkJejube.isChecked) favoriteList.add("대추")
        }
        return favoriteList
    }


    fun saveData() {
        activityMainBinding.apply {
            val type = getAnimalType()
            val name = textFieldName.editText?.text.toString()
            val age = getAnimalAge()
            val gender = getAnimalGender()
            val weight = getAnimalWeight()
            val favorite = getAnimalFavorite()

            animalList.add(AnimalClass(type, name, age, gender, weight, favorite))
        }

    }

    fun showData() {
        animalList.forEach {
            it.showAnimalInfo(activityMainBinding)
        }
    }

    fun resetInfo(){
        activityMainBinding.apply {
            typeGroup.check(checkDog.id)
            textFieldName.editText?.text?.clear()
            SilderAge.value = 0f
            genderGroup.check(radioMan.id)
            RangeSliderWeight.values = listOf(0f,200f)
            favoriteGroup.clearCheck()
        }
    }
}