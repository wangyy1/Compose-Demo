package com.wyy.compose.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomDrawer
import androidx.compose.material.BottomDrawerValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch


class BottomDrawerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    BottomDrawerScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomDrawerScreen() {
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val selectedItem = remember {
        mutableStateOf(0)
    }

    val scope = rememberCoroutineScope()
    BottomDrawer(
        drawerState = drawerState,
        drawerContent = {
            BottomDrawerContent(selectedIndex = selectedItem.value, onItemClick = {
            selectedItem.value = it
            scope.launch {
                drawerState.close()
            }
        })
    }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Modal Drawer Example") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            content = { Icon(Icons.Default.Menu, contentDescription = null) }
                        )
                    }
                )
            },
        ) { contentPadding ->
            // Screen content
            Box(modifier = Modifier.padding(contentPadding)) { /* ... */ }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomDrawerContent(selectedIndex: Int, onItemClick: (Int) -> Unit) {
    val items = listOf("Item 1", "Item 2", "Item 3")
    Column {
        items.forEachIndexed { index, text ->
            ListItem(
                modifier = Modifier.clickable { onItemClick(index) },
                text = { Text(text) },
                icon = {
                    if (selectedIndex == index) {
                        Icon(Icons.Default.Check, contentDescription = null)
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomDrawerScreenPreview() {
    ComposeDemoTheme {
        BottomDrawerScreen()
    }
}