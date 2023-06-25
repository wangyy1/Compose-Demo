package com.wyy.compose.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.ShoppingCart
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wyy.compose.R
import com.wyy.compose.ui.theme.ComposeDemoTheme

/**
 * 底部导航
 */
class BottomNavigationDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface {
                    BottomNavigationDemoScreen()
                }
            }
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object FriendsList : Screen("friendslist", R.string.friends_list)
}

val items = listOf(
    Screen.Profile, Screen.FriendsList
)

@Composable
fun BottomNavigationDemoScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    },
                    label = {
                        Text(text = stringResource(id = screen.resourceId))
                    },
                )
            }
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Profile.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Profile.route) {
                ProfileScreenBottom()
            }
            composable(Screen.FriendsList.route) {
                FriendsListScreenBottom()
            }
        }
    }
}

@Composable
fun ProfileScreenBottom() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Column {
            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)
            Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = null)
            Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null)
            Icon(imageVector = Icons.TwoTone.ShoppingCart, contentDescription = null)
            Icon(imageVector = Icons.Sharp.ShoppingCart, contentDescription = null)
        }
    }
}

@Composable
fun FriendsListScreenBottom() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        println("")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavigationDemoPreview() {
    ComposeDemoTheme {
        BottomNavigationDemoScreen()
    }
}