package com.psw.quick.compose.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleSourceViewActivity : ComponentActivity() {
    var filename = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filename = intent.getStringExtra("file") ?: "example_17.html" // this 파일소스
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
        showSourceFile()
    }

    @Composable
    fun showSourceFile(){
        val PATH = "file:///android_asset/"

        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl("${PATH}${filename}")
            }
        }, update = {
            it.loadUrl("${PATH}${filename}")
        })
    }

}



