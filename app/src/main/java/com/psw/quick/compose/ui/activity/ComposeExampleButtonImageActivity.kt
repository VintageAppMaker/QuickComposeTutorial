package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.R
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleButtonImageActivity : ComponentActivity() {
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
        val shapeLst = listOf(
            Pair("RoundedCornerShape", RoundedCornerShape(20.dp)),
            Pair("RectangleShape", RectangleShape),
            Pair("CircleShape", CircleShape),
            Pair("CutCornerShape", CutCornerShape(15.dp))
        )

        val scaleLst = listOf(
            Pair("Crop", ContentScale.Crop),
            Pair("FillBounds", ContentScale.FillBounds),
            Pair("Inside", ContentScale.Inside),
            Pair("Fit", ContentScale.Fit)
        )

        val scrState = rememberScrollState()

        Column (modifier = Modifier
            .padding(20.dp)
            .background(Color.White)
            .verticalScroll(scrState)){

            // Button Shape 예제.
            Text("Button Shape", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))

            shapeLst.forEach { item ->
                Button(onClick = {/* click handler */}, shape = item.second) {
                    Text(text = "${item.first}")
                }
                Spacer(Modifier.height(10.dp))
            }

            // Button Custom 예제.
            Text("Button Custom", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))

            Button(onClick = {/* click handler */},
                modifier = Modifier.border(width = 1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                contentPadding = PaddingValues(15.dp),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 15.dp,
                    pressedElevation = 20.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Box(modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                )

                Spacer(Modifier.width(2.dp))
                Text(text = "Press", color = Color.Black, style = TextStyle(fontSize = 15.sp))
                Spacer(Modifier.width(10.dp))
                Text("커스텀", color = Color.Gray, style = TextStyle(fontSize = 9.sp))
            }

            // Image 예제.
            Text("Image", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))

            Image(
                painter = painterResource(R.drawable.androidmarket),
                contentDescription = "image",
                Modifier.fillMaxWidth()
            )

            // Image 예제. scale
            Text("Image scale", color = Color.Black, fontSize = 18.sp)
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))

            scaleLst.forEach { item ->
                Text("${item.first}", color = Color.Black, style = TextStyle(fontSize = 10.sp))
                Image(
                    painter = painterResource(R.drawable.androidmarket),
                    contentDescription = "${item.first}",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape),
                    contentScale = item.second
                )
            }

        }
    }

}



