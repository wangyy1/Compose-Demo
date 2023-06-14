package com.wyy.compose.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class Demo2Activity : ComponentActivity() {


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

fun testFlow() = flow<String> {
    emit("test1")
    emit("test2")
    emit("test3")
}

data class City(val name: String, val country: String)

val CitySaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = {
            mapOf(nameKey to it.name, countryKey to it.country)
        },
        restore = {
            City(it[nameKey] as String, it[countryKey] as String)
        }
    )
}

val CitySaver2 = listSaver<City, Any>(
    save = { listOf(it.name, it.country) },
    restore = {City(it[0] as String, it[1] as String)}
)

@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember {
            mutableStateOf("")
        }

        val testFlow by testFlow().collectAsState(initial = "aa")
        Text(
            text = "Hello, $testFlow!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5,
        )


        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5,
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                            },
            label = { Text(text = "Name")}
        )
//        BasicTextField(value = "", onValueChange = {}, )
    }
}

@Preview
@Composable
fun HelloContentPreview(): Unit {
    HelloContent()
}