package com.lion.a033ex_animalmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.lion.a033ex_animalmanager.databinding.ActivityMainBinding
import com.lion.a033ex_animalmanager.databinding.MainRowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var inputDataActivityLauncher: ActivityResultLauncher<Intent>
    lateinit var showDataActivityLauncher: ActivityResultLauncher<Intent>
    val dataList = mutableListOf<AnimalClass>()

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

        settingRecyclerViewMain()
        settingInputDataActivityLauncher()
        settingFloattingActionButtonMain()
        settingShowDataActivityLauncher()
    }

    fun settingRecyclerViewMain(){
        activityMainBinding.apply {
            recyclerViewMain.adapter = RecyclerViewAdapter()
            recyclerViewMain.layoutManager = LinearLayoutManager(this@MainActivity)

            val deco = DividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
            recyclerViewMain.addItemDecoration(deco)

            recyclerViewMain.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (oldScrollY == 0) {
                    floatingActionButton.show()
                } else {
                    if (recyclerViewMain.canScrollVertically(1) == false) {
                        floatingActionButton.hide()
                    } else {
                        if (floatingActionButton.isShown == false) {
                            floatingActionButton.show()
                        }
                    }
                }
            }
        }
    }

    fun settingShowDataActivityLauncher(){
        val contract = ActivityResultContracts.StartActivityForResult()
        showDataActivityLauncher = registerForActivityResult(contract) { result ->
            // 결과 코드가 RESULT_OK인지 확인하여 성공적인 결과인지 판단한다.
            if (result.resultCode == RESULT_OK) {
                // Intent로부터 삭제할 위치를 가져온다. 기본값으로 -1을 설정한다.
                val deletePosition = result.data?.getIntExtra("delete_position", -1)

                // 삭제할 위치가 유효한지 확인한다. (-1이 아닌 경우)
                if (deletePosition != null && deletePosition != -1) {
                    // dataList에서 해당 위치의 동물 정보를 삭제한다.
                    dataList.removeAt(deletePosition)
                    // RecyclerView의 어댑터에 변경 사항을 알린다.
                    activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    fun settingFloattingActionButtonMain(){
        activityMainBinding.apply {
            floatingActionButton.setOnClickListener {
                val inputDataIntent = Intent(this@MainActivity, InputDataActivity::class.java)
                inputDataActivityLauncher.launch(inputDataIntent)
            }
        }
    }

    fun settingInputDataActivityLauncher(){
        val contract = ActivityResultContracts.StartActivityForResult()
        inputDataActivityLauncher = registerForActivityResult(contract) {
            if (it.resultCode == RESULT_OK) {
                if (it.data != null) {
                    val type = it.data?.getStringExtra("type")
                    val name = it.data?.getStringExtra("name")
                    val age = it.data?.getIntExtra("age", 0)
                    val gender = it.data?.getStringExtra("gender")
                    val favoriteFood = it.data?.getStringArrayExtra("favoriteFood")?.toList()

                    val animalClass = AnimalClass(type!!, name!!, age!!, gender!!, favoriteFood!!)
                    dataList.add(animalClass)

                    activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    inner class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewMainViewHolder>(){
        inner class RecyclerViewMainViewHolder(var mainRowBinding: MainRowBinding) : RecyclerView.ViewHolder(mainRowBinding.root), OnClickListener{
            override fun onClick(v: View?) {
                if (dataList.size > 0) {
                    val showDataIntent = Intent(this@MainActivity, ShowDataActivity::class.java)
                    showDataIntent.putExtra("type", dataList[adapterPosition].type)
                    showDataIntent.putExtra("name", dataList[adapterPosition].name)
                    showDataIntent.putExtra("age", dataList[adapterPosition].age)
                    showDataIntent.putExtra("gender", dataList[adapterPosition].gender)
                    showDataIntent.putExtra("favoriteFood", dataList[adapterPosition].favoriteFood.toTypedArray())
                    // 삭제할떄 필요한 순서를 담는다.
                    showDataIntent.putExtra("position", adapterPosition)
                    showDataActivityLauncher.launch(showDataIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewMainViewHolder {
            val mainRowBinding = MainRowBinding.inflate(layoutInflater)
            val recyclerViewMainViewHolder = RecyclerViewMainViewHolder(mainRowBinding)

            mainRowBinding.apply {
                root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                root.setOnClickListener(recyclerViewMainViewHolder)
            }

            return recyclerViewMainViewHolder
        }

        override fun onBindViewHolder(holder: RecyclerViewMainViewHolder, position: Int) {
            holder.mainRowBinding.apply {
                if (dataList.size == 0) {
                    textViewMainRow.text = "등록된 동물정보가 없습니다."
                } else {
                    textViewMainRow.text = dataList[position].name
                }
            }
        }

        override fun getItemCount(): Int {
            if (dataList.size == 0) {
                return 1
            } else {
                return dataList.size
            }
        }
    }
}