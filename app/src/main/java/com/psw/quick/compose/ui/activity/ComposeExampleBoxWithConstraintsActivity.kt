package com.psw.quick.compose.ui.activity

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

class ComposeExampleBoxWithConstraintsActivity : ComponentActivity() {
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
        Column() {
            Box(modifier = Modifier.fillMaxWidth().height(100.dp)){
                buildDynamicLayout()
            }

            Box(modifier = Modifier.fillMaxWidth().height(300.dp)){
                buildDynamicLayout()
            }

        }

    }

    @Composable
    private fun buildDynamicLayout() {
        BoxWithConstraints {
            if (maxHeight < 150.dp) {
                buildRowLayout()
            } else {
                buildConstraintLayout()
            }
        }
    }

    @Composable
    fun buildConstraintLayout() {
        fun setConstraints(): ConstraintSet {
            return ConstraintSet {
                val box1 = createRefFor("box1")
                val box2 = createRefFor("box2")

                constrain(box1) {
                    top.linkTo(parent.top, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                    // 늘린크기만큼 width
                    width = Dimension.fillToConstraints
                }

                constrain(box2) {
                    top.linkTo(box1.bottom, margin = 10.dp)
                }

            }
        }

        ConstraintLayout(setConstraints()) {
            Box(
                Modifier
                    .background(Color.Red)
                    .fillMaxWidth()
                    .height(100.dp)
                    .layoutId("box1"))

            Box(
                Modifier
                    .background(Color.Green)
                    .width(100.dp)
                    .height(100.dp)
                    .layoutId("box2"))
        }
    }

    @Composable
    fun buildRowLayout() {
        Row{
            Box(
                Modifier
                    .weight(1.0f)
                    .height(100.dp)
                    .background(Color.Red)
            )

            Box(
                Modifier
                    .background(Color.Green)
                    .width(100.dp)
                    .height(100.dp)
            )
        }
    }

}



