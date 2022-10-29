package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleShapeActivity : ComponentActivity() {
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
        val shapeList = listOf(CircleShape, RoundedCornerShape(10.dp), CutCornerShape(15.dp))

        Column (modifier = Modifier.padding(20.dp).background(Color.Red)){
            shapeList.forEach { shape ->
                Box(modifier = Modifier
                    .padding(10.dp)
                    .clip(shape) // clip 이후에 배경, 크기를 지정해야 함
                    .background(Color.White)
                    .width(100.dp)
                    .height(100.dp)
                )
            }
        }
    }

}



