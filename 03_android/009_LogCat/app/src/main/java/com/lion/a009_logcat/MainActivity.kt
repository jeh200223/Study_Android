package com.lion.a009_logcat

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 로그켓.
        // 안드로이드 스튜디오에 있는 콘솔 출력창
        // 연결되어 있는 에뮬레이터나 단말기별로 출력해볼 수 있다.
        // 현재 애플리케이션의 진행상황 등을 자유롭게 출력하고자 할 때 사용
        Log.v("test100", "Verbose")
        // 개발시 변수의 값이나 디버깅을 위해 사용하는 출력문을 출력하고자 할 때 사용
        Log.d("test100", "debug")
        // 경고 메시지용
        Log.w("test100", "warning")
        // 정보를 표시하고 싶을 때
        Log.i("test100", "information")
        // 에러메시지 표시할 때
        Log.e("test100", "error")
        // 매우 심각한 오류가 발생했을 때
        Log.wtf("test100", "error!!!!!!!!!!!!!!!!!!!!!")

        Log.d("test200", "test100")

        // 로그켓의 필터 사용법
        // 문자열만 작성 : 해당 문자열을 포함하는 것들이 모두 출력된다.

        // 접두사

        // 접두사:문자열 - 문자열을 포함하는 것
        // 접두사=:문자열 - 문자열과 같은 것
        // 접두사~:문자열 - 문자열이 아닌 것

        // age
        // 현 시각을 기준으로 얼마 전까지의 메시지만 볼것인가..
        // 단위 : s - 초, m - 분, h - 시, d - 일

        // is
        // is 다음에 추가로 넣어주는 옵션들에 해당하는 것만 골라 볼 수 있다.
        // 추가로 넣어주는 옵션들은 ctrl + spacebar 누르셔서 자동완성 목록에서 골라주세요

        // level
        // is와 비슷하지만 여러가지가 섞여져서 나온다.
        // 추가로 넣어주는 옵션들은 ctrl + spacebar 누르셔서 자동완성 목록에서 골라주세요

        // package
        // 현재 실행되고 있는 애플리케이션의 package 명을 넣어주면
        // 해당 어플이 출력하는 것만 골라 볼 수 있다.

        // package:mine
        // 현재 화면에 보여지고 있는 애플리케이션의 메시지를 보여준다.

        // tag:태그이름
        // 태그이름에 해당하는 것만 모아서 보여준다.

        // 만약 현재 보이는 애플리케이션에서 태그가 test100인 메시지 중
        // 에러 메시지만 보고 싶다면
        // package:mine tag:test100 is:error

    }
}











