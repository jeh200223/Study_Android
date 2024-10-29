# CardView

- View들을 모아서 관리할 수 있는 요소
- 외부에 테두리가 생긴다

### 주요 속성
- cardCornerRadius : 각 모서리의 둥근 정도를 설정한다.
- contentPadding : 내부 여백을 설정한다
- style : CardView의 스타일을 설정한다.(Outlined, Elevated, Filled)
- clickable : true를 주면 클릭 이벤트에 반응한다.
- checkable: 체크가 가능하게 설정한다. clickable을 true를 줘서 click 리스너에서 체크박스처럼 동작되게 구현해줘야 한다