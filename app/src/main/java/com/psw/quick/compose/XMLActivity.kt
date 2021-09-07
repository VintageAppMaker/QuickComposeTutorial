package com.psw.quick.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy

class XMLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xmlactivity)

        findViewById<androidx.compose.ui.platform.ComposeView>(R.id.compose_view).apply {
            // Dispose the Composition when viewLifecycleOwner is destroyed
            setContent {
                // In Compose world
                MaterialTheme {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center ){
                        (0..10).forEach {
                            val colortable = listOf(Color.Red, Color.Blue, Color.DarkGray)
                            Text("$it", color = colortable [ it % colortable.size])
                        }
                    }
                }
            }
        }
    }
}