@file:OptIn(ExperimentalTextApi::class, ExperimentalAnimationApi::class)

package com.wyy.compose.anima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateSize
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.size.Size
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlin.math.exp

/**
 * AnimatedContent、animateContentSize、Crossfade、updateTransition 动画
 */
class AnimatedContentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    AnimatedContentScreen()
                }
            }
        }
    }
}

@Composable
fun AnimatedContentScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), reverseScrolling = true)
    ) {
        AnimatedContentDemo1()
        AnimatedContentDemo2()
        AnimatedContentDemo3()
        AnimateContentSizeDemo4()
        CrossfadeDemo5()
        UpdateTransitionDemo7()
        RememberInfiniteTransitionDemo()
    }
}

/**
 * AnimatedContent 可组合项会在内容根据目标状态发生变化时，为内容添加动画效果。
 */
@Composable
fun AnimatedContentDemo1() {
    Row() {
        var count by remember {
            mutableStateOf(0)
        }
        Button(onClick = { count++ }) {
            Text(text = "Add")
        }
        AnimatedContent(targetState = count) { targetCount ->
            Text(text = "Count: $targetCount")
        }

    }
}

@Composable
fun AnimatedContentDemo2() {
    Row() {
        var count by remember {
            mutableStateOf(0)
        }
        Button(onClick = { count++ }) {
            Text(text = "Add")
        }
        Box(
            modifier = Modifier
                .size(60.dp)
                .border(2.dp, Color.Black),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically { height ->
                            height
                        } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(SizeTransform(clip = false))
                },

                ) {
                Text(
                    text = "$it",
                )
            }

        }
        Button(onClick = { count-- }) {
            Text(text = "Delete")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnimatedContentDemo3() {
    var expanded by remember {
        mutableStateOf(false)
    }
    Surface(
        color = MaterialTheme.colors.primary,
        onClick = {
            expanded = !expanded
        }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Text(text = "士大夫大师傅士大夫大师傅第三方手动阀手动阀士大夫第三方士大夫大师傅士大夫大师傅第三方手动阀手动阀士大夫第三方士大夫大师傅士大夫大师傅第三方手动阀手动阀士大夫第三方士大夫大师傅士大夫大师傅第三方手动阀手动阀士大夫第三方士大夫大师傅士大夫大师傅第三方手动阀手动阀士大夫第三方")
            } else {
                Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Phone, contentDescription = "")
                }
            }
        }
    }
}

/**
 * animateContentSize 修饰符可为大小变化添加动画效果。
 */
@Composable
fun AnimateContentSizeDemo4() {
    var message by remember {
        mutableStateOf("Hello")
    }
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .animateContentSize()
    ) {
        Button(onClick = { message += " Hello" }) {
            Text(text = message)
        }
    }
}

/**
 * Crossfade 可使用淡入淡出动画在两个布局之间添加动画效果。通过切换传递给 current 参数的值，可以使用淡入淡出动画来切换内容。
 */
@Composable
fun CrossfadeDemo5() {
    var currentPage by remember {
        mutableStateOf("A")
    }
    Crossfade(targetState = currentPage) { screen ->
        when (screen) {
            "A" -> Text(text = "Page A")
            "B" -> Text(text = "Page B")
        }
    }
}

enum class BoxState {
    Collapsed,
    Expanded
}

@Composable
fun UpdateTransitionDemo6() {

    var currentState by remember {
        mutableStateOf(BoxState.Collapsed)
    }

    val transition = updateTransition(targetState = currentState, label = "")

    val rect by transition.animateSize(label = "") { state ->
        when (state) {
            BoxState.Collapsed -> androidx.compose.ui.geometry.Size(
                100f,
                100f
            )

            BoxState.Expanded -> androidx.compose.ui.geometry.Size(100f, 200f)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UpdateTransitionDemo7() {
    var selected by remember {
        mutableStateOf(false)
    }
    val transition = updateTransition(targetState = selected, label = "")
    val borderColor by transition.animateColor(label = "",) { isSelected ->
        if (isSelected) Color.Magenta else Color.White
    }
    val elevation by transition.animateDp(label = "") { isSelected ->
        if (isSelected) 10.dp else 2.dp
    }

    Surface(
        onClick = {
            selected = !selected
        },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, borderColor),
        elevation = elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Hello, world!")
            transition.AnimatedVisibility(
                visible = { targetSelected -> targetSelected },
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Text(text = "It is fine today.")
            }
            transition.AnimatedContent {targetState ->
                if (targetState) {
                    Text(text = "Selected")
                } else {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                }
            }
        }
    }

}

@Composable
fun RememberInfiniteTransitionDemo() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier.size(100.dp).background(color = color)
    ) {

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimatedContentPreview() {
    ComposeDemoTheme {
        AnimatedContentScreen()
    }
}