package com.psw.quick.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    private fun toast(s : String ){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    @Composable
    private fun main_layout() {
        val scrollState = rememberScrollState()
        Column(
            Modifier
                .verticalScroll(scrollState) // scroll 관리
                .background(Color.White)
                .fillMaxWidth()    // %를 설정하여 채우기를 조절가능
                .wrapContentHeight(),  // %를 설정하여 채우기를 조절가능
            
            horizontalAlignment = Alignment.CenterHorizontally, // Alignment이다.
            verticalArrangement = Arrangement.Center            // Arrangement이다.
        ) {


            Header("예제 리스트")

            (0..10).forEach {
                CardView("테스트", "설명입니다.", {
                    Intent(this@MainActivity, XMLActivity::class.java)?.apply {
                    startActivity(this)
                }})
            }

        }
    }

    @Composable
    fun CardView(title: String, desc : String, fnClick : ()-> Unit = {} ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable {
                    fnClick()
                },
            elevation = 10.dp,
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier.padding(15.dp)

            ) {
                Row(){

                    Image(
                        painter = rememberImagePainter(
                            data = "https://3.bp.blogspot.com/-VVp3WvJvl84/X0Vu6EjYqDI/AAAAAAAAPjU/ZOMKiUlgfg8ok8DY8Hc-ocOvGdB0z86AgCLcBGAsYHQ/s1600/jetpack%2Bcompose%2Bicon_RGB.png",
                            builder = {
                                transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(2.dp)
                    )

                    Spacer(Modifier.width(10.0.dp))

                    Text(
                        buildAnnotatedString {

                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Black)
                            ) {
                                append("제목: ")
                            }

                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                            ) {

                                append("${title}")
                            }
                        },

                        Modifier.align(Alignment.CenterVertically)

                    )
                }

                Spacer(Modifier.height(20.0.dp))


                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Black)
                        ) {
                            append("설명: ")
                        }

                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Gray)
                        ) {
                            append("$desc")
                        }

                    }
                )
            }
        }
    }

    @Composable
    fun Header(message: String) {
        val backColor = Color(android.graphics.Color.parseColor("#993333") )
        Text(
            modifier = Modifier
                .fillMaxWidth()             // width
                .background(backColor),     // 백그라운드
            color = Color(android.graphics.Color.parseColor("#efefef") ),             // 색상
            text  = "$message!",         // text
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }



    @Composable
    @Preview(showBackground = true)
    fun testMain(){
        main_layout()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuickComposeTutorialTheme {
        //testMain()
    }
}
