package com.lion.a033_toastsnackbar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a033_toastsnackbar.databinding.ActivityMainBinding

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
                // Toast 메시지를 요청한다.
                // Toast 메시지는 안드로이드 OS 에게 요청하는 메시지이다.
                // 어플 실행 여부에 관계없이 요청 받은 순서대로 메시지를 보여주며 일정 시간이 지나면 자동으로 사라진다.
                // 사용자가 보지 못할 수 있는 메시지이다.
                // 두 번째 : 보여주고 싶은 메시지
                // 세 번째 : 메시지를 보여줄 시간 (LENGTH_SHORT, LENGTH_LONG)
                val t1 = Toast.makeText(this@MainActivity, "기본 토스트", Toast.LENGTH_SHORT)
                // 메시지 출력을 OS에게 요청한다.
                t1.show()
            }
        }
    }
}