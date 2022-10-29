package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleTextActivity : ComponentActivity() {
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
        val weightLst = listOf(
            Pair("ExtraBold", FontWeight.ExtraBold),
            Pair("Bold", FontWeight.Bold),
            Pair("Medium", FontWeight.Medium),
            Pair("Normal", FontWeight.Normal),
            Pair("Light", FontWeight.Light),
            Pair("Thin", FontWeight.Thin)
        )

        val ellipsisLst = listOf(
            Pair("Ellipsis - Text", TextOverflow.Ellipsis),
            Pair("Clip - Text", TextOverflow.Clip),
            Pair("Visible - Text", TextOverflow.Visible)
        )

        val scrState = rememberScrollState()

        // Text의 크기는 SP가 기본.
        Column (modifier = Modifier
            .padding(20.dp)
            .background(Color.White)
            .verticalScroll(scrState)){


            // FontWeight 예제.
            Text("FontWeight 리스트", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))
            weightLst.forEach { item ->
                Text("${item.first}", fontWeight = item.second, color = Color.Gray)
            }

            // ellipsis 예제. - 범위를 넘어가는 문자열
            Text("ellipsis 리스트", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))
            ellipsisLst.forEach { item ->
                Box(modifier = Modifier.width(55.dp)){
                    Text("${item.first}", maxLines = 1, overflow = item.second, color = Color.Gray)
                }
            }

            // TextStyle 예제. TextStyle 사용하기
            Text("TextStyle", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))

            Text("style= TextStyle(...)", style = TextStyle(
                shadow = Shadow(
                    color = Color.White,
                    offset = Offset(5f, 5f),
                    blurRadius = 5f
                ),
                color = Color.LightGray,
                background = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Thin,
                fontStyle = FontStyle.Italic

            ))

        }
    }

}



