package com.wyy.compose.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch

/**
 * 一个简单的 Scaffold Demo
 */
class ScaffoldActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn() {
                items(100) {
                    Text(text = "Item $it", modifier = Modifier.padding(2.dp))
                }
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "首页")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            if (scaffoldState.drawerState.isClosed) {
                                scaffoldState.drawerState.open()
                            }
                        }
                    }) {
                        Icon(
                            Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Snackbar")
                }
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = { // 设置底部应用栏
//            BottomNavigation() {
//                BottomNavigationItem(
//                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
//                    label = { Text("主页") },
//                    selected = true,
//                    onClick = {
//
//                    },
//                )
//                BottomNavigationItem(
//                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
//                    label = { Text("主页") },
//                    selected = true,
//                    onClick = {
//
//                    },
//                )
//                BottomNavigationItem(
//                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
//                    label = { Text("主页") },
//                    selected = true,
//                    onClick = {
//
//                    },
//                )
//                BottomNavigationItem(
//                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
//                    label = { Text("主页") },
//                    selected = true,
//                    onClick = {
//
//                    },
//                )
//            }
            BottomAppBar(
                cutoutShape = CircleShape,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = { /* Handle favorite click */ }) {
                        Icon(Icons.Filled.Home, contentDescription = null)
                    }
                    IconButton(modifier = Modifier.weight(1f),
                        onClick = { /* Handle favorite click */ }) {
                        Icon(Icons.Filled.AccountBox, contentDescription = null)
                    }
                    IconButton(modifier = Modifier.weight(1f),
                        onClick = { /* Handle favorite click */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(modifier = Modifier.weight(1f),
                        onClick = { /* Handle settings click */ }) {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                }
            }
        },
        content = { contentPadding -> // 屏幕内容
            Column(Modifier.padding(contentPadding).fillMaxSize()) {
                Box() {
                    Icon(
                        Icons.Default.ShoppingCart, contentDescription = null,
                        modifier = Modifier.size(80.dp),
                    )
                    Badge(
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        // 在这里可以放置数字或文本
                        Text(text = "1")
                    }
                }
                BadgedBox(badge = {
                    Badge {
                        // 在这里可以放置数字或文本
                        Text(text = "1")
                    }
                }) {
                    Icon(
                        Icons.Default.ShoppingCart, contentDescription = null,
                        modifier = Modifier.size(80.dp),
                    )
                }
            }
        },
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ComposeDemoTheme {
        HomeScreen();
    }
}