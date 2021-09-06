package com.psw.quick.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    testMain()
                }
            }
        }
    }
}

@Composable
fun TextOut(message: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()             // width
            .background(Color.Red),     // 백그라운드
        color = Color(android.graphics.Color.parseColor("#efefef") ),             // 색상
        text  = "$message!",         // text
        textAlign = TextAlign.Center    // Align
    )
}

@Composable
fun testMain(){
    basic_layout()
}

@Composable
private fun basic_layout() {
    // linearlayout과 유사 orientation vertical
    Column(
        Modifier
            .background(Color.Blue)
            .fillMaxWidth()    // %를 설정하여 채우기를 조절가능
            .wrapContentHeight(),  // %를 설정하여 채우기를 조절가능
        horizontalAlignment = Alignment.CenterHorizontally, // Alignment이다.
        verticalArrangement = Arrangement.Center            // Arrangement이다.
    ) {
        // Basic
        Image(
            painter = rememberImagePainter("https://image.api.playstation.com/vulcan/ap/rnd/202103/0200/9RHJbZ83bo1d61vdHe9NWxhl.png"),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        // Advanced
        Image(
            painter = rememberImagePainter(
                data = "https://image.api.playstation.com/vulcan/ap/rnd/202103/0200/9RHJbZ83bo1d61vdHe9NWxhl.png",
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(84.dp)
                .padding(16.dp)
        )
        TextOut("hi, this is test")
        Divider(
            color = Color.Transparent,
            thickness = 10.dp
        ) // 구분선
        TextOut("Next line")

        Column(
            Modifier
                .background(Color.Yellow)
                // margin이 없다. padding을 두번 활용해야 한다.
                .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 20.dp)
                .fillMaxHeight(0.4f)     // %를 설정하여 채우기를 조절가능
        ) {
            TextOut("2. basic_layout2")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuickComposeTutorialTheme {
        testMain()
    }
}