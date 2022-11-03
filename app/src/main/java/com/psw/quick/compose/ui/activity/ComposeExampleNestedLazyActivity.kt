package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleNestedLazyActivity : ComponentActivity() {
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

    val bigData  = (0..10).map { "data $it" }
    val bigData2 = (0..100).map { "data $it" }
    val bigData3 = (0..1000).map { "data $it" }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ExampleMain() {
        // LazyColumn에서 item을 사용하여
        // NestedScroll처럼 사용
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            // Header
            item {
                Text("List Test", style = TextStyle(fontSize = 30.sp, color = Color.Black))
            }

            // ex1) list를 items로 처리함
            items(items = bigData, itemContent = { item ->
                Text("$item", modifier = Modifier.padding(start=20.dp),
                    style = TextStyle(fontSize = 15.sp, color = Color.Black))
            })

            // Header
            item {
                Text("LazyRow Test", style = TextStyle(fontSize = 30.sp, color = Color.Black))
            }

            // ex2) item로 둘러쌓은 후, LazyRow를 사용하여 list 처리
            item {
                LazyRow{
                    // item으로 섹션처리(LazyRow 예제)
                    items(items = bigData2, itemContent = { item ->
                        Box( modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .padding(5.dp)
                            .background(Color.Black)){
                            Text("$item",
                                modifier=Modifier.align(Alignment.Center),
                                color = Color.White, textAlign = TextAlign.Center)
                        }
                    })
                }
            }

            // Header
            item {
                Text("Grid Test", style = TextStyle(fontSize = 30.sp, color = Color.Black))
            }

            // 제공되는 Grid가 아직까지 실험상태에 있다. 그래서
            // Google의 Accompanist FlowLayout library를 사용하여 구현해야한다.
            item{
                val gridSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                FlowRow(mainAxisSize = SizeMode.Expand,
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween) {
                    bigData3.forEach {
                        item ->
                        Card(modifier = Modifier
                            .size(gridSize)
                            .padding(5.dp), backgroundColor = Color.LightGray){
                            Box(modifier = Modifier.fillMaxSize().background(Color.Red)) {
                                Text("${item}",
                                    style = TextStyle(color = Color.White),
                                    modifier =Modifier.align(Alignment.Center),
                                    textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    }
}