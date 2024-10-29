package com.lion.a011_textfield

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a011_textfield.databinding.ActivityMainBinding

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
            button.setOnClickListener {
                // TextField에 에러 메시지를 설정한다.
                textField2.error = "오류입니다"
            }

            button2.setOnClickListener {
                // TextField의 에러 메시지를 제거한다.
                textField2.error = null
            }

            button3.setOnClickListener {
                // editText 프로퍼티는 입력 요소에 접근할 수 있다.
                textView.text = textField1.editText?.text
                textView2.text = textField2.editText?.text
            }
        }
    }
}