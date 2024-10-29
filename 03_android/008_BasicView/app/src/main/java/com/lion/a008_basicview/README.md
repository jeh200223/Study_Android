# TextView
- 사용자에게 문자열을 보여주기 위해 사용하는 View

### 주요 속성
- text : 사용자에게 보여줄 문자열을 설정한다.
- lines : TextView를 통해 보여줄 문자열의 라인 수를 설정한다. 라인수가 고정된다. 생략시 무제한
- maxLines : 최대 라인 수. 라인수가 변한다.
- textColor : 표시되는 문자열의 색상
- textSize : 표시되는 문자열의 크기. sp 단위의 크기
- textAppearance : 문자열의 크기, 폰트 등등 미리 설정되어 있는 형태를 선택할 수 있다.
- fontFamily : 폰트를 설정한다
- typeface : 폰트내에서 문자열의 스타일을 설정한다(폰트가 지원해야 한다)
- lineSpacingExtra : 줄 간견을 설정할 수 있다
- textStyle : 문자열을 두껍게(bold), 기울어지게(italic), 모두 대문자로 설정할 수 있다.
- textAlignment : 문자열의 가로방향 정렬

### 주요 프로퍼티
- text : 문자열을 설정하거나 가져올 수 있다

### 주요 메서드
- append : 문자열을 추가한다

---

# Button
- 사용자가 누르면 무언가 작업을 할 수 있는 View

### 주요 속성
- text : 표시할 문자열을 설정한다.

### 주요 리스너
- OnClickListener : 사용자가 버튼을 누르면 동작하는 리스너

---

# EditText
- 사용자에게 문자열을 입력받을 수 있는 View

### 주요 속성
- text : EditText에 문자열을 설정할 수 있다
- hint : 입력된 값이 없을 경우 표시할 안내 문구
- inputType : 입력 값에 대해 설정한다. 표시되는 형식, 입력 받는 값의 종류, 키보드 등에 영향을 준다.
- imeOptions : 나타나는 키보드 우측 하단에 있는 엔터키의 모양을 설정한다. 제일 마지막에 있는 입력 요소에 설정하는 것이 
일반적이다

### 주요 프로퍼티
- text : 사용자가 입력한 문자열을 가져오거나 문자열을 설정할 수 있다.

### 주요 리스너
- setOnEditorActionListener : 키보드 우측 하단의 리턴키를 눌렀을 때
- addTextChangedListener : 입력할 때 마다 동작하는 리스너

