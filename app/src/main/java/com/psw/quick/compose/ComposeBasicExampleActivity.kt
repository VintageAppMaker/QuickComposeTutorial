package com.psw.quick.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeBasicExampleActivity : ComponentActivity() {
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
}


@Composable
fun ExampleMain(
) {

    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.Cyan)) {

        Box(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Red)) {

            Box(modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)) {

                Text("😀", modifier = Modifier.align(Alignment.TopStart))
                Text("😀", modifier = Modifier.align(Alignment.TopCenter))
                Text("😀", modifier = Modifier.align(Alignment.TopEnd))
                Text("🐯", modifier = Modifier.align(Alignment.Center), fontSize = 90.sp)
                Text("😀", modifier = Modifier.align(Alignment.BottomStart))
                Text("😀", modifier = Modifier.align(Alignment.BottomCenter))
                Text("😀", modifier = Modifier.align(Alignment.BottomEnd))

            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    QuickComposeTutorialTheme {
        ExampleMain()
    }
}