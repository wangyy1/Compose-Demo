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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MovableContentState
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
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.widget.NestedScrollView
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlin.math.roundToInt

/**
 * 自定义布局
 */
class Demo8Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    Demo8Screen()
                }
            }
        }
    }
}

@Composable
fun Demo8Screen() {
    Text(
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            check(placeable[FirstBaseline] != AlignmentLine.Unspecified)

            val firstBaseline = placeable[FirstBaseline]

            // Height of the composable with padding - first baseline
            val placeableY = 24.dp.roundToPx() - firstBaseline
            val height = placeable.height + placeableY
            layout(placeable.width, height) {
                // Where the composable gets placed
                placeable.placeRelative(0, placeableY)
            }
        },
        text = "Hi there!"
    )
}

@Composable
fun MyBasicColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
    ) {measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var yPosition = 0

            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)

                yPosition += placeable.height
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Demo8ScreenPreview() {
    ComposeDemoTheme {
//        Demo8Screen()
        MyBasicColumn(
            modifier = Modifier.padding(8.dp)
        ) {
            Text("MyBasicColumn")
            Text("places items")
            Text("vertically.")
            Text("We've done it by hand!")
        }
    }
}