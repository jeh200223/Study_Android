package com.lion.a008_basicview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.lion.a008_basicview.databinding.ActivityMainBinding

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
            // 버튼에 리스너를 설정한다.
            // 코틀린 언어를 이용해 안드로이드 애플리케이션을 개발할 때
            // 리스너 대신에 고차함수를 제공하는 것들이 있다.
            // 주로 구현할 메서드가 하나만 있거나 주로 사용하는 메서드가 하나만 있을 경우에 제공된다.
            // 만약 주로 사용하는 메서드가 두 개 이상인 리스너인 경우에는 클래스를 직접 만들어 사용해야 한다.
            button.setOnClickListener {
                // 각 EditText에서 입력한 문자열을 가지고 온다.
                val str1 = editTextText.text.toString()
                val str2 = editTextText2.text.toString()
                // TextView에 출력해준다.
                textView.text = str1
                textView2.text = str2
            }

            // 키보드 우측 하단에 있는 리턴키를 눌렀을 때
            // true를 반환하면 키보드가 내려가지 않는다
            editTextText2.setOnEditorActionListener { v, actionId, event ->
                // 각 EditText에서 입력한 문자열을 가지고 온다.
                val str1 = editTextText.text.toString()
                val str2 = editTextText2.text.toString()
                // TextView에 출력해준다.
                textView.text = str1
                textView2.text = str2
                textView3.text = "엔터키를 눌렀습니다"
                false
            }

            // EditText의 문자를 입력할 때 마다 동작하는 리스너
            // it : 현재 EditText에 입력되어 있는 문자열
            editTextText.addTextChangedListener { 
                textView.text = it
                textView3.text = "문자열이 변경되었습니다"
            }
        }
    }

}