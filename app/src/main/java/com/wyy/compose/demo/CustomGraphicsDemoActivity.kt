@file:OptIn(ExperimentalTextApi::class)

package com.wyy.compose.demo

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import com.wyy.compose.R
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * 自定义图形
 */
class CustomGraphicsDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    CustomGraphicsDemoScreen()
                }
            }
        }
    }
}

@Composable
fun CustomGraphicsDemoScreen() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState, reverseScrolling = true)
                .fillMaxSize()
        ) {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo1(): Unit { // 绘制一个方形
    Canvas(
        modifier = Modifier
            .size(200.dp, 300.dp)
            .background(Color.Magenta)
    ) {
        val canvasQuadrantSize = size / 2F
        drawRect(
            color = Color.Magenta,
            size = canvasQuadrantSize
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo2(): Unit { // 绘制一个对角线
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawLine(
                start = Offset(x = canvasWidth, y = 0f),
                end = Offset(x = 0f, y = canvasHeight),
                color = Color.Blue
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo3(): Unit { // 缩放
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            scale(scaleX = 10f, scaleY = 15f) {
                drawCircle(Color.Blue, radius = 20.dp.toPx())
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo4(): Unit { // 平移
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            translate(left = 100f, top = -300f) {
                drawCircle(Color.Blue, radius = 200.dp.toPx())
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo5() { // 旋转
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            rotate(degrees = 45f) {
                drawRect(
                    color = Color.Gray,
                    topLeft = Offset(x = size.width / 3f, y = size.height / 3f),
                    size = size / 3f
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo6() { // 边衬取
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            val canvasQuadrantSize = size / 2F
            inset(horizontal = 50f, vertical = 30f) {
                drawRect(color = Color.Green, size = canvasQuadrantSize)
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo7() { // 多个转换
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            withTransform({
                translate(left = size.width / 5F)
                rotate(degrees = 45F)
            }) {
                drawRect(
                    color = Color.Gray,
                    topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                    size = size / 3F
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo8() { // 绘制文本
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            drawText(textMeasurer, "Hello")
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo9() { // 测量文本
    val textMeasurer = rememberTextMeasurer()
    val longTextSample =
        "绘制文本的方式与其他绘制命令略有不同。通常情况下，您会在绘制命令中指定绘制形状/图片所需的尺寸（宽度和高度）。在绘制文本时，您可以通过几个参数来控制所呈现文本的大小，例如字体大小、字体、连字和字母间距。\n" +
                "\n" +
                "在 Compose 中，您可以使用 TextMeasurer 来获取根据上述因素测量的文本大小。如果您想在文本后面绘制背景，可以通过测量的信息获知文本所占区域的大小："
    Spacer(
        modifier = Modifier
            .drawWithCache {
                val measuredText = textMeasurer.measure(
                    AnnotatedString(longTextSample),
                    constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                    style = TextStyle(fontSize = 18.sp)
                )
                onDrawBehind {
                    drawRect(Color.Yellow, size = measuredText.size.toSize())
                    drawText(measuredText)
                }
            }
            .fillMaxSize()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo10() { // 测量文本
    val textMeasurer = rememberTextMeasurer()
    val longTextSample =
        "绘制文本的方式与其他绘制命令略有不同。通常情况下，您会在绘制命令中指定绘制形状/图片所需的尺寸（宽度和高度）。在绘制文本时，您可以通过几个参数来控制所呈现文本的大小，例如字体大小、字体、连字和字母间距。\n" +
                "" +
                "在 Compose 中，您可以使用 TextMeasurer 来获取根据上述因素测量的文本大小。如果您想在文本后面绘制背景，可以通过测量的信息获知文本所占区域的大小："
    Spacer(
        modifier = Modifier
            .drawWithCache {
                val measuredText = textMeasurer.measure(
                    AnnotatedString(longTextSample),
                    constraints = Constraints.fixed(
                        width = (size.width / 3f).toInt(),
                        height = (size.height / 3f).toInt()
                    ),
                    style = TextStyle(fontSize = 18.sp),
                    overflow = TextOverflow.Ellipsis
                )
                onDrawBehind {
                    drawRect(Color.Yellow, size = measuredText.size.toSize())
                    drawText(measuredText)
                }
            }
            .fillMaxSize()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo11() { // 绘制图片
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            drawImage(dogImage)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo12() { // 绘制基本形状,镜像渐变
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), reverseScrolling = true)
    ) {
        Canvas( // 圆
            modifier = Modifier.size(200.dp, 200.dp), onDraw = {
                drawCircle(Color(0xFFBA68C8))
            }
        )
        Canvas( // 矩形
            modifier = Modifier.size(200.dp, 200.dp), onDraw = {
                drawRect(
                    color = Color.Magenta,
                    size = Size(200.dp.toPx(), 100.dp.toPx())
                )
            }
        )
        Canvas( // 圆角矩形
            modifier = Modifier.size(200.dp, 200.dp), onDraw = {
                drawRoundRect(
                    color = Color.Magenta,
                    size = Size(200.dp.toPx(), 100.dp.toPx()),
                    cornerRadius = CornerRadius(10.0f)
                )
            }
        )
        Canvas( // 划线
            modifier = Modifier.size(200.dp, 200.dp), onDraw = {
                drawLine(
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = 200.dp.toPx(), y = 200.dp.toPx()),
                    color = Color.Blue
                )
            }
        )
        Canvas( // 椭圆形
            modifier = Modifier.size(200.dp, 200.dp), onDraw = {
                drawOval(
                    color = Color.Magenta,
                    size = Size(200.dp.toPx(), 100.dp.toPx()),
                )
            }
        )
        Canvas( // 弧形
            modifier = Modifier.size(200.dp, 200.dp), onDraw = {
                drawArc(
                    color = Color.Magenta,
                    startAngle = 0.0f,
                    sweepAngle = 270.0f,
                    useCenter = true,
                    size = Size(200.dp.toPx(), 100.dp.toPx()),
                )
            }
        )
        Canvas( // 画点
            modifier = Modifier.size(200.dp, 200.dp), onDraw = {
                drawPoints(
                    points = arrayListOf(
                        Offset(10.0.dp.toPx(), 10.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 20.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 30.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 40.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 50.0.dp.toPx()),

                        Offset(20.0.dp.toPx(), 50.0.dp.toPx()),
                        Offset(30.0.dp.toPx(), 50.0.dp.toPx()),
                        Offset(40.0.dp.toPx(), 50.0.dp.toPx()),
                        Offset(50.0.dp.toPx(), 50.0.dp.toPx()),

                        Offset(50.0.dp.toPx(), 40.0.dp.toPx()),
                        Offset(50.0.dp.toPx(), 30.0.dp.toPx()),
                        Offset(50.0.dp.toPx(), 20.0.dp.toPx()),
                        Offset(50.0.dp.toPx(), 10.0.dp.toPx()),

                        Offset(40.0.dp.toPx(), 10.0.dp.toPx()),
                        Offset(30.0.dp.toPx(), 10.0.dp.toPx()),
                        Offset(20.0.dp.toPx(), 10.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 10.0.dp.toPx()),
                    ),
                    pointMode = PointMode.Points,
                    color = Color.Magenta,
                    strokeWidth = 10.0.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
        )
        Canvas(
            modifier = Modifier.size(200.dp, 200.dp), onDraw = {
                drawPoints(
                    points = arrayListOf(
                        Offset(10.0.dp.toPx(), 10.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 20.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 30.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 40.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 50.0.dp.toPx()),

                        Offset(20.0.dp.toPx(), 50.0.dp.toPx()),
                        Offset(30.0.dp.toPx(), 50.0.dp.toPx()),
                        Offset(40.0.dp.toPx(), 50.0.dp.toPx()),
                        Offset(50.0.dp.toPx(), 50.0.dp.toPx()),

                        Offset(50.0.dp.toPx(), 40.0.dp.toPx()),
                        Offset(50.0.dp.toPx(), 30.0.dp.toPx()),
                        Offset(50.0.dp.toPx(), 20.0.dp.toPx()),
                        Offset(50.0.dp.toPx(), 10.0.dp.toPx()),

                        Offset(40.0.dp.toPx(), 10.0.dp.toPx()),
                        Offset(30.0.dp.toPx(), 10.0.dp.toPx()),
                        Offset(20.0.dp.toPx(), 10.0.dp.toPx()),
                        Offset(10.0.dp.toPx(), 10.0.dp.toPx()),
                    ),
                    pointMode = PointMode.Points,
                    color = Color.Magenta,
                    strokeWidth = 10.0.dp.toPx(),
                    cap = StrokeCap.Square
                )
            }
        )

        Spacer(
            modifier = Modifier
                .size(200.dp, 200.dp)
                .drawWithCache {
                    val path = Path()
                    path.moveTo(0f, 0f)
                    path.lineTo(size.width / 2f, size.height / 2f)
                    path.lineTo(size.width, 0f)
                    path.close()
                    onDrawBehind {
                        drawPath(path, Color.Magenta, style = Stroke(width = 10f))
                    }
                }
        )

        val drawable = ShapeDrawable(OvalShape())
        Spacer(
            modifier = Modifier
                .size(200.dp, 200.dp)
                .drawWithContent {
                    drawIntoCanvas { canvas: Canvas ->
                        drawable.setBounds(0, 0, size.width.toInt(), size.height.toInt())
                        drawable.draw(canvas.nativeCanvas)
                    }
                }
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo13() { // 实现类似手电筒效果
    var pointerOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput("dragging") {
                detectDragGestures { change, dragAmount ->
                    pointerOffset += dragAmount
                }
            }
            .onSizeChanged {
                pointerOffset = Offset(it.width / 2f, it.height / 2f)
            }
            .drawWithContent {
                drawContent()
                drawRect(
                    Brush.radialGradient(
                        listOf(Color.Transparent, Color.Black),
                        center = pointerOffset,
                        radius = 100.dp.toPx()
                    )
                )
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo14() { // drawBehind 在可组合项后面绘制
    Text(
        text = "Hello Compose!",
        modifier = Modifier
            .padding(10.dp)
            .drawBehind {
                drawRoundRect(
                    Color(0xFFBBAAEE),
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }
            .padding(10.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo15() { // drawWithCache 可以缓存绘制对象，绘制渐变色
    Text(
        text = "Hello Compose!",
        modifier = Modifier
            .padding(10.dp)
            .drawWithCache {
                val brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF9E82F0),
                        Color(0xFF42A5F5)
                    )
                )
                onDrawBehind {
                    drawRoundRect(
                        brush = brush,
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }
            }
            .padding(10.dp)
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo16() { // drawWithCache 可以缓存绘制对象，绘制渐变色
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), reverseScrolling = true),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sunset),
            contentDescription = "Sunset",
            modifier = Modifier
                .graphicsLayer {
                    this.scaleX = 1.2f
                    this.scaleY = 0.8f
                }
        )
        Image(
            painter = painterResource(id = R.drawable.sunset),
            contentDescription = "Sunset",
            modifier = Modifier
                .graphicsLayer {
                    this.translationX = 100.dp.toPx()
                    this.translationY = 10.dp.toPx()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.sunset),
            contentDescription = "Sunset",
            modifier = Modifier
                .graphicsLayer {
                    this.transformOrigin = TransformOrigin(0f, 0f)
                    this.rotationX = 90f
                    this.rotationY = 275f
                    this.rotationZ = 180f
                }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo17() { // drawWithCache 可以缓存绘制对象，绘制渐变色
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), reverseScrolling = true),
        verticalArrangement = Arrangement.Center
    ) {
        // 测试1
        Box(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                }
                .background(Color(0xFFF06292))
        ) {// 裁剪和形状
            Text(
                text = "Hello Compose",
                style = TextStyle(color = Color.Black, fontSize = 46.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(0xFF4DB6AC))
        )
        // 测试2
        Box(
            modifier = Modifier
                .size(200.dp)
                .border(BorderStroke(4.dp, Color.Black))
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                    translationY = 50.dp.toPx()
                }
                .background(Color(0xFFF06292))
        ) {// 裁剪和形状
            Text(
                text = "Hello Compose",
                style = TextStyle(color = Color.Black, fontSize = 46.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(0xFF4DB6AC))
        )
        // 测试3
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RectangleShape)
                .border(2.dp, Color.Black)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                    translationY = 50.dp.toPx()
                }
                .background(Color(0xFFF06292))
        ) {// 裁剪和形状
            Text(
                text = "Hello Compose",
                style = TextStyle(color = Color.Black, fontSize = 46.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(0xFF4DB6AC))
        )
        // alpha 值
        Image(
            painter = painterResource(id = R.drawable.sunset),
            contentDescription = "clock",
            modifier = Modifier
                .graphicsLayer {
                    this.alpha = 0.5f
                }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo18() { //
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(120.dp)
            .aspectRatio(1f)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFFC5E1A5),
                        Color(0xFF80DEEA)
                    )
                )
            )
            .padding(8.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                val path = Path()
                path.addOval(
                    Rect(
                        topLeft = Offset.Zero,
                        bottomRight = Offset(size.width, size.height)
                    )
                )
                onDrawWithContent {
                    clipPath(path = path) {
                        this@onDrawWithContent.drawContent()
                    }
                    val dotSize = size.width / 8f
                    drawCircle(
                        Color.Black,
                        radius = dotSize,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        ),
                        blendMode = BlendMode.Clear
                    )
                    drawCircle(
                        Color(0xFFEF5350),
                        radius = dotSize * 0.8f,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        )
                    )
                }
            }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo19() { //
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Canvas(
            modifier = Modifier
                .graphicsLayer()
                .size(100.dp)
                .border(2.dp, color = Color.Blue),
        ) {
            drawRect(color = Color.Magenta, size = Size(200.dp.toPx(), 200.dp.toPx()))
        }

        Spacer(modifier = Modifier.size(300.dp))

        Canvas(
            modifier = Modifier
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                .size(100.dp)
                .border(2.dp, color = Color.Blue)
        ) {
            drawRect(color = Color.Red, size = Size(200.dp.toPx(), 200.dp.toPx()))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo20() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Canvas(
            modifier = Modifier.size(200.dp)
        ) {
            drawSquares()
        }
        Spacer(modifier = Modifier.size(36.dp))

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    alpha = 0.5f
                },
        ) {
            drawSquares()
        }

        Spacer(modifier = Modifier.size(36.dp))

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.ModulateAlpha
                    alpha = 0.5f
                },
        ) {
            drawSquares()
        }
    }
}

private fun DrawScope.drawSquares() {
    val size = Size(100.dp.toPx(), 100.dp.toPx())
    drawRect(color = Red, size = size)
    drawRect(
        color = Purple, size = size,
        topLeft = Offset(size.width / 4f, size.height / 4f)
    )
    drawRect(
        color = Yellow, size = size,
        topLeft = Offset(size.width / 4f * 2f, size.height / 4f * 2f)
    )
}

val Purple = Color(0xFF7E57C2)
val Yellow = Color(0xFFFFCA28)
val Red = Color(0xFFEF5350)





// 自定义绘制修饰符
class FlippedModifier: DrawModifier {
    override fun ContentDrawScope.draw() {
        scale(1f, -1f) {
            this@draw.drawContent()
        }
    }
}

fun Modifier.flipped() = this.then(FlippedModifier())


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDemo21() {
    Text(
        text = "Hello Compose!",
        modifier = Modifier
            .flipped()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomGraphicsDemoScreenPreview() {
    ComposeDemoTheme {
        CustomGraphicsDemoScreen()
    }
}