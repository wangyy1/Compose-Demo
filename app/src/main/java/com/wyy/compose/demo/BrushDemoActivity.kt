@file:OptIn(ExperimentalTextApi::class)

package com.wyy.compose.demo

import android.graphics.RuntimeShader
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
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
import androidx.compose.ui.platform.LocalDensity
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
import org.intellij.lang.annotations.Language
import kotlin.math.roundToInt

/**
 * Brush：渐变和着色器
 */
class BrushDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    BrushDemoScreen()
                }
            }
        }
    }
}

@Composable
fun BrushDemoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState(), reverseScrolling = true),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Demo1()
    }
}

@Composable
fun Demo1() {
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawRect(brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)))
        }
    )
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawRect(brush = Brush.linearGradient(listOf(Color.Red, Color.Blue)))
        }
    )
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawRect(brush = Brush.verticalGradient(listOf(Color.Red, Color.Blue)))
        }
    )
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawRect(brush = Brush.sweepGradient(listOf(Color.Red, Color.Blue)))
        }
    )
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawRect(brush = Brush.radialGradient(listOf(Color.Red, Color.Blue)))
        }
    )
    Canvas( // 使用 colorStop 更改颜色分布
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawRect(
                brush = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.Yellow,
                        0.2f to Color.Red,
                        1f to Color.Blue
                    )
                )
            )
        }
    )
    val titleSize = with(LocalDensity.current) {
        50.dp.toPx()
    }
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(Color.Yellow, Color.Red, Color.Blue),
                    endX = titleSize,
                    tileMode = TileMode.Repeated
                )
            )
    )
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(Color.Yellow, Color.Red, Color.Blue),
                    endX = titleSize,
                    tileMode = TileMode.Mirror
                )
            )
    )
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(Color.Yellow, Color.Red, Color.Blue),
                    endX = titleSize,
                    tileMode = TileMode.Clamp
                )
            )
    )
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(Color.Yellow, Color.Red, Color.Blue),
                    endX = titleSize,
                    tileMode = TileMode.Decal
                )
            )
    )
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val customBrush = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                return LinearGradientShader(
                    from =  Offset.Zero,
                    colors = listColors,
                    to = Offset(size.width / 4f, 0f),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(customBrush)
    ) {

    }

    Box(modifier = Modifier
        .size(200.dp)
//        .fillMaxSize()
        .background(
            Brush.radialGradient(
                listOf(Color(0xFF2be4dc), Color(0xFF243484))
            )
        )
    )

    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 0.95f)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
    )

    val imageBrush = ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.dog)))

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(imageBrush)
    )

    Text(
        text = "Hello Android!",
        style = TextStyle(
            brush = imageBrush,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp
        )
    )

    Canvas(modifier = Modifier.size(200.dp), onDraw = {
        drawCircle(imageBrush)
    })



}

@Language("AGSL")
val CUSTOM_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;
    
    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;
        
        float mixValue = distance(uv, vec2(0, 1));
        return mix(color, color2, mixValue)
    }
""".trimIndent()

val Coral = Color(0xFFF3A397)
val LightYellow = Color(0xFFF8EE92)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
@Preview
fun ShaderBrushExample() {
    Box(
        modifier = Modifier
            .drawWithCache {
                val shader = RuntimeShader(CUSTOM_SHADER)
                val shaderBrush = ShaderBrush(shader)
                shader.setFloatUniform("resolution", size.width, size.height)
                onDrawBehind {
                    shader.setColorUniform(
                        "color",
                        android.graphics.Color.valueOf(
                            LightYellow.red, LightYellow.green,
                            LightYellow.blue, LightYellow.alpha
                        )
                    )
                    shader.setColorUniform(
                        "color2",
                        android.graphics.Color.valueOf(
                            Coral.red,
                            Coral.green,
                            Coral.blue,
                            Coral.alpha
                        )
                    )
                }
            }
            .fillMaxWidth()
            .height(200.dp)
    ) {

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BrushDemoScreenPreview() {
    ComposeDemoTheme {
        BrushDemoScreen()
    }
}