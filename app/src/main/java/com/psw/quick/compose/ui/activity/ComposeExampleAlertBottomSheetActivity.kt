package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme
import kotlinx.coroutines.launch

class ComposeExampleAlertBottomSheetActivity : ComponentActivity() {
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


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ExampleMain(
    ) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState()

        fun closeBottomSheet() {
            scope.launch {
                scaffoldState.bottomSheetState.collapse()
            }
        }

        fun openBottomSheet (){
            scope.launch {
                scaffoldState.bottomSheetState.expand() }
        }

        val BottomSheetShape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        )

        BottomSheetScaffold(sheetPeekHeight = 0.dp, scaffoldState = scaffoldState,
            sheetShape = BottomSheetShape,
            sheetContent = {
                showBottomSheetDialog(::closeBottomSheet){
                    dialogContent()
                }
            }) { paddingValues ->
            Box(Modifier.padding(paddingValues)){
                bodyContent(::openBottomSheet)
            }
        }
    }

    @Composable
    fun bodyContent(openSheet: () -> Unit) {
        val alertDialog = remember { mutableStateOf(false)  }

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { openSheet() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                Text(text = "Open BottomSheet", color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { alertDialog.value = true  },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                Text(text = "Open AlertDialog", color = Color.Gray)
            }

            if(alertDialog.value == true){
                showAlertDialog(
                    fnCancel = { b -> alertDialog.value = false},
                    fnOnConfirm = { b ->  alertDialog.value = false},
                    fnOnDismissRequest = { b ->  alertDialog.value = false})
            }
        }
    }

    @Composable
    fun dialogContent() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.DarkGray, shape = RectangleShape)){
            Text(text = "아직은 ExperimentalMaterialApi",
                Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),color = Color.White,
                fontSize = 15.sp)
        }
    }

    @Composable
    fun showBottomSheetDialog(
        onClosePressed: () -> Unit,
        content: @Composable() () -> Unit
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            content()

            IconButton(
                onClick = onClosePressed,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(29.dp)

            ) {
                Icon(Icons.Filled.Close, tint = Color.White, contentDescription = null)
            }
        }
    }

    @Composable
    fun showAlertDialog(fnOnDismissRequest: (Boolean) -> Unit,
                        fnOnConfirm:  (Boolean) -> Unit,
                        fnCancel:     (Boolean) -> Unit,
    ){
        AlertDialog(
            backgroundColor = Color.White.copy(alpha = 0.9f),
            onDismissRequest = {
                fnOnDismissRequest(false)
            },
            title = {
                Text(text = "Example", color = Color.Gray)
            },
            text = {
                Text("내용입니다.", color = Color.Gray)
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    onClick = {
                        fnOnConfirm(false)
                    }) {
                    Text("선택", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    onClick = {
                        fnCancel( false )

                    }) {
                    Text("취소", color = Color.Black)
                }
            })
    }

}





