# Material Switch
- 좌우로 움직이면서 ON/OFF 상태를 설정하는 View

### 주요 속성
1. text : 스위치에 문자열을 표시할 수 있다
2. textOn, textOff, showText : ON/OFF 상태일 때의 문자열을 설정한다(사용X)
3. thumbIcon : 스위치에 지정한 아이콘을 표시해준다
4. checked : ON(true) / OFF(false) 상태를 설정한다

### 주요 프로퍼티
1. isChecked : ON(true) / OFF(false) 상태 값을 설정하거나 가져올 수 있다.

### 주요 메서드
1. toggle : ON/OFF 상태를 반전시킨다

### 주요 리스너
1. setOnCheckedChangeListener : ON/OFF 상태가 변경되었을 때

---

# RadioButton
- 1개 이상의 항목이 주어지고 그 중에서 하나만 선택할 수 있는 View
- RadioGroup을 배치하고 그 안에 RadioButton을 넣어준다.
- 하나의 RadioGroup 안에서 하나의 RadioButton을 선택할 수 있다.

### 주요 속성
1. text (RadioButton) : 라디오 버튼에 표시되는 문자열을 설정한다
2. checkedButton (RadioGroup) : Radio Group 내에서 어떠한 라디오 버튼을 선택할 것인지 설정

### 주요 프로퍼티
1. checkedRadioButtonId (RadioGroup) : 라디오 그룹내에 체크되어 있는 라디오 버튼의 ID를 가져온다

### 주요 메서드
1. check (RadioGroup) : 라디오 그룹내에서 체크하고 싶은 라디오 버튼의 아이디를 지정한다

### 주요 리스너
1. setOnCheckedChangeListener : 라디오 그룹내의 라디오 버튼 체크 상태가 변경되었을 때
