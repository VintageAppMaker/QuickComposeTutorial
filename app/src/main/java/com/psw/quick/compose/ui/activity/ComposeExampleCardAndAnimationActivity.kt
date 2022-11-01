package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme
import kotlin.random.Random

class ComposeExampleCardAndAnimationActivity : ComponentActivity() {
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
        val scrState = rememberScrollState()
        Column(modifier = Modifier
            .verticalScroll(scrState)
            .padding(20.dp)
            .background(Color.White)) {

            (0..2).forEach {
                CardView(title= "#${it} gift")
            }

        }
    }

    @Composable
    fun CardView(title: String) {
        val isExpanded = rememberSaveable{ mutableStateOf(false) }
        val detail     = rememberSaveable{ mutableStateOf("") }

        // 크기 애니메이션
        val aniWidthDp: Dp by animateDpAsState(
            targetValue = if (isExpanded.value)
               LocalConfiguration.current.screenWidthDp.dp
            else
               LocalConfiguration.current.screenWidthDp.dp  * 0.7f
        )

        // 색상 애니메이션
        val backgroundColor by animateColorAsState(
            if (isExpanded.value) Color.LightGray else Color.White,
            animationSpec = tween(
                durationMillis = 1400,
                delayMillis = 200,
                easing = LinearEasing
            )
        )
        
        // 위치 애니메이션
        val offset by animateOffsetAsState(
            targetValue = if (isExpanded.value)
                Offset(0f, 0f) else Offset(0f, -30f),

            finishedListener = { off ->
                val foodlist = listOf("🍕", "🍔", "🌭")
                val indx = ( 0 .. foodlist.size -1 ).random()

                detail.value = foodlist[indx].repeat(100)
            }
        )


        Card(
            modifier = Modifier
                .width(aniWidthDp)
                .offset(offset.x.dp, offset.y.dp)// width 애니메이션
                .padding(15.dp)
                .clickable(onClick = {
                    isExpanded.value = !isExpanded.value
                }),
            elevation = 10.dp,
            backgroundColor = backgroundColor
        ) {
            var imgPath = if(isExpanded.value)
                "https://3.bp.blogspot.com/-VVp3WvJvl84/X0Vu6EjYqDI/AAAAAAAAPjU/ZOMKiUlgfg8ok8DY8Hc-ocOvGdB0z86AgCLcBGAsYHQ/s1600/jetpack%2Bcompose%2Bicon_RGB.png"
            else
                "https://avatars.githubusercontent.com/u/32689599?s=200&v=4"

            Column{
                Row{
                    Image(
                        painter = rememberImagePainter(
                            data = imgPath,
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
                    Text( "$title", Modifier.align(Alignment.CenterVertically),
                        color = Color.Black)
                }

                if(isExpanded.value){
                    Text( "${detail.value}",
                        modifier = Modifier.padding(10.dp),
                        style = TextStyle(fontSize = 20.sp))
                }

            }
        }
    }

}



