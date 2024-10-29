package com.lion.a025ex_listview

import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lion.a025ex_listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    val imageArray = arrayOf(
        R.drawable.imgflag1,
        R.drawable.imgflag2,
        R.drawable.imgflag3,
        R.drawable.imgflag4,
        R.drawable.imgflag5,
        R.drawable.imgflag6,
        R.drawable.imgflag7,
        R.drawable.imgflag8)

    val strArray = arrayOf(
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국")

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
            val dataList = mutableListOf<MutableMap<String, *>>()

            imageArray.forEachIndexed { index, i ->
                val map = mutableMapOf(
                    "image" to imageArray[index],
                    "text" to strArray[index]
                )
                dataList.add(map)
            }

            val mapArray = arrayOf("image", "text")
            val viewIdArray = intArrayOf(R.id.imageView2,R.id.textView2)

            listView.adapter = SimpleAdapter(
                this@MainActivity, dataList, R.layout.row, mapArray, viewIdArray
            )

            listView.setOnItemClickListener { parent, view, position, id ->
                textView.text = strArray[position]
                imageView.setImageResource(imageArray[position])
            }
        }
    }


}