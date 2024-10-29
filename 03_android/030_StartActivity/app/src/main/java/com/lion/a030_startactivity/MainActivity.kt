package com.lion.a030_startactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a030_startactivity.databinding.ActivityMainBinding

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

        // startActivity : 다른 액티비티를 실행 시키는 것만 하고자 할 때 사용
        // startActivityForResult : 다른 Activity를 실행시켰다가 다시 돌아올 때 무언가 작업을 할 경우

        activityMainBinding.apply {
            button.setOnClickListener {
                // Intent 생성
                // Android OS 에게 무언가 실행을 요청할 때 실행 정보를 담는 객체
                // 두 번째 매개변수에 실행시키고 싶은 Activity 클래스를 지정해준다.
                val firstIntent = Intent(this@MainActivity, FirstActivity::class.java)
                // 새롭게 실행되는 Activity에게 데이터를 전달하고 싶다면 Intent에 담아준다.
                // 어떤 메서드를 통해 실행하더라도 데이터는 잘 전달된다.
                firstIntent.putExtra("data1", 100)
                firstIntent.putExtra("data2", 11.11)
                firstIntent.putExtra("data3", true)
                firstIntent.putExtra("data4", "문자열1")
                // Activity 가동
                // startActivity(firstIntent)
                startActivityForResult(firstIntent, 100)
            }

            button2.setOnClickListener {
                val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
                // startActivity(secondIntent)
                startActivityForResult(secondIntent, 200)
            }

            button3.setOnClickListener {
                val thirdIntent = Intent(this@MainActivity, ThirdActivity::class.java)
                // startActivity(thirdIntent)
                startActivityForResult(thirdIntent, 300)
            }
        }
    }

    // startActivityForResult로 다른 Activity를 실행한 다음 돌아올 때 호출되는 메서드
    // requestCodde : startActivityForResult 메서드를 사용할 때 두 번째 매개변수에 넣어준 정수값이 들어온다.
    // 이를 통해 어떤 Activity를 갔다 왔는지 구분한다.
    // data : 종료된 Acitivity 에서 setResult에 설정한 Intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityMainBinding.apply {
            // requestCode로 분기한다
            // 어떤 Activity를 실행했다가 돌아왔는지를 구분
            when(requestCode){
                100 -> {
                    textView4.text = "FirstActivity에서 돌아왔습니다\n"
                    // resultCode로 분기한다.
                    // 실행시켰던 Activity에서 수행된 작업의 결과가 어떻게 되었는지를 구분
                    when(resultCode){
                        RESULT_OK -> {
                            textView4.append("작업 수행 성공\n")
                            // 데이터를 가져온다.
                            if(data != null){
                                textView4.append("value1 : ${data.getIntExtra("value1", 0)}\n")
                                textView4.append("value2 : ${data.getDoubleExtra("value2", 0.0)}\n")
                                textView4.append("value3 : ${data.getBooleanExtra("value3", false)}\n")
                                textView4.append("value4 : ${data.getStringExtra("value4")}")
                            }
                        }
                        RESULT_CANCELED -> textView4.append("작업 취소\n")
                        RESULT_FIRST_USER -> textView4.append("그 외의 결과 1\n")
                        RESULT_FIRST_USER + 1 -> textView4.append("그 외의 결과 2\n")
                    }
                }
                200 -> textView4.text = "SecondActivity에서 돌아왔습니다"
                300 -> textView4.text = "ThirdActivity에서 돌아왔습니다"
            }
        }
    }
}