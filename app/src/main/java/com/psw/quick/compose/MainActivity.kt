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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.psw.quick.compose.ui.activity.*
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ComposeMain()
                }
            }
        }
    }

    @Composable
    private fun main_layout() {
        val scrollState = rememberScrollState()

        Box(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)){
            Column(
                Modifier
                    .verticalScroll(scrollState) // scroll 관리
                    .background(Color.White)
                    .fillMaxWidth()    // %를 설정하여 채우기를 조절가능
                    //.wrapContentHeight(),  // %를 설정하여 채우기를 조절가능
                    .fillMaxHeight(),

                horizontalAlignment = Alignment.CenterHorizontally, // Alignment이다.
                verticalArrangement = Arrangement.Center            // Arrangement이다.
            ) {


                Header("예제 리스트")

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xff993333))){
                    Image(
                        modifier = Modifier.aspectRatio(1.0f),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null, // decorative element
                        contentScale = ContentScale.FillBounds
                    )

                    Text(
                        buildAnnotatedString {

                            withStyle(style = SpanStyle(color = Color.Red)
                            ) {
                                append("Compose 예제\n")
                            }

                            withStyle(style = SpanStyle(color = Color(0xFFa3aaB8))
                            ) {

                                append("빠르게 시작하기 ")
                            }
                        },

                        Modifier.align(Alignment.BottomEnd)

                    )
                }

                Spacer(Modifier.height(30.dp))

                buildCardList()

            }
        }
    }

    @Composable
    fun buildCardList(){
        CardView("#1 Box", resources.getString(R.string.example_1),
            ComposeExampleBoxActivity::class.java, "example_1.html")

        CardView("#2 Row, Column", resources.getString(R.string.example_2),
            ComposeExampleRowColActivity::class.java, "example_2.html")

        CardView("#3 ConstraintLayout", resources.getString(R.string.example_3),
            ComposeExampleConstraintsActivity::class.java, "example_3.html")

        CardView("#4 BoxWithConstraints", resources.getString(R.string.example_4),
            ComposeExampleBoxWithConstraintsActivity::class.java, "example_4.html")

        CardView("#5 Shape", resources.getString(R.string.example_5),
            ComposeExampleShapeActivity::class.java, "example_5.html"
        )

        CardView("#6 Text", resources.getString(R.string.example_6),
            ComposeExampleTextActivity::class.java, "example_6.html"
        )

        CardView("#7 Button, Image", resources.getString(R.string.example_7),
            ComposeExampleButtonImageActivity::class.java, "example_7.html"
        )

        CardView("#8 StateManagement", resources.getString(R.string.example_8),
            ComposeExampleRemeberStateActivity::class.java, "example_8.html"
        )

        CardView("#9 TextField", resources.getString(R.string.example_9),
            ComposeExampleTextFieldActivity::class.java, "example_9.html"
        )

        CardView("#10 Scaffold", resources.getString(R.string.example_10),
            ComposeExampleScaffoldActivity::class.java, "example_10.html"
        )

        CardView("#11 Alert, BottomSheet", resources.getString(R.string.example_11),
            ComposeExampleAlertBottomSheetActivity::class.java, "example_11.html"
        )

        CardView("#12 CheckBox, Dropdown menu", resources.getString(R.string.example_12),
            ComposeExampleCheckBoxDropDownMenuActivity::class.java, "example_12.html"
        )
    }



    @Composable
    fun CardView(title: String, desc : String, clsLaunch: Class<*>, launchFile : String  ) {

        val openDialog = remember { mutableStateOf(false)  }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable(onClick = {
                    openDialog.value = true
                }),
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

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color.Black)
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color.Black)
                        ) {
                            append("설명: ")
                        }

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color.Gray)
                        ) {
                            append("$desc")
                        }

                    }
                )
            }
        }

        if(openDialog.value){
            AlertDialog(
                backgroundColor = Color.White.copy(alpha = 0.9f),
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "선택", color = Color.Gray)
                },
                text = {
                    Text("예제실행 및 소스보기", color = Color.Gray)
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        onClick = {
                            openDialog.value = false
                            Intent(this@MainActivity, clsLaunch).apply {
                                startActivity(this)
                            }
                        }) {
                        Text("예제실행", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        onClick = {
                            openDialog.value = false
                            Intent(this@MainActivity, ComposeExampleSourceViewActivity::class.java).apply {
                                putExtra("file", launchFile)
                                startActivity(this)
                            }
                        }) {
                        Text("소스보기", color = Color.Black)
                    }
                }
            )

        }

    }

    @Composable
    fun Header(message: String) {
        val backColor = Color(0xff993333)
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
    fun ComposeMain(){
        main_layout()
    }

}

