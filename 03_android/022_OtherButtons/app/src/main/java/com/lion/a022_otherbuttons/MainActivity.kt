package com.lion.a022_otherbuttons

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.OnChangedCallback
import com.lion.a022_otherbuttons.databinding.ActivityMainBinding

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
            floatingActionButton.setOnClickListener {
                textView.text = "Floating Action Button을 눌렀습니다"
            }

            button.setOnClickListener {
                // 만약 펼쳐져 있다면
                if(fab3.isExtended){
                    // 접는다
                    fab3.shrink()
                } else {
                    // 펼친다.
                    fab3.extend()
                }
            }

            button3.setOnClickListener {

                if(floatingActionButton.isShown){
                    floatingActionButton.hide()
                } else {
                    floatingActionButton.show()
                }

                if(fab3.isShown){
                    fab3.hide()
                } else {
                    fab3.show()
                }
            }
            // 체크박스 형태로 사용할 Button Toggle Group은 최초에 선택되어 있는 버튼은
            // 코드로 설정해줘야 한다.
            toggle1.check(R.id.button8)
            toggle1.check(R.id.button10)

            button14.setOnClickListener {
                // 체크박스 처럼 사용하는 그룹인 경우
                textView.text = ""
                toggle1.checkedButtonIds.forEach {
                    when(it){
                        R.id.button8 -> textView.append("첫 번째 체크,")
                        R.id.button9 -> textView.append("두 번째 체크,")
                        R.id.button10 -> textView.append("세 번째 체크,")
                    }
                }

                // 라디오 버튼 처럼 사용하는 그룹인 경우
                when(toggle2.checkedButtonId){
                    R.id.button11 -> textView2.text = "첫 번째 버튼 선택"
                    R.id.button12 -> textView2.text = "두 번째 버튼 선택"
                    R.id.button13 -> textView2.text = "세 번째 버튼 선택"
                }
            }

            button15.setOnClickListener {
                // 체크박스
                toggle1.check(R.id.button8)
                toggle1.check(R.id.button10)
                // 라디오
                toggle2.check(R.id.button13)
            }
        }
    }
}