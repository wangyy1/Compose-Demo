package com.wyy.compose.demo

import android.media.ImageReader
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wyy.compose.R
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch

class Demo6Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface(
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.picture),
            contentDescription = "图片",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "添加",
            tint = Color.White,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .align(alignment = Alignment.BottomEnd)
                .pointerInput(Unit) {
                    detectTapGestures {

                    }
                }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyScreenPreview() {
    ComposeDemoTheme {
        MyScreen()
    }
}