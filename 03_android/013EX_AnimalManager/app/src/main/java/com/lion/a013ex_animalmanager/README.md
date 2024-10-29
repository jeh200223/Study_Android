# View Binding 셋팅

### build.gradle.kts 파일에 ViewBinding 설정을 한다.

```kt
    viewBinding{
        enable = true
    }
```

### MainActivity에 ViewBinding 코드를 작성해준다.

```kt
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
```

---

# 화면 디자인

### res/drawable 폴더에 사용할 아이콘 이미지를 넣어준다.

### 화면디자인.txt 파일에 화면 설계를 먼저 한다.

```text
LinearLayout
    - orientation : vertical

    TextInputLayout
        - id : textFieldName
        - hint : 이름
        - startIconDrawable : icon1
        - endIconMode : clear_text
        - layout_height : wrap_content

        TextInputEditText
            - hint : 삭제
            - singleLine : true

    TextInputLayout
        - id : textFieldType
        - hint : 종류
        - startIconDrawable : icon2
        - endIconMode : clear_text
        - layout_height : wrap_content

        TextInputEditText
            - hint : 삭제
            - singleLine : true

    TextInputLayout
        - id : textFieldAge
        - hint : 나이
        - startIconDrawable : icon3
        - endIconMode : clear_text
        - layout_height : wrap_content

        TextInputEditText
            - hint : 삭제
            - inputType : number|number
            - imeOptions : actionDone
            - singleLine : true

    Button
        - id : buttonSave
        - text : 등록 완료
        - style : outlined

    Button
        - id : buttonShow
        - text : 정보 출력
        - style : outlined
```

### activity_main.xml 에 화면 작업을 한다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity" >

    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/textFieldName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="이름"
        app:endIconMode="clear_text"
        app:startIconDrawable="@drawable/icon1">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/textFieldType"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="종류"
        app:endIconMode="clear_text"
        app:startIconDrawable="@drawable/icon2">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/textFieldAge"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="나이"
        app:endIconMode="clear_text"
        app:startIconDrawable="@drawable/icon3">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:imeOptions="actionDone"
            android:inputType="number" />
    </com.google.android.material.textfield.TextInputLayout>

    <Button
        android:id="@+id/buttonSave"
        style="@style/Widget.Material3.Button.OutlinedButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="등록 완료" />

    <Button
        android:id="@+id/buttonShow"
        style="@style/Widget.Material3.Button.OutlinedButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="정보 출력" />
</LinearLayout>
```

---

# MainActivity 작업

### 키보드를 올려주는 메서드를 구현한다.
- 이 메서드를 onCreate에서 호출하게 되면 화면이 보이지 않은 상태에서 키보드를 올려달라는 요청을 하게 된다.
- 이 때, 이 요청은 무시되버린다.
- 이 때문에 키보드를 올리는 코드를 별도의 쓰래드로 뺀 다음 딜레임 타임을 줘서 onCreate가 끝날 때 까지 대기해줘야 한다.

```kt
    // 지정된 View에 focus를 주고 키보드를 올려준다.
    fun showKeyboard(view:View?){
        // 지정된 View에 포커스를 준다.
        view?.requestFocus()

        thread {
            Thread.sleep(1000)
            // 포커스를 가지고 있는 View에 대해 키보드를 올려준다.
            // 입력을 관리하는 관리자를 가져온다.
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            // 키보드를 올려준다.
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
```

### 첫 번째 입력 요소에 포커스를 준다.

[MainActivity.kt - onCreate()]
```kt
        activityMainBinding.apply {
            // 첫 번째 입력 요소에 포커스를 준다.
            showKeyboard(textFieldName.editText)
        }
```

### 저장 처리를 할 메서드를 만들어준다.

[MainActivity.kt]
```kt
    // 등록완료 처리 메서드
    fun saveData(){
        
    }
```

### 등록 완료 버튼의 리스너를 구현해준다.

[MainActivity.kt - onCreate()]
```kt
            // 등록 완료 버튼
            buttonSave.setOnClickListener {
                // 등록 처리를 하는 메서드를 호출한다.
                saveData()
            }
```

### 마지막 입력 요소에서 엔터키를 눌렀을 때의 리스너를 구현해준다.

[MainActivity.kt - onCreate()]

```kt
            // 마지막 입력 요소에 대한 엔터키 이벤트
            textFieldAge.editText?.setOnEditorActionListener { v, actionId, event ->
                // 등록 처리를 하는 메서드를 호출한다.
                saveData()
                true
            }
```

### 동물 정보를 관리하기 위한 클래스를 구현해준다.

[AnimalClass.kt]
```kt
package com.lion.a013ex_animalmanager

import android.util.Log

class AnimalClass(val name:String, val type:String, val age:Int) {

    // 정보를 출력하는 메서드
    fun showAnimalInfo(){
        Log.d("animal", "이름 : $name")
        Log.d("animal", "타입 : $type")
        Log.d("animal", "나이 : $age")
        Log.d("animal", "---------------------------")
    }
}
```

### 동물 정보를 관리할 리스트를 생성해준다.

[MainActivity.kt]
```kt
    // 동물들의 정보를 담을 list
    val animalList = mutableListOf<AnimalClass>()
```

### 동물 정보를 저장하는 메서드를 구현해주낟.

[MainActivity.kt]
```kt
    // 등록완료 처리 메서드
    fun saveData(){
        activityMainBinding.apply {
            // 각 입력요소에 입력된 내용을 가져온다.
            val nameData = textFieldName.editText?.text.toString()
            val typeData = textFieldType.editText?.text.toString()
            val ageData = textFieldAge.editText?.text.toString().toInt()
            // 동물 객체를 생성한다.
            val animalClass = AnimalClass(nameData, typeData, ageData)
            // 리스트에 담는다
            animalList.add(animalClass)
        }
    }
```

### 입력 요소들을 초기화 하는 메서드를 구현한다.

[MainActivity.kt]

```kt
    // 입력 요소 초기화
    fun resetInput(){
        activityMainBinding.apply {
            // 각 입력 요소를 비워준다.
            textFieldName.editText?.setText("")
            textFieldType.editText?.setText("")
            textFieldAge.editText?.setText("")
            // 첫 번째 입력 요소에 포커스를 준다.
            textFieldName.editText?.requestFocus()
        }
    }
```

### 입룍 요소를 초기화 하는 메서드를 각각 호출해준다.

[MainActivity.kt - onCreate()]

```kt
            // 등록 완료 버튼
            buttonSave.setOnClickListener {
                // 등록 처리를 하는 메서드를 호출한다.
                saveData()
                resetInput()
            }

            // 마지막 입력 요소에 대한 엔터키 이벤트
            textFieldAge.editText?.setOnEditorActionListener { v, actionId, event ->
                // 등록 처리를 하는 메서드를 호출한다.
                saveData()
                resetInput()
                true
            }
```

### 동물 정보를 출력하는 메서드를 만들어준다.

[MainActivity.kt]
```kt
    // 동물 정보 출력
    fun showAnimalInfo(){
        // 동물의 수 만큼 반복한다.
        animalList.forEach {
            // 출력한다.
            it.showAnimalInfo()
        }
    }
```

### 동물 정보 출력 메서드를 호출해준다.

[MainActivity.kt - onCreate()]
```kt
            // 출력 버튼을 눌렀을 때
            buttonShow.setOnClickListener {
                // 동물 정보를 출력하는 메서드를 호출한다.
                showAnimalInfo()
            }
```