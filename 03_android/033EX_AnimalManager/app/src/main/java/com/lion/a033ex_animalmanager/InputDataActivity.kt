package com.lion.a033ex_animalmanager

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.checkbox.MaterialCheckBox
import com.lion.a033ex_animalmanager.databinding.ActivityInputDataBinding

class InputDataActivity : AppCompatActivity() {
    lateinit var activityInputDataBinding: ActivityInputDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        activityInputDataBinding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(activityInputDataBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        settingMaterialCheckBox()
        // 동물 정보 입력
        settingAnimalInfo()
    }

    // 동물 종류를 설정하는 메서드
    fun settingType(): String {
        var type = ""
        activityInputDataBinding.apply {
            type = when (typeGroup.checkedChipId) {
                R.id.dog -> "강아지"
                R.id.cat -> "고양이"
                R.id.parrot -> "앵무새"
                else -> {
                    "유효하지않습니다."
                }
            }
        }
        return type
    }

    // 성별을 설정하는 메서드
    fun settingGender(): String {
        var gender = ""
        activityInputDataBinding.apply {
            gender = when (genderGroup.checkedChipId) {
                R.id.male -> "수컷"
                R.id.female -> "암컷"
                else -> {
                    "유효하지않습니다."
                }
            }
        }
        return gender
    }


    // 체크박스 기능을 설정하는 메서드
    fun settingMaterialCheckBox() {
        activityInputDataBinding.apply {
            checkBoxAll.addOnCheckedStateChangedListener { checkBox, state ->
                when (state) {
                    MaterialCheckBox.STATE_CHECKED -> {
                        checkApple.isChecked = true
                        checkBanana.isChecked = true
                        checkJejube.isChecked = true
                    }

                    MaterialCheckBox.STATE_UNCHECKED -> {
                        checkApple.isChecked = false
                        checkBanana.isChecked = false
                        checkJejube.isChecked = false
                    }
                }
            }

            val checkBoxListener = OnCheckedChangeListener { button, isChecked ->
                var checkCount = 0
                if (checkApple.isChecked) {
                    checkCount++
                }
                if (checkBanana.isChecked) {
                    checkCount++
                }
                if (checkJejube.isChecked) {
                    checkCount++
                }

                checkBoxAll.checkedState = if (checkCount == 0) {
                    MaterialCheckBox.STATE_UNCHECKED
                } else if (checkCount == 3) {
                    MaterialCheckBox.STATE_CHECKED
                } else {
                    MaterialCheckBox.STATE_INDETERMINATE
                }
            }
            checkApple.setOnCheckedChangeListener(checkBoxListener)
            checkBanana.setOnCheckedChangeListener(checkBoxListener)
            checkJejube.setOnCheckedChangeListener(checkBoxListener)
        }
    }

    // 좋아하는 간식을 설정하는 메서드
    fun settingFavoriteFood(): MutableList<String> {
        val favoriteFood = mutableListOf<String>()
        activityInputDataBinding.apply {
            if (checkApple.isChecked) {
                favoriteFood.add(checkApple.text.toString())
            }
            if (checkBanana.isChecked) {
                favoriteFood.add(checkBanana.text.toString())
            }
            if (checkJejube.isChecked) {
                favoriteFood.add(checkJejube.text.toString())
            }
        }
        return favoriteFood
    }


    // 동물 정보를 입력하는 메서드
    fun settingAnimalInfo() {
        activityInputDataBinding.apply {
            // 엔터키를 누르면 현재 Activity를 종료한다.
            textFieldName.editText?.setOnEditorActionListener { v, actionId, event ->
                val animalType = settingType()
                val animalGender = settingGender()
                val animalFavoriteFood = settingFavoriteFood()

                // 데이터를 담을 인텐트를 생성한다.
                val dataIntent = Intent()
                dataIntent.putExtra("type", animalType)
                dataIntent.putExtra("name", textFieldName.editText?.text.toString())
                dataIntent.putExtra("age", SliderAge.value.toInt())
                dataIntent.putExtra("gender", animalGender)
                dataIntent.putExtra("favoriteFood", animalFavoriteFood.toTypedArray())

                setResult(RESULT_OK, dataIntent)
                finish()
                false
            }
        }
    }
}