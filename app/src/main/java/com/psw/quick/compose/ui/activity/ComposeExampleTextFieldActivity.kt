package com.psw.quick.compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.psw.quick.compose.R
import com.psw.quick.compose.ui.theme.QuickComposeTutorialTheme

class ComposeExampleTextFieldActivity : ComponentActivity() {
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
        val scrState = rememberScrollState()
        var count = remember{ mutableStateOf(0)}

        Column (modifier = Modifier
            .padding(20.dp)
            .background(Color.White)
            .verticalScroll(scrState)){

            // TextField 기본
            ExampleDesc("Simple"){
                basicTextField()
            }

            // 밑줄지우기
            ExampleDesc("remove Underline"){
                removeUnderlineTextField()
            }

            // 커스텀
            ExampleDesc("rounded TextFiled"){
                roundedTextFiled()
            }

            // OutlinedTextField
            ExampleDesc("OutlinedTextField"){
                labelField()
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

    @Composable
    fun basicTextField(){
        var text by remember { mutableStateOf(TextFieldValue(""))}
        TextField(value = text,
            textStyle = TextStyle(color = Color.Black),
            onValueChange = { str ->  text = str})
    }

    @Composable
    fun removeUnderlineTextField(){
        var text by remember { mutableStateOf(TextFieldValue(""))}
        TextField(value = text,
            modifier = Modifier,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            onValueChange = { str ->  text = str},
            textStyle = TextStyle(textDecoration = TextDecoration.None)
        )
    }

    @Composable
    fun roundedTextFiled(){
        var text by remember { mutableStateOf(TextFieldValue(""))}
        TextField(
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                text = it
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon", tint=Color.Red) },
            modifier = Modifier.border(
                BorderStroke(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(listOf(Color.Black, Color.Gray))
                ),
                shape = RoundedCornerShape(25)
            ),
            placeholder = { Text(text = "Enter your e-mail", color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
    @Composable
    fun labelField() {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = text,
            label = { Text(text = "email adress", color = Color.Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                text = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.Black,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black
            )
        )
    }
}



