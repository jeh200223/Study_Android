package com.lion.a032ex_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a032ex_activity.databinding.ActivityInputDataBinding

class InputDataActivity : AppCompatActivity() {
    lateinit var activityInputDataBinding: ActivityInputDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        activityInputDataBinding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(activityInputDataBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + 10, systemBars.top, systemBars.right + 10, systemBars.bottom)
            insets
        }

        // 마지막 입력요소 구성
        settingTextFieldAge()
    }

    // 제일 마지막에 있는 입력 요소 구성
    fun settingTextFieldAge(){
        activityInputDataBinding.apply {
            // 엔터키를 누르면 현재 Activity를 종료한다.
            textFieldAge.editText?.setOnEditorActionListener { v, actionId, event ->
                // 데이터를 담을 인텐트를 생성한다.
                val dataIntent = Intent()
                dataIntent.putExtra("name", textFieldName.editText?.text.toString())
                dataIntent.putExtra("nickName", textFieldNickName.editText?.text.toString())
                dataIntent.putExtra("age", textFieldAge.editText?.text.toString().toInt())

                setResult(RESULT_OK, dataIntent)
                finish()
                false
            }
        }
    }
}