package com.lion.a026ex_recyclerview

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lion.a026ex_recyclerview.databinding.ActivityMainBinding
import com.lion.a026ex_recyclerview.databinding.RowBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    // 사용할 데이터
    val imageArray = arrayOf(
        R.drawable.imgflag1,
        R.drawable.imgflag2,
        R.drawable.imgflag3,
        R.drawable.imgflag4,
        R.drawable.imgflag5,
        R.drawable.imgflag6,
        R.drawable.imgflag7,
        R.drawable.imgflag8,
    )

    val strArray = arrayOf(
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    )
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
            recyclerView.adapter = RecyclerViewAdapter()

            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    inner class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
        inner class ViewHolderClass(val rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root), OnClickListener {
            override fun onClick(v: View?) {
                activityMainBinding.textView.text = strArray[adapterPosition]
                activityMainBinding.imageView.setImageResource(imageArray[adapterPosition])
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolder = ViewHolderClass(rowBinding)

            rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.setOnClickListener(viewHolder)
            return viewHolder
        }

        override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolderClass, position: Int) {
            holder.rowBinding.textView2.text = strArray[position]
            holder.rowBinding.imageView2.setImageResource(imageArray[position])
        }

        override fun getItemCount(): Int {
            return imageArray.size
        }
    }
}