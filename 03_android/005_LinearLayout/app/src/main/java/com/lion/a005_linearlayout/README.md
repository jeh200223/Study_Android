# LinearLayout
- 방향성을 가지고 View를 배치하는 Layout

### 주요 속성
1. orientation : 배치 방향을 설정한다
- horizontal(기본) : 좌측에서 우측으로 배치한다.
- vertical : 위에서 아래로 배치한다.
- horizontal 인 경우에는 한 칸에 하나의 View만 배치할 수 있다
- vertical 인 경우에는 한 줄에 하나의 View만 배치할 수 있다.

2. layout_weight : View가 배치되고 남은 공간을 각각 얼마나 가져갈 것인가를 설정한다.
- 가져갈 비율을 정수로 설정한다.
- 만약 남은 공간을 가져가는 것이 아닌 크기의 비율을 설정하겠다면 Horizontal 인 경우에는 layout_width를 
Vertical인 경우에는 layout_height 를 match_parent로 설정해준다.(주의. 뜻대로 안될 수도 있다)