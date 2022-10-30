package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleRemeberStateActivity : ComponentActivity() {
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

    // remember{}를 사용할 경우, Composable은 상태관리를 하는 Stateful이 된다.
    // Stateful은 성능저하를 가져온다(해당함수가 전체갱신됨)
    // 최소한으로 만들기 위해서 hoisting을 사용한다(부분갱신).
    // hoisting은 Composable 함수에 remember를 가지지않고
    // 외부에서 파라메터를 받아처리하는 방법이다.
    @Composable
    fun ExampleMain(
    ) {
        val scrState = rememberScrollState()
        var count = remember{ mutableStateOf(0)}

        Column (modifier = Modifier
            .padding(20.dp)
            .background(Color.White)
            .verticalScroll(scrState)){

            // remember 사용예제
            Text("remeber 사용", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))

            rememberStateExample()

            // hoisting 사용예제
            Text("hoisting 사용", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))

            hoistingExample(count.value, {count.value++})
        }
    }

    @Composable
    fun rememberStateExample(){
        var count = remember{ mutableStateOf(0)}
        Row(Modifier.fillMaxWidth()){
            Text("${count.value}",
                color = Color.Blue,
                modifier = Modifier.weight(1.0f)
                    .fillMaxHeight().align(Alignment.CenterVertically),
                textAlign = TextAlign.Center)

            Button(onClick = {count.value++}, modifier = Modifier.weight(1.0f).padding(end= 5.dp)){
                Text("click +")
            }
        }
    }

    @Composable
    fun hoistingExample(count : Int, fnOnChange : ()->Unit){
        Row(Modifier.fillMaxWidth()){
            Text("${count}",
                color = Color.Blue,
                modifier = Modifier.weight(1.0f)
                    .fillMaxHeight().align(Alignment.CenterVertically),
                textAlign = TextAlign.Center)

            Button(onClick = {fnOnChange()}, modifier = Modifier.weight(1.0f).padding(end= 5.dp)){
                Text("click +")
            }
        }
    }

}



