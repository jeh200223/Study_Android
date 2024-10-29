# Floating Action Button

- 공중에 떠있는 듯 한 모습을 가진 버튼

### 주요 속성
- srcCompat : FAB에 보여줄 아이콘 이미지를 설정한다.
- fabCustomSize : FAB의 크기를 설정한다

### 주요 메서드
- hide : 사라진다
- show : 나타난다

---

# Extended Floating Action Button
- 접었다 폈다 할 수 있는 FAB

### 주요 속성
- srcCompat : FAB에 보여줄 아이콘 이미지를 설정한다.
- text : 문자열을 설정한다.
- fabCustomSize : FAB의 크기를 설정한다

### 주요 메서드
- hide : 사라진다
- show : 나타난다
- shrink : 접는다
- extended : 펼친다

# Icon Button
- 버튼의 스타일을 IconButton을 설정한다.
- icon 속성을 통해 보여줄 아이콘을 설정한다.
- 가로 길이는 wrap_content
- 만약 layout_weight가 있다면 삭제한다
- text 속성도 삭제한다.
- style : Filled, Tonal, Outlined


# Material Button Toggle Group
- 다수 버튼들을 관리하는 버튼 그룹
- 그룹 내에 배치된 버튼은 체크박스나 라디오 버튼 처럼 사용한다.
- 체크박스 형태로 사용할 때 최초에 선택되어 있는 버튼을 설정하려면 반드시 코드로 해야 한다.
- singleSelection을 true로 주면 라디오 처럼 사용할 수 있다.
- 이 때, checkedButton 속성으로 최초에 선택되어 있는 버튼을 설정해준다.