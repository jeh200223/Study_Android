# Activity 작업 방식

1. activity_main.xml 에 화면 작업을 해준다.
- UI 요소들을 배치하고 필요한 속성들을 설정해준다.
- 여기서 하는 작업들은 화면이 처음 나타났을 때의 모습을 설정하는 것이다
- tools로 시작하는 속성들 : 화면 모양을 작업할 때만 적용되는 속성들. 여기서 설정한  속성 값들은 실제로 실행하면 적용되지 않는다.
- 화면을 디자인할 때 임시로 설정해주고 싶은 것들을 설정해준다.
- 그 외에(android, app 등)로 시작하는 속성들 : 실행하면 반영되는 속성들이다.
- 속성을 설정할 때 빈 칸으로만 나온면 : 자유롭게 입력
- 속성을 설정할 때 깃발이 나온다면 : 항목들을 제공하고 1개 이상 설정할 수 있다.
- 속성을 설정할 때 스포이드가 나온다면 : 색상 등의 값을 고를 수 있는 다얼로그가 뜬다.
- 속성을 설정할 때 ∨ 아이콘이 나온다면 : 항목들을 제공하고 하나를 설정할 수 있다.

2. Activity 파일 작업을 해준다.
- 코드에서 사용하고 싶은 화면 UI 요소 객체를 담을 변수를 선언한다.
- 위에서 선언한 변수에 UI 요소 객체를 담아준다. (반드시 setContentView 다음에 해야 한다.)
- 객체가 가지고 있는 프로퍼티나 메서드들을 이용하여 필요한 작업을 해준다.
- 화면 요소에 대한 어떠한 사건을 처리하겠다면 리스너를 사용한다.


3. 번외
[ 메서드를 오버라이딩 했는데 매개변수가 p0, p1 이나 변수의 타입으로 나타날 경우 ]
- build.gradle.kts (Module:app) 파일을 열어 target sdk 버전을 확인한다.
- 상단 메뉴에서 Tools > SDK Manaer를 실행한다.
- SDK Platforms 탭에서 위에서 확인한 target sdk 버전과 동일한 api 버전을 체크하고 finish를 눌러준다.
