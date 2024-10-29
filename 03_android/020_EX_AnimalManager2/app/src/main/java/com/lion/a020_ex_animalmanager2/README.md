# ViewBinding 셋팅

### build.gradle.kts (Module:App) 에 ViewBinding 셋팅을 한다. 

[build.gradle.ksts]
```kt
    viewBinding {
        enable = true
    }
```

### MainActivity에 기본 코드를 작성해준다.

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


# 화면 작업

### 기본 layout을 LinearLayout Vertical로 변경한다.

```xml
LinearLayout
    - orientation : vertical
```

### ScrollView를 배치한다.
```text
    ScrollView

        LinearLayout
            - orientation : vertical
```

### 동물 종류를 선택하기 위한 Chip를 배치한다.

```text

            TextView
                - text : 동물 종류

            ChipGroup
                - id : chipGroupAnimalType
                - singleSelection : true
                - checkedChip : chipDogType
                - layout_height : wrap_content

                Chip
                    - id : chipDogType
                    - text : 강아지
                    - style : Filter

                Chip
                    - id : chipCatType
                    - text : 고양이
                    - style : Filter

                Chip
                    - id : chipParrotType
                    - text : 앵무새
                    - style : Filter
```

### 전체적인 여백을 설정한다.
[MainActivity.kt]

```kt
            v.setPadding(systemBars.left + 50, systemBars.top, systemBars.right + 50,
                systemBars.bottom)
```

### 동물 이름을 입력받을 TextField를 배치한다.

```text
            Space
                - layout_height : 20dp
                
            TextInputLayout
                - id : textFieldAnimalName
                - hint : 동물 이름
                - endIconMode : clear_text
                - layout_height : wrap_content
                
                TextInputEditText
                    - hint : 삭제
                    - singleLine : true
```

### 동물의 나이를 설정하기 위한 Slider를 배치한다.
```text
            Space
                - layout_height : 20dp

            TextView
                - text : 동물의 나이

            Slider
                - id : sliderAnimalAge
                - valueFrom : 0.0
                - valueTo : 50.0
                - stepSize : 1.0
                - labelBehavior : floating
```

### 성별을 선택하기 위한 RadioButton을 배치한다.

```text

            Space
                - layout_height : 20dp

            TextView
                - text : 성별

            RadioGroup
                - id : radioGroupGender
                - layout_height : wrap_content
                - checkedButton : radioButtonMale

                RadioButton
                    - id : radioButtonMale
                    - text : 숫컷

                RadioButton
                    - id : radioButtonFemale
                    - text : 암컷
```

### RangeSlider의 초기값을 위한 값을 정의한다

[ res/values/array.xml ]
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <array name="animal_age">
        <item>10.0</item>
        <item>30.0</item>
    </array>
</resources>
```

### 동물의 몸무게 범위를 설정하기 위한 RangeSlider를 배치한다.

```text

            Space
                - layout_height : 20dp

            TextView
                - text : 몸무게 범위

            RangeSlider
                - id : rangeSliderAnimalWeight
                - valueFrom : 0.0
                - valueTo : 200.0
                - stepSize : 1.0
                - labelBehavior : floating
                - values : array1.xml
```

### 좋아하는 간식 선택을 위한 Chip을 구성해준다.

```text
            Space
                - layout_height : 20dp

            TextView
                - text : 좋아하는 간식

            ChipGroup
                - id : chipGroupAnimalFood
                - layout_height : wrap_content

                Chip
                    - id : chipApple
                    - text : 사과
                    - style : Filter

                Chip
                    - id : chipBanana
                    - text : 바나나
                    - style : Filter

                Chip
                    - id : chipJujube
                    - text : 대추
                    - style : Filter
```

### 버튼들과 출력할 TextView를 배치해준다.

```text


            Space
                - layout_height : 20dp

            Button
                - id : buttonSave
                - text : 입력 완료

            Button
                - id : buttonShow
                - text : 동물 정보 출력

            TextView
                - id : textViewResult
                - textAppearance : Large
```

