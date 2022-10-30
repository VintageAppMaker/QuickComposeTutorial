package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleRowColActivity : ComponentActivity() {
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
        // Scroll을 가능하게 하려면
        // horizontalScroll이나 verticalScroll에
        // rememberScrollState()를 넘겨주어야 한다.
        val rowScr = rememberScrollState()
        val colScr = rememberScrollState()

        Column(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(colScr)
            .background(Color.Blue)){
            (0..2).forEach { Box(modifier = Modifier
                .padding(10.dp)
                .width(100.dp)
                .height(100.dp)
                .background(Color.Cyan)) }

            // 스크롤 안됨
            Row(modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()){
                (0..13).forEachIndexed { index, item -> Box(modifier = Modifier
                    .padding(10.dp)
                    .width(100.dp)
                    .height(100.dp)
                    .background(if (item % 2 == 0) Color.Yellow else Color.DarkGray)) }
            }

            // 스크롤 됨
            Row(modifier = Modifier
                .horizontalScroll(rowScr)
                .background(Color.Black)
                .fillMaxWidth()){
                (0..13).forEachIndexed{ indx, item -> Box(modifier = Modifier
                    .padding(10.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .background(if (item % 2 == 0) Color.Yellow else Color.DarkGray)){
                    Text(text = "$indx", modifier = Modifier.align(Alignment.Center))
                } }
            }

            //  weight()로 비율설정
            //  wrapContentWidth()로 widt 설정
            //  매우중요!!-> height(IntrinsicSize.Max)로
            //  Row안의 View의 height를 Row의 height 설정
            Row(modifier = Modifier
                .background(Color.White)
                .height(IntrinsicSize.Max)
                .fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                        .wrapContentWidth(Alignment.Start),
                    color = Color.Black,
                    text = "Left"
                )

                Divider(
                    color = Color.Black,
                    modifier = Modifier.fillMaxHeight().width(1.dp)
                )

                // 이곳이 제일크다. 결국 Row의 IntrinsicSize.Max는 100
                Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Black))

                Text(
                    modifier = Modifier
                        .weight(0.2f)
                        .padding(end = 4.dp)
                        .wrapContentWidth(Alignment.End),
                    color = Color.Black,
                    text = "Right"
                )
            }

            // 범위를 겹치게 하기
            overlapExample()
        }
    }

    @Composable
    fun overlapExample() {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(20.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            (0..4).forEach {
                Box(modifier = Modifier.size(50.dp).background(Color.Red))
                Box(
                    modifier = Modifier
                        .offset(x= -20.dp) // Box의 offset에 -값으로 이동
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.CenterVertically)
                ){Text("😁", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, style = TextStyle(color = Color.Red, fontSize = 15.sp))}
            }
        }
    }

}



