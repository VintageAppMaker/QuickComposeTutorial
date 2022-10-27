package com.psw.quick.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleRowColMainActivity : ComponentActivity() {
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

        Column(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
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

        }

    }
}



