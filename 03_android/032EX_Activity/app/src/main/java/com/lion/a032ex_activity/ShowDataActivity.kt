package com.lion.a032ex_activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a032ex_activity.databinding.ActivityShowDataBinding

class ShowDataActivity : AppCompatActivity() {
    lateinit var activityShowDataBinding: ActivityShowDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        activityShowDataBinding = ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(activityShowDataBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 메인 화면 버튼 구성 메서드
        settingButtonShowDataFinish()
        // 텍스트뷰를 구성하는 메서드
        settingTextView()
    }

    // 메인 화면 버튼을 구성하는 메서드
    fun settingButtonShowDataFinish(){
        activityShowDataBinding.apply {
            buttonShowDataFinish.setOnClickListener {
                // 현재 Activity를 종료한다.
                finish()
            }
        }
    }

    // TextView에 데이터를 셋팅하는 메서드
    fun settingTextView(){
        activityShowDataBinding.apply {
            textViewShowDataName.text = intent.getStringExtra("name")
            textViewShowDataNickName.text = intent.getStringExtra("nickName")
            textViewShowDataAge.text = intent.getIntExtra("age", 0).toString()
        }
    }
}