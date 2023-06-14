package com.wyy.compose.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch

class Demo5Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.pointerInput(),
//                    color = MaterialTheme.colors.background
                ) {
                    MoviesScreen()
                }
            }
        }
    }
}

@Composable
fun MoviesScreen(scaffoldState: ScaffoldState = rememberScaffoldState()) {
    val scope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState) {
        Column() {
            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Something happened!")
                }
            }) {
                Text(text = "Press me")
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MoviesScreen() {
    ComposeDemoTheme {
        MoviesScreen()
    }
}