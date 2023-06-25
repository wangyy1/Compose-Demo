@file:OptIn(ExperimentalTextApi::class)

package com.wyy.compose.anima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyy.compose.ui.theme.ComposeDemoTheme

/**
 * AnimatedVisibility 动画
 */
class AnimatedVisibilityActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    AnimatedVisibilityScreen()
                }
            }
        }
    }
}

@Composable
fun AnimatedVisibilityScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), reverseScrolling = true)
    ) {
        AnimatedVisibilityDemo()
        AnimatedVisibilityDemo2()
        AnimatedVisibilityDemo3()
        AnimatedVisibilityDemo4()
        AnimatedVisibilityDemo5()
    }
}


/**
 * AnimatedVisibility 可组合项可为内容的出现和消失添加动画效果。
 */
@Composable
fun AnimatedVisibilityDemo() {
    var editable by remember {
        mutableStateOf(true)
    }
    AnimatedVisibility(visible = editable) {
        Text(text = "Edit")
    }
}

/**
 * 默认情况下，内容以淡入和扩大的方式出现，以淡出和缩小的方式消失。您可以通过指定 EnterTransition 和 ExitTransition 来自定义这种过渡效果。
 */
@Composable
fun AnimatedVisibilityDemo2() {
    var editable by remember {
        mutableStateOf(true)
    }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = editable,
        enter = slideInVertically {
            with(density) {
                -40.dp.roundToPx()
            }
        } + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Text(
            text = "Hello",
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Red)
        )
    }
}

/**
 * AnimatedVisibility 还提供了接受 MutableTransitionState 的变体。这样，只要将 AnimatedVisibility 添加到组合树中，您就可以立即触发动画。该属性还有助于观察动画状态。
 */
@Composable
fun AnimatedVisibilityDemo3() {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    Column() {
        AnimatedVisibility(visibleState = state) {
            Text(text = "Hello, World!")
        }
        Text(
            text = when {
                state.isIdle && state.currentState -> "Visible"
                !state.isIdle && state.currentState -> "Disappearing"
                state.isIdle && !state.currentState -> "Invisible"
                else -> "Appearing"
            }
        )
    }
}


/**
 * AnimatedVisibility 中的内容（直接或间接子项）可以使用 animateEnterExit 修饰符为每个子项指定不同的动画行为。其中每个子项的视觉效果均由 AnimatedVisibility 可组合项中指定的动画与子项自己的进入和退出动画构成。
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityDemo4() {
    var visible by remember {
        mutableStateOf(false)
    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .animateEnterExit(
                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    )
                    .sizeIn(minWidth = 256.dp, minHeight = 64.dp * 2)
                    .background(Color.Red)
            ) {

            }
        }
    }
    Button(onClick = { visible = !visible }) {
        Text(text = "显示")
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityDemo5() {
    var visible by remember {
        mutableStateOf(true)
    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        val background by transition.animateColor(label = "ColorAnimation2") { state ->
            if (state == EnterExitState.Visible) Color.Blue else Color.Gray
        }
        Box(modifier = Modifier.size(128.dp).background(background))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimatedVisibilityScreenPreview() {
    ComposeDemoTheme {
        AnimatedVisibilityScreen()
    }
}