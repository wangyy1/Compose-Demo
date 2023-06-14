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
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
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
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.core.widget.NestedScrollView
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlin.math.roundToInt

/**
 * ConstraintLayout 学习
 */
class ConstraintLayoutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    ConstraintLayoutScreen()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutScreen() {
//    ConstraintLayoutContent()
//    DecoupledConstraintLayout()
//    GuidelineConstraintLayout()
    BarrierConstraintLayout()
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button, text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button")
        }

        Text(
            text = "Text",
            Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
            }
        )
    }
}

/**
 * Decoupled API
 */
@Composable
fun DecoupledConstraintLayout() {
    fun decoupledConstraints(margin: Dp): ConstraintSet {
        return ConstraintSet {
            val button = createRefFor("button")
            val text = createRefFor("text")


            constrain(button) {
                top.linkTo(parent.top, margin = margin)
            }
            constrain(text) {
                top.linkTo(button.bottom, margin)
            }
        }
    }


    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 16.dp)
        } else {
            decoupledConstraints(margin = 32.dp)
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("button")
            ) {
                Text(text = "Button")
            }
            Text(text = "Text", modifier = Modifier.layoutId("text"))
        }
    }
}

/**
 * 引导线
 */
@Composable
fun GuidelineConstraintLayout() {
    fun guidelineConstraints(): ConstraintSet {
        return ConstraintSet {
            var startGuideline = createGuidelineFromTop(300.dp)

            val button = createRefFor("button")
            val text = createRefFor("text")
//            val endBarrier = createEndBarrier(button, text, margin = 10.dp)
            constrain(button) {
                top.linkTo(startGuideline)
            }
            constrain(text) {
                top.linkTo(button.bottom)
            }


            var startGuideline2 = createGuidelineFromTop(0.5f)

            val button2 = createRefFor("button2")
            val text2 = createRefFor("text2")
//            val endBarrier = createEndBarrier(button, text, margin = 10.dp)
            constrain(button2) {
                start.linkTo(parent.start)
                top.linkTo(startGuideline2)
                end.linkTo(parent.end)
            }
            constrain(text2) {
                start.linkTo(parent.start)
                top.linkTo(button2.bottom)
                end.linkTo(parent.end)
            }
        }
    }


    BoxWithConstraints(modifier = Modifier.background(Color.Red)) {
        val constraints = guidelineConstraints()

        ConstraintLayout(constraints) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.background(Color.Yellow).layoutId("button")
            ) {
                Text(text = "Button")
            }
            Text(text = "Text", modifier = Modifier.layoutId("text"))


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.background(Color.Yellow).layoutId("button2")
            ) {
                Text(text = "Button2")
            }
            Text(text = "Text2", modifier = Modifier.layoutId("text2"))
        }
    }
}


/**
 * 屏障线
 */
@Composable
fun BarrierConstraintLayout() {
    fun barrierConstraints(): ConstraintSet {
        return ConstraintSet {
            val button = createRefFor("button")
            val text = createRefFor("text")
            val text2 = createRefFor("text2")
            val endBarrier = createEndBarrier(button, text, margin = 10.dp)
            constrain(button) {
                top.linkTo(parent.top)
            }
            constrain(text) {
                top.linkTo(button.bottom)
            }
            constrain(text2) {
                start.linkTo(endBarrier)
//                end.linkTo(parent.end)
            }
        }
    }


    BoxWithConstraints() {
        val constraints = barrierConstraints()

        ConstraintLayout(constraints, modifier = Modifier.size(400.dp).background(Color.Green)) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.background(Color.Yellow).layoutId("button")
            ) {
                Text(text = "Button")
            }
            Text(text = "Text Text Text Text Text Text ", modifier = Modifier.layoutId("text"))
            Text(text = "Text2 ", modifier = Modifier.layoutId("text2"))
        }

    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConstraintLayoutScreenPreview() {
    ComposeDemoTheme {
        ConstraintLayoutScreen()
    }
}