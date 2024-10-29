# ViewBinding 셋팅

### build.gradle.kts 에 viewBinding 코드를 넣어준다.

[build.gradle.kts(Module:app)]
```kt
    viewBinding {
    enable = true
}
```

### MainActivity에 View Bindng 코드를 넣어준다.

[MainActivity.kt]
```kt
    lateinit var activityMainBinding: ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(activityMainBinding.root)
```

---

# MainActivity 화면 작업

### RecyclerView를 배치한다.

[activity_main.xml]

```text
    RecyclerView
        - layout_width : 0dp
        - layout_height : 0dp
        - layout_constraintBottom_toBottomOf : parent
        - layout_constraintEnd_toEndOf : parent
        - layout_constraintStart_toStartOf : parent
        - layout_constraintTop_toTopOf : parent
        - id : recyclerViewMain
```

### 항목 하나를 구성할때 사용할 layout 파일을 만들어준다.

[res/layout/main_row.xml]

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <TextView
        android:id="@+id/textViewMainRow"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="10dp"
        android:text="TextView"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />
</LinearLayout>
```

### RecyclerView 구성을 위한 임시 데이터를 정의한다.

[MainActivity.kt]

```kt
    // RecyclerView 구성을 위한 임시 데이터
val dataList = Array<String>(50){
    "문자열 : $it"
}
```

### Adapter를 작성한다.

[MainActivity.kt]
```kt
    // RecyclerView의 어뎁터
inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.RecyclerViewMainViewHolder>(){
    // ViewHolder를 상속받은 클래스
    // ViewHolder를 주 생성자에서 Property로 정의해야 한다.
    inner class RecyclerViewMainViewHolder(var mainRowBinding: MainRowBinding) : RecyclerView.ViewHolder(mainRowBinding.root){

    }

    // ViewHolder를 생성하는 메서드
    // 보여져야 하는 항목에 대한 ViewHolder 가 없을 경우 호출된다.
    // 재 사용 가능한 ViewHolder가 있으면 호출하지 않는다..
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewMainViewHolder {
        val mainRowBinding = MainRowBinding.inflate(layoutInflater)
        val recyclerViewMainViewHolder = RecyclerViewMainViewHolder(mainRowBinding)

        return recyclerViewMainViewHolder
    }

    // 항목의 전체 개수를 반환하는 메서드
    override fun getItemCount(): Int {
        return dataList.size
    }

    // 항목에 배치되어 있는 View에 값을 설정하여 준다.
    override fun onBindViewHolder(holder: RecyclerViewMainViewHolder, position: Int) {
        holder.mainRowBinding.apply {
            Log.d("test100", "${dataList[position]}")
            textViewMainRow.text = dataList[position]
        }
    }
}
```

### RecyclerView를 구성하는 메서드를 구현해준다.

[MainActivity.kt]

```kt
    fun settingRecyclerViewMain(){
    activityMainBinding.apply {
        // 어뎁터를 설정한다.
        recyclerViewMain.adapter = RecyclerViewMainAdapter()
        // 보여주는 방식을 설정한다.
        recyclerViewMain.layoutManager = LinearLayoutManager(this@MainActivity)
        // 구분선 데코레이션
        val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
        recyclerViewMain.addItemDecoration(deco)
    }
}
```

### RecyclerView를 구성하는 메서드를 호출해준다.

[MainAcitivity.kt - onCreate()]
```kt
        settingRecyclerViewMain()
```

### FloatingActionButton을 배치해준다.

[res/layout/activity_main.xml]

```text
    FloatingActionButton
        - id : floatingActionButtonMain
        - layout_marginEnd : 50dp
        - layout_marginBottom : 50dp
        - layout_constraintBottom_toBottomOf : parent
        - layout_constraintEnd_toEndOf : parent
        - srcCompat : ic_menu_edit
```

### FloatingActionButton을 사라지게 하도록 구현해준다.

[MainActivity.kt - settingRecyclerViewMain()]

```kt
            // 리사이클러뷰가 스크롤 상태가 변경되면 동작하는 리스너
recyclerViewMain.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
    // canScrollVertically : 위나 아래로 스크롤이 가능한지를 확인하는 메서드
    // 만약 -1 을 넣어줬는데 false가 반환되면 : 제일 위에 있는 상태
    // 만약 1을 넣어줬는데 false가 반환되면 : 제일 마지막에 있는 상태
    // 만약 제일 아래에 있는 상태라면..
    if(recyclerViewMain.canScrollVertically(1) == false){
        // FloatingActionButton을 사라지게 된다.
        floatingActionButtonMain.hide()
    } else {
        // 만약 제일 아래에 있는 상태가 아니고 FloatingActionButton이 보이지 않는 상태라면
        if(floatingActionButtonMain.isShown == false){
            // FloatingActionButton을 나타나게 한다.
            floatingActionButtonMain.show()
        }
    }
}
```

---

# 데이터 입력 화면 구성

### InputDataActivity를 생성해준다.

### activity_input_data 는 다음과 같이 구성해준다.

```text
LinearLayout
    - orientation : vertical

    TextInputLayout
        - id : textFieldName
        - startIconDrawable : 아무거나
        - endIconMode : clear_text
        - hint : 이름
        - layout_height : wrap_content

        TextInputEditText
            - hint : 삭제
            - singleLine : true

    Space
        - layout_height : 20dp

    TextInputLayout
        - id : textFieldNickName
        - startIconDrawable : 아무거나
        - endIconMode : clear_text
        - hint : 닉네임
        - layout_height : wrap_content

        TextInputEditText
            - hint : 삭제
            - singleLine : true

    Space
        - layout_height : 20dp

    TextInputLayout
        - id : textFieldAge
        - startIconDrawable : 아무거나
        - endIconMode : clear_text
        - hint : 나이
        - layout_height : wrap_content

        TextInputEditText
            - hint : 삭제
            - imeOptions : actionDone
            - inputType : number|number
            - singleLine : true
```

### InputDataActivity를 위한 런처를 선언해준다.

[MainActivity.kt]

```kt
    // Activity 실행을 위한 런처
lateinit var inputDataActivityLauncher:ActivityResultLauncher<Intent>
```

### inputDataActivityLauncher를 구성하는 메서드를 구현해준다.

[MainActivity.kt]

```kt
    // InputDataActivity 실행을 위한 런처 구성한다.
fun settingInputDataActivityLauncher(){
    // 런처를 구성해준다.
    val contract = ActivityResultContracts.StartActivityForResult()
    inputDataActivityLauncher = registerForActivityResult(contract){

    }
}
```

### 메서드를 호출해준다.

[MainActivity - onCreate()]

```kt
        // InputDataActivity 런처 구성
settingInputDataActivityLauncher()
```

### FloatingActionButton에 대한 설정을 하는 메서드를 구현한다.

[MainActivity.kt]
```kt
    // FloatingActionButton 설정
fun settingFloatingActionButtonMain(){
    activityMainBinding.apply {
        // Click Listener
        floatingActionButtonMain.setOnClickListener {
            // InputDataActivity를 실행한다.
            val inputDataIntent = Intent(this@MainActivity, InputDataActivity::class.java)
            inputDataActivityLauncher.launch(inputDataIntent)
        }
    }
}
```


### 메서드를 호출한다.

[MainActivity.kt - onCreate()]

```kt
        // FloatingActionButton 구성
settingFloatingActionButtonMain()
```

---

### viewBinding 설정을 해준다.

[InputDataActivity.kt]

```kt

lateinit var activityInputDataBinding: ActivityInputDataBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    activityInputDataBinding = ActivityInputDataBinding.inflate(layoutInflater)
    setContentView(activityInputDataBinding.root)
```


### 마지막 입력요소에 대한 구성을 구현한다.

[InputDataActivity.kt]
```kt
    // 제일 마지막에 있는 입력 요소 구성
fun settingTextFieldAge(){
    activityInputDataBinding.apply {
        // 엔터키를 누르면 현재 Activity를 종료한다.
        textFieldAge.editText?.setOnEditorActionListener { v, actionId, event ->
            setResult(RESULT_OK)
            finish()
            false
        }
    }
}
```

### 메서드를 호출한다.

[InputDataActivity.kt - onCreate()]
```kt

// 마지막 입력요소 구성
settingTextFieldAge()
```

---

# 데이터 보는 화면 구성

### ShowDataActivity를 만들어준다.

### activity_show_data.xml 을 구성해준다.

```text
LinearLayout
    - orientation : vertical

    TextView
        - id : textViewShowDataName
        - textAppearance : Large

    Space
        - layout_height : 20dp

    TextView
        - id : textViewShowDataNickName
        - textAppearance : Large

    Space
        - layout_height : 20dp

    TextView
        - id : textViewShowDataAge
        - textAppearance : Large

    Space
        - layout_height : 20dp

    Button
        - id : buttonShowDataFinish
        - text : 메인 화면
```

### ShowDataActivity 실행을 위한 런처를 선언해준다.

[MainActivity.kt]

```kt
    lateinit var showDataActivityLauncher:ActivityResultLauncher<Intent>
```

### showDataActivityLauncher를 구성하는 메서드를 만들어준다.

[MainActivity.kt]

```kt

// ShowDataActivity 실행을 위한 런처를 구성한다.
fun settingShowDataActivityLauncher(){
    val contract = ActivityResultContracts.StartActivityForResult()
    showDataActivityLauncher = registerForActivityResult(contract){

    }
}
```

### RecyclerView의 어뎁터 클래스의 ViewHolder 클래스에 OnClickListenr를 구현해준다.

[MainActivity.kt - RecyclerViewMainAdapter - RecyclerViewMainViewHolder]
```kt
        inner class RecyclerViewMainViewHolder(var mainRowBinding: MainRowBinding) : RecyclerView.ViewHolder(mainRowBinding.root), OnClickListener{
    // 항목을 눌렀을 때
    override fun onClick(v: View?) {
        // ShowDataActivity를 실행한다.
        val showDataIntent = Intent(this@MainActivity, ShowDataActivity::class.java)
        showDataActivityLauncher.launch(showDataIntent)
    }
}
```

### 항목에 OnClickListener를 설정해준다.

[MainActivity.kt - RecyclerViewMainAdapter - onCreateViewHolder()]

```kt
            mainRowBinding.apply {
    // 터치 영역을 전체로 하기 위해 가로 세로 길이를 설정한다.
    root.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    // onClickListener 설정
    root.setOnClickListener(recyclerViewMainViewHolder)
}
```

### ShowDataActivityLauncher를 구성하는 메서드를 호출해준다.

[MainActivity.kt - onCreate()]

```kt
        // ShowDataActivity 런처 구성
settingShowDataActivityLauncher()
```

### ShowDataActivity에 ViewBinding 코드를 넣어준다.

[ShowDataActivity.kt]

```kt
    lateinit var activityShowDataBinding: ActivityShowDataBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    activityShowDataBinding = ActivityShowDataBinding.inflate(layoutInflater)
    setContentView(activityShowDataBinding.root)
```

### 메인 화면 버튼을 구성해준다.

[ShowDataActivity.kt]
```kt
    // 메인 화면 버튼을 구성하는 메서드
fun settingButtonShowDataFinish(){
    activityShowDataBinding.apply {
        buttonShowDataFinish.setOnClickListener {
            // 현재 Activity를 종료한다.
            finish()
        }
    }
}
```

### 메서드를 호출해준다.

[ShowDataActivity.kt - onCreate()]
```kt
        // 메인 화면 버튼 구성 메서드
        settingButtonShowDataFinish()
```

---

# 입력 기능을 구현한다.

### 입력한 정보를 담을 클래스를 정의한다.

[DataModel.kt]
```kt
package com.lion.a032ex_activity

data class DataModel(var name:String, var nickName:String, var age:Int)
```


### RecyclerView를 구성하기 위한 배열을 리스트로 변경한다.

[MainActivity.kt]

```kt
    // RecyclerView 구성을 위한 임시 데이터
//    val dataList = Array<String>(50){
//        "문자열 : $it"
//    }

    // RecyclerView를 구성하기 위한 리스트
    val dataList = mutableListOf<DataModel>()
```
    
### RecyclerView의 어뎁터의 코드를 수정한다.

[MainActivity.kt - RecyclerViewMainAdapter - onBindViewHolder()]
```kt
                // textViewMainRow.text = dataList[position]
                textViewMainRow.text = dataList[position].name
```

### 데이터가 없을 때를 처리해준다.

[MainActivity.kt]
```kt
    // RecyclerView의 어뎁터
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.RecyclerViewMainViewHolder>(){
        // ViewHolder를 상속받은 클래스
        // ViewHolder를 주 생성자에서 Property로 정의해야 한다.
        inner class RecyclerViewMainViewHolder(var mainRowBinding: MainRowBinding) : RecyclerView.ViewHolder(mainRowBinding.root), OnClickListener{
            // 항목을 눌렀을 때
            override fun onClick(v: View?) {
                if(dataList.size > 0) {
                    // ShowDataActivity를 실행한다.
                    val showDataIntent = Intent(this@MainActivity, ShowDataActivity::class.java)
                    showDataActivityLauncher.launch(showDataIntent)
                }
            }
        }

        // ViewHolder를 생성하는 메서드
        // 보여져야 하는 항목에 대한 ViewHolder 가 없을 경우 호출된다.
        // 재 사용 가능한 ViewHolder가 있으면 호출하지 않는다..
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewMainViewHolder {
            val mainRowBinding = MainRowBinding.inflate(layoutInflater)
            val recyclerViewMainViewHolder = RecyclerViewMainViewHolder(mainRowBinding)

            mainRowBinding.apply {
                // 터치 영역을 전체로 하기 위해 가로 세로 길이를 설정한다.
                root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                // onClickListener 설정
                root.setOnClickListener(recyclerViewMainViewHolder)
            }

            return recyclerViewMainViewHolder
        }

        // 항목의 전체 개수를 반환하는 메서드
        override fun getItemCount(): Int {
            if(dataList.size == 0){
                return 1
            }
            return dataList.size
        }

        // 항목에 배치되어 있는 View에 값을 설정하여 준다.
        override fun onBindViewHolder(holder: RecyclerViewMainViewHolder, position: Int) {
            holder.mainRowBinding.apply {
                if(dataList.size == 0){
                    textViewMainRow.text = "등록된 데이터가 없습니다"
                } else {
                    // textViewMainRow.text = dataList[position]
                    textViewMainRow.text = dataList[position].name
                }
            }
        }
    }
```

### 사용자가 입력한 데이터를 Intent에 담아 전달하는 코드를 작성한다.

[InputDataActivity.kt - settingTextFieldAge()]

```kt
                // 데이터를 담을 인텐트를 생성한다.
                val dataIntent = Intent()
                dataIntent.putExtra("name", textFieldName.editText?.text.toString())
                dataIntent.putExtra("nickName", textFieldNickName.editText?.text.toString())
                dataIntent.putExtra("age", textFieldAge.editText?.text.toString().toInt())

                setResult(RESULT_OK, dataIntent)
```

### 입력 화면에서 돌아왔을 때 리사이클러뷰를 재구성하도록 한다.

[MainActivity.kt - settingInputDataActivityLauncher()]

```kt
            // 데이터가 전달된 것이 있다면
            if(it.resultCode == RESULT_OK){
                if(it.data != null){
                    // 데이터를 추출한다.
                    val name = it.data?.getStringExtra("name")
                    val nickName = it.data?.getStringExtra("nickName")
                    val age = it.data?.getIntExtra("age", 0)
                    // 객체에 담는다.
                    val dataModel = DataModel(name!!, nickName!!, age!!)
                    // 리스트에 담는다.
                    dataList.add(dataModel)
                    // 리사이클러뷰를 갱신한다.
                    activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                }
            }
```

---

# 항목을 부르면 데이터를 출력하는 부분을 구현한다.


### 항목을 누르면 호출되는 곳에 데이터를 담는 코드를 작성한다.

[MainActivity.kt - RecyclerViewMainAdapter - RecyclerViewMainViewHolder - onClick()]

```kt
                    // 사용자가 누른 항목 번째 객체에서 데이터를 추출해 Intent에 담아준다.
                    showDataIntent.putExtra("name", dataList[adapterPosition].name)
                    showDataIntent.putExtra("nickName", dataList[adapterPosition].nickName)
                    showDataIntent.putExtra("age", dataList[adapterPosition].age)
```
### 데이터를 보여주는 부분을 구현한다.

[ShowDataActivity.kt]

```kt
    // TextView에 데이터를 셋팅하는 메서드
    fun settingTextView(){
        activityShowDataBinding.apply {
            textViewShowDataName.text = intent.getStringExtra("name")
            textViewShowDataNickName.text = intent.getStringExtra("nickName")
            textViewShowDataAge.text = intent.getIntExtra("age", 0).toString()
        }
    }
```

