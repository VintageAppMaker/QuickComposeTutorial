package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.R
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleScaffoldActivity : ComponentActivity() {
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

    val themeColor= Color.Red
    @Composable
    fun ExampleMain(
    ) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { appBarView()  },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = { floatingButtonView() },
            drawerContent = { drawerContentView() },
            content = { contentView() },
            bottomBar = { bottomBarView() }
        )
    }

    @Composable
    private fun contentView() {
        Box(
            Modifier
                .fillMaxSize()
                .background(themeColor)){
            Column(modifier = Modifier.align(Alignment.Center)){
                Text("🍟🥪🥨🥃",
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = Color.Black, fontSize = 50.sp))

                Text("enjoy",
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = Color.White, fontSize = 30.sp))
            }

        }
    }

    @Composable
    fun appBarView(){
        TopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically){
                Box(modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .background(Color.Yellow))
                Spacer(modifier = Modifier.width(10.dp))
                Text("side 메뉴선택", color = Color.White)
                Text("+", modifier = Modifier.weight(1.0f).padding(end = 15.dp), color = Color.White, textAlign = TextAlign.End)
            }
        },
        backgroundColor = themeColor)
    }

    @Composable
    fun floatingButtonView(){
        FloatingActionButton(onClick = {}, backgroundColor = themeColor){
            Image(
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null, // decorative element
                contentScale = ContentScale.FillBounds
            )
        }
    }

    @Composable
    fun drawerContentView(){
        Column(Modifier.background(Color.Yellow).fillMaxHeight()){
            Spacer(Modifier.height(30.dp))

            val menuList =listOf("🍦 아이스크림", "🍩 도넛", "🍰 케익" , "☕ 커피")
            menuList.forEach {
                Text(text = "${it}",
                    modifier = Modifier.fillMaxWidth().padding(start = 15.dp),
                    style = TextStyle(color = themeColor, fontSize = 20.sp),
                    textAlign = TextAlign.Left
                )
            }
        }
    }

    @Composable
    fun bottomBarView(){
        var selectedIndx = remember{ mutableStateOf(0)}
        BottomNavigation(backgroundColor = themeColor){
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "") },
                label = { Text("add") },
                selected = selectedIndx.value == 0,
                onClick = {
                    selectedIndx.value = 0
                }
            )

            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Filled.Call, contentDescription = "") },
                label = { Text("call") },
                selected = selectedIndx.value == 1,
                onClick = {
                    selectedIndx.value = 1
                }
            )
        }
    }

}





