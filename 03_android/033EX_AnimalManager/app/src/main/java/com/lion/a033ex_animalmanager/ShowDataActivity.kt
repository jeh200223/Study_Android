package com.lion.a033ex_animalmanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a033ex_animalmanager.databinding.ActivityMainBinding
import com.lion.a033ex_animalmanager.databinding.ActivityShowDataBinding

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
        settingButtonHome()
        // 텍스트뷰를 구성하는 메서드
        settingTextView()
        // 삭제 버튼 구성 메서드
        settingButtonDelete()
    }

    // 메인 화면 버튼을 구성하는 메서드
    fun settingButtonHome(){
        activityShowDataBinding.apply {
            buttonHome.setOnClickListener{
                // 현재 Activity를 종료한다.
                finish()
            }
        }
    }

    // TextView에 데이터를 셋팅하는 메서드
    fun settingTextView(){
        activityShowDataBinding.apply {
            textViewType.text = "동물 종류 : ${intent.getStringExtra("type")}"
            textViewName.text = "이름 : ${intent.getStringExtra("name")}"
            textViewAge.text = "나이 : ${intent.getIntExtra("age", 0)}"
            textViewGender.text = "성별 : ${intent.getStringExtra("gender")}"
            val favoriteFoodArray = intent.getStringArrayExtra("favoriteFood")
            val favoriteFoodText = when {
                favoriteFoodArray == null || favoriteFoodArray.isEmpty() -> "없음"
                else -> favoriteFoodArray.joinToString(", ")
            }
            textViewFavoriteFood.text = "좋아하는 간식 : $favoriteFoodText"
        }
    }

    // 메인 화면 버튼을 구성하는 메서드
    fun settingButtonDelete(){
        activityShowDataBinding.apply {
            buttonDelete.setOnClickListener {
                // Intent로부터 삭제할 동물의 인덱스(position)를 가져온다.
                // 기본값으로 -1을 설정하여 인덱스가 없을 경우를 처리한다.
                val position = intent.getIntExtra("position", -1)

                // position이 -1이 아닌 경우, 즉 유효한 인덱스가 전달된 경우에만 실행
                if (position != -1) {
                    // 데이터를 담을 인텐트를 생성한다.
                    val resultIntent = Intent()

                    // 'delete_position'이라는 키로 현재 삭제할 동물의 인덱스(position)를 resultIntent에 추가
                    resultIntent.putExtra("delete_position", position)

                    // 결과로서 RESULT_OK와 함께 resultIntent를 설정하여 호출한 Activity(MainActivity)로 돌아감
                    setResult(RESULT_OK, resultIntent)
                }
                finish()
            }
        }
    }
}