package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleCheckBoxDropDownMenuActivity : ComponentActivity() {
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
        Column(modifier = Modifier
            .padding(20.dp)
            .background(Color.White)) {
            ExampleDesc(title = "checkbox"){
                checkBoxExample()
            }

            ExampleDesc(title = "Dropdown"){
                DropdownExample()
            }
        }
    }

    @Composable
    fun checkBoxExample(){
        var isChecked  by remember { mutableStateOf(false)}
        var isChecked2 by remember { mutableStateOf(false)}

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Gray,
                    checkedColor = Color.Black,
                    checkmarkColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomCheckBox(isCheck = isChecked2, { b -> isChecked2 = !isChecked2})


        }
    }

    @Composable
    fun CustomCheckBox(isCheck : Boolean = false, fnOnCheckChange : (Boolean) -> Unit ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {

            Box(modifier = Modifier
                .size(20.dp)
                .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                .clip(CircleShape)
                .background(
                    if (isCheck) Color.Black else Color.LightGray
                )
                .clickable {
                    fnOnCheckChange(isCheck)
                },

                contentAlignment = Alignment.Center
            ){
                if(isCheck)
                    Icon(Icons.Default.Check, contentDescription = "", tint = Color.White)
            }

            Text("${isCheck }", color = Color.Black)
        }
    }
    @Composable
    fun DropdownExample() {
        var expanded by remember { mutableStateOf(false) }
        val items = listOf("🍔 17$", "🍕 10$", "🧀 13$", "🍰 13$")
        var selected by remember { mutableStateOf(0) }
        Box(modifier = Modifier
            .clickable(onClick = { expanded = true })
            .border(width = 1.dp, color = Color.LightGray)
            .wrapContentSize(Alignment.Center)) {
            Text(items[selected],modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .background(
                    Color.White
                ), textAlign = TextAlign.Center, color = Color.Black)

            Icon(Icons.Default.ArrowDropDown,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 15.dp))

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(
                        Color.DarkGray
                    )
            ) {
                items.forEachIndexed { index, title ->
                    DropdownMenuItem(onClick = {
                        selected = index
                        expanded = false
                    }) {
                        Text(text = title, color= Color.White)
                    }
                }
            }
        }
    }

    @Composable
    fun ExampleDesc(title : String, content: @Composable () -> Unit){
        Text("$title ", color = Color.Black, fontSize = 18.sp)
        Divider(color = Color.Gray, thickness = 0.5.dp)
        Spacer(Modifier.height(10.dp))

        content()
    }


}



