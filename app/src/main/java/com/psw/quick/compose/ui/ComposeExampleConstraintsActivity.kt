package com.psw.quick.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleConstraintsActivity : ComponentActivity() {
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
        buildConstraintLayout()
    }

    @Composable
    fun buildConstraintLayout() {
        ConstraintLayout(setConstraints()) {
            Box(Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(10.dp)
                .layoutId("box1"))

            Box(Modifier
                .background(Color.Green)
                .width(100.dp)
                .height(100.dp)
                .layoutId("box2"))

            Box(Modifier
                .background(Color.Yellow)
                .width(50.dp)
                .height(50.dp)
                .layoutId("box3"))

        }
    }

    private fun setConstraints(): ConstraintSet {
        return ConstraintSet {
            val box1 = createRefFor("box1")
            val box2 = createRefFor("box2")
            val box3 = createRefFor("box3")

            constrain(box1) {
                top.linkTo(parent.top, margin = 30.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)

                // 늘린크기만큼 width
                width = Dimension.fillToConstraints
            }

            constrain(box2) {
                top.linkTo(box1.bottom, margin = 10.dp)
            }

            constrain(box3) {
                bottom.linkTo(parent.bottom, margin = 30.dp)
                start.linkTo(parent.start, margin = 30.dp)
            }
        }
    }
}



