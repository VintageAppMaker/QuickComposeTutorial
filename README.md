# Android jetpack compose 빠르게 시작하기
> 개발자를 위한 핵심만 빠르게 시작하기

필수기초지식
1. 선언형 패러다임의 이해가 필요함(화면과 데이터는 바인딩으로 동작한다).

~~~kotlin
 UI = f(state)
 🔈 UI는 상태에 따라 자동으로 변한다. 
 🔈 상태는 외부에서 변경되지만, UI와 변수로 연결되어 있다.
 🔈 View는 상태가 변경될 때마다 다시 그려진다.
 🔈 그런 이유로 View는 이름이 없고, 
   View에서 사용할 수 있는 메소드도 없다
   명령형 구조처럼 [객체명.setText()] 같은 코드는 구현할 수 없다. 
~~~

2. @Preview
만족스러운 수준은 아니지만, 미리보기 기능이 어느정도 가능하다.
~~~kotlin
@Preview 어노테이션은 파라메터가 없는 함수에서만 사용가능
@Preview 함수가 여러개이면 그만큼 미리보기 가능
@Compose 함수는 Activity 외부에서 구현해서 사용해도 무방
@Compose 함수에서 고정값이 아니라면(getString, 통신, 등등) 보여지지 않음
@Preview가 완벽하지 않다. 에뮬레이터와 단말기의 적용이 다를 때가 있다.
~~~

3. 코드량과 최종바이너리가 훨씬 줄어든다.
4. @Composable 함수는 모듈화하기 좋다.
5. Flutter보다 코딩하기 수월하다
~~~kotlin
 
 Flutter의 dart는 특별히 불편한 것도 좋은 것도 없다.(무난함이 장점이다)
 🔈 Flutter의 경우, 함수에 네임드 파라메터를 넣는 식으로 구현된다.
 🔈 코드 중간에 로직을 넣기 힘든 경우가 대부분이다.
 🔈 가독성이 떨어진다.
 
 -> 그래서 들여쓰기도 2칸이다.
   
 Android의 kotlin은 코드의 강력함과 더불어 DSL을 가지고 있다.
 🔈 Jetpack compose의 경우, DSL로 되어 있다. 
 🔈 Composable 함수 내에서 UI와 로직의 제한이 거의 없다. 
 🔈 가독성이 비교적 높다.

-> 그래서 들여쓰기도 4칸이다.
 
~~~

6. Flutter보다 빠르고 상태관리, 디버깅이 편하다
~~~kotlin
 
 🔈 속도는 개발자라면 느낄 수 있을 정도로 빠르다
 🔈 statful, stateless 관리가 비교적 편하다(remember 변수와 상태호이스팅).
 🔈 위의 이유로 앱의 속도저하가 발생하는 경우가 비교적 적다. 
 🔈 Flutter의 UI build시 발생되는 에러에 비해, 원인추적이 용이하다. 
 
~~~

7. 개발환경은 Flutter가 쾌적한 편이다.
~~~kotlin
 
 🔈 메모리 사용량이 많아졌다
 🔈 Flutter의 개발환경 중 하나인 VSCode에 비해 상당히 무겁다.
 🔈 Flutter는 실시간 갱신이 잘되어 있으며, 결과물을 Desktop이나 Web으로도 확인가능하다.
   
~~~

#### Content
- [메인소스](.\app\src\main\java\com\psw\quick\compose\ui\mainActivity.kt)