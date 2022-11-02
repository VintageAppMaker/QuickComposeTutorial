package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ComposeExampleLaunchEffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ExampleMain()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        QuickComposeTutorialTheme {
            ExampleMain()
        }
    }

    @Composable
    fun ExampleMain(
    ) {
        val scrStateCol  = rememberScrollState()
        val scrStateRow = rememberScrollState()
        var select = remember{ mutableStateOf(0)}

        Column (modifier = Modifier
            .padding(20.dp)
            .background(Color.White)
            .verticalScroll(scrStateCol),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Row(
                Modifier
                    .background(Color.White)
                    .horizontalScroll(scrStateRow)) {
                Button(onClick = {select.value = 0},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier
                        .padding(5.dp)
                        .border(width = 1.dp, color = Color.Gray)) {
                    Text("LaunchEffect", color = Color.Black)
                }

                Button(onClick = {select.value = 1},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier
                        .padding(5.dp)
                        .border(width = 1.dp, color = Color.Gray)) {
                    Text("SideEffect", color = Color.Black)
                }

                Button(onClick = {select.value = 2},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier
                        .padding(5.dp)
                        .border(width = 1.dp, color = Color.Gray)) {
                    Text("DisposibleEffect", color = Color.Black)
                }

                Button(onClick = {select.value = 3},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier
                        .padding(5.dp)
                        .border(width = 1.dp, color = Color.Gray)) {
                    Text("produceState", color = Color.Black)
                }

                Button(onClick = {select.value = 4},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier
                        .padding(5.dp)
                        .border(width = 1.dp, color = Color.Gray)) {
                    Text("deriveStateOf", color = Color.Black)
                }
            }

            Divider(thickness = 1.dp, color = Color.Gray)

            when(select.value){
                0 -> { ExampleDesc("LaunchEffect"){ launchEffectSimple()}}
                1 -> { ExampleDesc("SideEffect")  { sideEffectSimple()}}
                2 -> { ExampleDesc("DisposiableEffect"){DisposiableEffectSimple()} }
                3 -> { ExampleDesc("produceState"){produceStateSimple()}}
                4 -> { ExampleDesc("deriveStateOf"){deriveStateOfSimple()}}
            }
        }
    }


    @Composable
    fun ExampleDesc(title : String, content: @Composable () -> Unit){
        Text("$title ", color = Color.Black, fontSize = 18.sp)
        Divider(color = Color.Gray, thickness = 0.5.dp)
        Spacer(Modifier.height(10.dp))

        content()
    }

    // coroutine에서 실행됨
    // key값이 변경되면 실행
    @Composable
    fun launchEffectSimple(){

        var count    by remember { mutableStateOf(0) }
        var message  by remember { mutableStateOf("") }
        var message2 by remember { mutableStateOf("") }

        Column() {
            Text("\uD83D\uDC69 LaunchedEffect(key1 = Unit):\n$message2", color= Color.Black)
            Text("\uD83D\uDC69 while (true) {...}:\n$count", color= Color.Black)
            Text("\uD83D\uDC69 LaunchedEffect(key1 = count):\n$message", color= Color.Black)
        }

        // 키가 없으므로 화면이 빌딩되면 1회실행
        LaunchedEffect(key1 = Unit) {
            // 계속 호출하는 지 채크하기 위함
            message2 = "1회 최초실행"

            while (true) {
                delay(500)
                count++
                if (count == 5) break
            }
        }

        // count가 변경되면 화면이 빌딩되면 1회실행
        LaunchedEffect(key1 = count) {
           message = "launchEffect 호출 ${count}"
        }

    }

    // coroutine 아님
    // 화면이 갱신시 매번 실행됨
    @Composable
    fun sideEffectSimple(){

        var count    by remember { mutableStateOf(0) }
        Column() {
            Text("\uD83D\uDC69 변경횟수: $count", color=  Color.Red)
        }

        // 화면이 변경되면 매번실행됨
        SideEffect {
            if ( count < 100){
                count++
            }
        }
    }

    // coroutine 아님
    // key값이 변경되면 실행
    @Composable
    fun DisposiableEffectSimple(){

        var count     by remember { mutableStateOf(0) }
        var message   by remember { mutableStateOf("") }
        var message2  by remember { mutableStateOf("") }

        Column() {
            Text("\uD83D\uDC69 종료: $message2", color= Color.Black)
            Text("\uD83D\uDC69 시작: $message", color= Color.Black)
            Button(onClick = {count++},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .padding(5.dp)
                    .border(width = 1.dp, color = Color.Gray)) {
                Text("DisposibleEffect", color = Color.Black)
            }
        }

        // 실행시 이전작업을 종료시키며
        // onDispose()가 실행된다.
        // 이전작업 종료하고 새로운 작업 시작시 사용됨
        DisposableEffect(key1 = count) {
            val id = (1..1000).random()
            message = "$id"

            onDispose {
                message2 = "$id"
            }
        }
    }

    // coroutine
    // key값이 변경되면 실행
    @Composable
    fun produceStateSimple(){

        var bRun      by remember { mutableStateOf(false) }

        // coroutine
        // 이전작업 종료하거나 새로운 작업 요청시 사용됨
        // value로 값을 전달함
        // initialValue 초기값
        val randomNumber by produceState(initialValue = 0, bRun) {
            var job: Job? = null
            if (bRun) {
                job = MainScope().launch {
                    while (true) {
                        delay(500)
                        value = (0..1000).random()
                    }
                }
            }

            awaitDispose {
                job?.cancel()
            }
        }

        Column() {
            Text("\uD83D\uDC69 $randomNumber", color= Color.Black)
            Button(onClick = {bRun = !bRun},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .padding(5.dp)
                    .border(width = 1.dp, color = Color.Gray)) {
                Text("${ if(bRun) "멈춤" else "실행"}", color = Color.Black)
            }
        }

    }

    // coroutine 아님
    // key값이 변경되면 실행
    @Composable
    fun deriveStateOfSimple(){

        // list는 이렇게 선언해야 사용가능함.
        val listItems = remember { mutableStateListOf<Int>()}
        val showButtonDerive by remember {
            derivedStateOf {
                listItems.size  < 5
            }
        }

        Column() {
            Button(onClick = {listItems.add(listItems.size + 1)},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .padding(5.dp)
                    .border(width = 1.dp, color = Color.Gray)) {
                Text("추가 ${listItems.size}", color = Color.Black)
            }

            if(showButtonDerive){
                listItems.forEach {
                    Text("🎁".repeat(it), color = Color.Black)
                }
            } else {
                Text("표시할 제한개수를 넘었습니다.", color = Color.Black)
            }
        }
    }

}



