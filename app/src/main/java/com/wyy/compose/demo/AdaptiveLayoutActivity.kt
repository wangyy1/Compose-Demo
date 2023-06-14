package com.wyy.compose.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wyy.compose.R
import com.wyy.compose.ui.theme.ComposeDemoTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.lang.Integer.min

/**
 * 自适应布局
 */
class AdaptiveLayoutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    AdaptiveLayoutScreen()
                }
            }
        }
    }
}

enum class WindowSizeClass {Compact, Medium, Expanded}


@Composable
fun MyApp(windowSizeClass: WindowSizeClass) {
    // 在size类上执行逻辑来决定是否显示顶部应用栏。
    val showTopAppBar = windowSizeClass != WindowSizeClass.Compact


}


@Composable
fun AdaptiveLayoutScreen() {
    Box {
        BoxWithConstraints {
            if (maxWidth < 500.dp) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.picture),
                        modifier = Modifier.size(100.dp),
                        contentDescription = "图标"
                    )
                    Text(text = "Card Title", fontSize = 20.sp)
                }
            } else {
                Row {
                    Column {
                        Text(text = "Card Title", fontSize = 20.sp)
                        Text(text = "Extra description", fontSize = 18.sp)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.picture),
                        modifier = Modifier.size(100.dp),
                        contentDescription = "图标"
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdaptiveLayoutPreview() {
    ComposeDemoTheme {
        AdaptiveLayoutScreen();
    }
}