package com.wyy.compose.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

class Demo1Activity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var clicks by remember {
                mutableStateOf(0)
            }
//            Greeting(names = listOf<String>("1", "2"))
            ClickCounter(clicks = clicks) {
                clicks++
            }
        }
    }

}


/**
 * 动态内容
 */
@Composable
fun Greeting(names: List<String>): Unit {
    for (name in names) {
        Text(text = "Hello $name")
    }
}


@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick, enabled = false) {
        Text(text = "I've been clicked $clicks times")
    }
}

@Preview
@Composable
fun GreetingPreview(): Unit {
//    Greeting(names = listOf<String>("1", "2"))
    var clicks by remember {
        mutableStateOf(0)
    }
    ClickCounter(clicks = clicks) {
        clicks++
    }
}