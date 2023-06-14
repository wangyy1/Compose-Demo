@file:OptIn(ExperimentalTextApi::class)

package com.wyy.compose.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wyy.compose.R
import com.wyy.compose.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch

/**
 * Image相关属性
 */
class ImageDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    ImageDemoScreen()
                }
            }
        }
    }
}

@Composable
fun ImageDemoScreen() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState, reverseScrolling = true)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.dog), 
            contentDescription = stringResource(id = R.string.dog_content_description),
            modifier = Modifier.height(150.dp)
        )
        AsyncImage(
            model = "https://profile-avatar.csdnimg.cn/default.jpg",
            contentDescription = null
        )
        Icon(Icons.Rounded.ShoppingCart, contentDescription = "")
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(150.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Yellow)
        )
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Yellow)
        )
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(SquashedOval())
        )
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .border(
                    BorderStroke(4.dp, Color.Yellow),
                    CircleShape
                )
                .padding(4.dp)
                .clip(CircleShape)
        )
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.dog_content_description),
            modifier = Modifier.aspectRatio(16f / 9f)
        )
    }
}

// 自定义形状
class SquashedOval: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            addOval(
                Rect(
                    left = size.width / 4f,
                    top = 0f,
                    right = size.width * 3 / 4f,
                    bottom =  size.height
                )
            )
        }
        return Outline.Generic(path = path)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageDemoScreenPreview() {
    ComposeDemoTheme {
        ImageDemoScreen()
    }
}