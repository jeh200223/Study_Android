package com.lion.a001_helloworld

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 애플리케이션이 사용하는 화면의 영역을 상단의
        // Status 바 까지 확장한다.
        // 하단의 버튼 영역의 색상도 비슷하게 설정해준다.
        enableEdgeToEdge()

        // Activity가 관리할 화면 객체를 지정한다.
        // R.layout.activity_main :
        // R - res 폴더
        // layout - res 폴더 안에 있는 layout 폴더
        // activity_main - layout 폴더 안에 있는 activity_main.xml 을 의미한다.
        // activity_main.xml 에 기록한 내용을 토대로 화면 객체를 만들고 Acitivity에 지정해준다.
        setContentView(R.layout.activity_main)

        // 상단 status바의 높이를 구한 다음 그 만큼의 내부 여백을 설정한다.
        // enableEdgeToEdge 때문에 개발자가 배치한 화면 요소들이 Status 바 영역을 침범하는 것을 막아준다.
        // 만약 내부 여백을 더 주고 싶다면 그 만큼 더해주면 된다.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}