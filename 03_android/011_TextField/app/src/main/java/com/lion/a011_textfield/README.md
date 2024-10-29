# TextField

- TextInputLayout과 TextInputEditText의 조합
- 기존의 EditText를 보완한 문자열 입력 요소
- TextInputEditText는 EditText를 상속받고 있다.

### 주요 속성
1. hint
- 입력한 문자열이 없을 경우 보여지는 안내 문구.
- 사용자가 문자열을 입력하면 hint는 상단으로 올라간다.
- TextField를 배치하면 TextInputEditText에 이미 설정되어 있는데 저는 이것을 지우고 TextInputLayout에 설정합니다

2. counterEnabled  
- 현재 입력한 문자열의 글자 수를 표시한다

3. counterMaxLength 
- 입력할 수 있는 최대 문자수를 표시해준다. 그냥 표시만 한다. 글자수를 넘어가도 입력은 됩니다

4. helperTextEnabled
- TextField 하단에 문장을 표시할 수 있도록 설정한다

5. helperText
- TextField 하단에 표시할 문자열을 설정한다. helperTextEnabled가 true로 설정되어 있어야만 보인다.

6. startIconDrawable
- 좌측에 표시할 아이콘을 설정한다.

7. endIconMode
- 우측에 표시할 아이콘을 설정한다.
- None : 아무것도 설정하지 않는다.
- password_toggle : 비밀번호 입력 요소인 경우 아이콘을 누르면 입력한 내용이 보였다 안보였다하는 기능
- clear_text : 사용자가 입력한 내용이 있을 때만 나타나며 아이콘을 누르면 입력한 내용이 모두 삭제된다
- dropdown_menu : Drop Menu 메뉴 기능을 쓸때 사용한다.
- custom : 아이콘을 자유롭게 설정할 수 있다.

### 주요 프로퍼티
1. error
- TextField 하단에 붉은색 메시지를 표시하고 입력 요소 전체를 붉은색으로 표시한다.
- 입력에 대해 문제가 있을 경우 사용한다.
- null을 넣어주면 정상 상태로 돌아온다.

2. editText
- TextInputLayout안에 있는 TextInputEditText 객체가 들어있는 프로퍼티
- 이를 통해 입력된 문자열 값을 가져온다

### 주요 메서드

### 주요 리스너