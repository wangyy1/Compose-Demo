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
import androidx.compose.material.BottomSheetScaffold
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
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch


class BottomSheetScaffoldActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    BottomSheetScaffoldScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffoldScreen() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Text(text = "item 0")
            Text(text = "item 2")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
            Text(text = "item 3")
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Expand or collapse sheet") },
                onClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.apply {
                            if (isCollapsed) expand() else collapse()
                        }
                    }
                }
            )
        }
    ) {
        Column {
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
            Text(text = "itemitemitemitemitemitemitem ")
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomSheetScaffoldScreenPreview() {
    ComposeDemoTheme {
        BottomSheetScaffoldScreen()
    }
}