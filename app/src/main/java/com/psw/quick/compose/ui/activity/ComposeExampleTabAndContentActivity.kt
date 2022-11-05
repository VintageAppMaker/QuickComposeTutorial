package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import com.google.accompanist.pager.calculateCurrentOffsetForPage

class ComposeExampleTabAndContentActivity : ComponentActivity() {
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
    fun pageOne(){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Yellow)){
            Text("😀", modifier = Modifier.align(Alignment.TopStart))
            Text("😀", modifier = Modifier.align(Alignment.TopCenter))
            Text("😀", modifier = Modifier.align(Alignment.TopEnd))
            Text("🐯", modifier = Modifier.align(Alignment.Center), fontSize = 90.sp)
            Text("😀", modifier = Modifier.align(Alignment.BottomStart))
            Text("😀", modifier = Modifier.align(Alignment.BottomCenter))
            Text("😀", modifier = Modifier.align(Alignment.BottomEnd))
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun pageTwo(){
        val pagerState = rememberPagerState()
        Column{
            HorizontalPager(
                count = 14,
                contentPadding = PaddingValues(horizontal = 32.dp),
                state = pagerState
            ) { page ->
                Card(
                    Modifier
                        .padding(16.dp)
                        .wrapContentSize(),
                    elevation = 3.dp,
                    contentColor = Color.Green,
                    backgroundColor = Color.DarkGray
                ) {
                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)){
                        Text("$page", modifier = Modifier.align(Alignment.Center) )
                    }
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                indicatorShape = CircleShape,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = Color.Black,
                inactiveColor = Color.LightGray
            )
        }
    }

    // 탭(타이틀 및 컨텐츠)
    data class TabsItem(val title: String, val content:  @Composable () -> Unit)

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun ExampleMain(
    ) {
        // scroll을 불가능하게 해야 비율대로 적용된다.
        Column (modifier = Modifier
            .fillMaxSize()){
            val tabs = listOf(
                TabsItem("page 1", {pageOne()}),
                TabsItem("page 2", {pageTwo()}),
            )

            val pagerState = rememberPagerState()
            Column {
                Tabs(tabs = tabs, pagerState = pagerState)
                Box(modifier = Modifier
                    .fillMaxWidth()){
                    TabsContent(tabs = tabs, pagerState = pagerState)
                }
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun Tabs(tabs: List<TabsItem>, pagerState: PagerState) {
        val scope = rememberCoroutineScope()
        TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.background
            ){

            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = {
                        Text(
                            text = tab.title,
                            style = TextStyle(fontSize =30.sp, color = Color.Black)
                        )},
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun TabsContent(tabs: List<TabsItem>, pagerState: PagerState) {
        HorizontalPager(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), state = pagerState, count = tabs.size) { page ->
            tabs[page].content()
        }
    }

}




