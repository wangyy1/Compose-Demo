package com.wyy.compose.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyy.compose.ui.theme.ComposeDemoTheme

class Demo4Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Box() {
        BoxWithConstraints() {
            
        }
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .requiredSize(400.dp, 100.dp)
            .offset()
            .padding(24.dp)
    ) {
        Text(text = "Hello,")
        Text(text = name)
    }
//    val rememberScaffoldState = rememberScaffoldState()
//    LaunchedEffect(rememberScaffoldState.snackbarHostState) {}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        Greeting("Android")
    }
}