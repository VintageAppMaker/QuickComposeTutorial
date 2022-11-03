package com.psw.quick.compose.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// 프로젝트를 신규로 만들시
// MVVM은 기본구조이기에
// ViewModel에서 비지니스 로직을 코루틴으로 구현하는 것은
// 일반화 되어있다.
class ExampleViewModel : ViewModel(){
    var title = mutableStateOf("")
    var count = mutableStateOf(0)

    // StateFlow를 사용한다.
    private val _State = MutableStateFlow<ActionState>(ActionState.Nodata)
    val actionState: StateFlow<ActionState> = _State

    // 통신함수를 가상으로 구현
    fun getData(){
        CoroutineScope(Dispatchers.IO).launch {
            _State.value = ActionState.Nodata

            delay(2000)
            _State.value = ActionState.Loading

            delay(2000)
            _State.value = ActionState.Loaded(
                (0..10).map { "data => $it" }
                    .toList()
            )

        }
    }

    // 액션 상태정의
    sealed class ActionState {
        object Nodata : ActionState()
        object Loading : ActionState()
        class Loaded(val data: List<String>) : ActionState()
        class Error(val message: String) : ActionState()
    }
}

class ComposeExampleViewModelActivity : ComponentActivity() {
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
    fun ExampleMain( // 최상위 Composable 함수에만 사용
    ) {
        val scrStateCol  = rememberScrollState()

        // jetpack 버전이 업그레이드되면서
        // 내부에서 사용시 remember를 반드시 사용해야 한다.
        val exampleViewModel: ExampleViewModel = remember {
            ExampleViewModel()
        }

        Column (modifier = Modifier
            .padding(20.dp)
            .background(Color.White)
            .verticalScroll(scrStateCol),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            //  viewModel의 StateFlow값을 대기
            val rst = exampleViewModel.actionState.collectAsState().value
            when(rst ){
                is ExampleViewModel.ActionState.Nodata ->{
                    Text("데이터가 없습니다",
                        style = TextStyle(fontSize = 30.sp, color = Color.Black),
                        textAlign = TextAlign.Center)
                }

                is ExampleViewModel.ActionState.Loading ->{
                    Text("로딩중입니다. ",
                        style = TextStyle(fontSize = 30.sp, color = Color.Black),
                        textAlign = TextAlign.Center)

                    LinearProgressIndicator()
                }

                is ExampleViewModel.ActionState.Loaded ->{
                    Text("결과",
                        style = TextStyle(color = Color.Black, fontSize = 30.sp ),
                        textAlign = TextAlign.Center)
                    Divider(thickness = 3.dp, color = Color.LightGray)

                    // ActionState.Loaded로 넘겨진 데이터는
                    // data로 액세스 가능
                    rst.data.forEach {
                        Text("$it", style = TextStyle(color = Color.Black))
                    }
                    Divider(thickness = 3.dp, color = Color.LightGray)
                }

                else -> {}
            }

            Spacer(modifier = Modifier.height(20.dp))

            // viewModel의 인스턴스를
            // 다른 컴포저블 함수에 넘기면 안된다.(공식문서에서 강하게 언급)
            // 메모리 누수 발생가능성이 높아지기에
            // viewModel의 값을 파라메터로 넘기는 호이스팅을 사용한다.
            buildCounter(exampleViewModel.count.value, {exampleViewModel.count.value++})
            buildRestartButton(exampleViewModel::getData)
        }

        // 최초1회 실행
        LaunchedEffect(key1 = Unit){
            exampleViewModel.getData()
        }

    }

    @Composable
    private fun buildCounter(count: Int, fnInc : ()->Unit) {
        Text("count : ${count}",
            style = TextStyle(fontSize = 30.sp, color = Color.Black),
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { fnInc() }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
            Text("inc ++",
                style = TextStyle(fontSize = 15.sp, color = Color.Black),
                textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }

    @Composable
    private fun buildRestartButton(fnRestart : ()->Unit) {
        Divider(thickness = 3.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { fnRestart() }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
            Text("getData()",
                style = TextStyle(fontSize = 15.sp, color = Color.Black),
                textAlign = TextAlign.Center)
        }
    }
}