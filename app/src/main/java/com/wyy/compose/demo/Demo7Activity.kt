package com.wyy.compose.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.widget.NestedScrollView
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlin.math.roundToInt

class Demo7Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    Demo7Screen()
                }
            }
        }
    }
}

@Composable
fun Demo7Screen() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(10) {
            Text(text = "Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}

/**
 * 滚动
 * https://developer.android.google.cn/jetpack/compose/gestures?hl=zh-cn
 */
@Composable
fun ScrollableSample() {
    var offset by remember {
        mutableStateOf(0f)
    }
    Box(
        Modifier
            .size(150.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            )
    ) {
        Text(text = offset.toString())
    }
}

/**
 * 拖动
 * https://developer.android.google.cn/jetpack/compose/gestures?hl=zh-cn
 */
@Composable
fun DraggableSample() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember {
            mutableStateOf(0f)
        }
        var offsetY by remember {
            mutableStateOf(0f)
        }
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
                }
                .background(Color.Blue)
                .size(50.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
    }
}

/**
 * 滑动
 * https://developer.android.google.cn/jetpack/compose/gestures?hl=zh-cn
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) {
        squareSize.toPx()
    }
    val anchors = mapOf(0f to 0, sizePx to 1)

    Box(
        modifier = Modifier
            .width(width * 3)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { from, to ->
                    Log.d("----", "$from--$to")
                    FractionalThreshold(0.3f)
                },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        ) {

        }
    }
}

/**
 * 多点触控：平移、缩放、旋转
 * https://developer.android.google.cn/jetpack/compose/gestures?hl=zh-cn
 */
@Composable
fun TransformableSample() {
    var scale by remember {
        mutableStateOf(1f)
    }
    var rotation by remember {
        mutableStateOf(0f)
    }
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += panChange
    }
    Box(
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation,
                translationX = offset.x,
                translationY = offset.y
            )
            .transformable(state = state)
            .background(Color.Blue)
            .fillMaxSize()
    ) {

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Demo7ScreenPreview() {
    ComposeDemoTheme {
//        Demo7Screen()
//        ScrollableSample()
//        DraggableSample()
//        SwipeableSample()
        TransformableSample()
    }
}