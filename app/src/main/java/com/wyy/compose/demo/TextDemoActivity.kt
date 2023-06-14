@file:OptIn(ExperimentalTextApi::class)

package com.wyy.compose.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.wyy.compose.R
import com.wyy.compose.ui.theme.ComposeDemoTheme

/**
 * 文本相关属性
 */
class TextDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    TextDemoScreen()
                }
            }
        }
    }
}

@Composable
fun TextDemoScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        SimpleText()
        StringResourceText()
        BlueText()
        BigText()
        ItalicText()
        BoldText()
        CenterText()
        CenterText2()
        TextShadow()
        DifferentFonts()
        MultipleStylesInText()
        com.wyy.compose.demo.ParagraphStyle()
        LongText()
        OverflowedText()
        SelectableText()
        PartiallySelectableText()
        SimpleClickableText()
        AnnotatedClickableText()
    }
}

@Composable
fun SimpleText() {
    Text(text = "Hello World")
}

@Composable
fun StringResourceText() {
    Text(text = "${stringResource(id = R.string.hello_world)}-Resource")
}

@Composable
fun BlueText() {
    Text(text = "Hello World", color = Color.Blue)
}

@Composable
fun BigText() {
    Text(text = "Hello World", fontSize = 30.sp)
}

@Composable
fun ItalicText() {
    Text(text = "Hello World", fontStyle = FontStyle.Italic)
}

@Composable
fun BoldText() {
    Text(text = "Hello World", fontWeight = FontWeight.Bold)
}

@Composable
fun CenterText() {
    Text(
        text = "Hello World",
        textAlign = TextAlign.Center,
        modifier = Modifier.width(150.dp)
    )
}

@Composable
fun CenterText2() {
    Surface(
        modifier = Modifier.width(150.dp),
        color = Color.Red
    ) {
        Text(
            text = "Hello World",
            textAlign = TextAlign.End,

        )
    }
}

@Composable
fun MultipleStylesInText() {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
        }
    )
}

@Composable
fun LongText() {
    Text(text = "Hello ".repeat(50), maxLines = 2)
}

@Composable
fun OverflowedText() {
    Text(text = "Hello Compose".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis)
}

@Composable
fun ParagraphStyle() {
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("Hello\n")
                }
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                ) {
                    append("World\n")
                }
                append("Compose")
            }
        }
    )
}

@Composable
fun AlignedText() {
    Text(
        text = "Hello world, \nit's a wonderful \nday.",
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.Both
                )
            )
        )
    )
}

@Composable
fun TextShadow() {
    val offset = Offset(10.0f, 10.0f)
    Text(
        text = "Hello world!",
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Blue,
                offset = offset,
                blurRadius = 3f
            )
        )
    )
}

@Composable
fun DifferentFonts() {
    Column {
        Text(text = "Hello World", fontFamily = FontFamily.Serif)
        Text(text = "Hello World", fontFamily = FontFamily.SansSerif)
    }
}

@Composable
fun SelectableText() {
    SelectionContainer {
        Text(text = "This text is selectable")
    }
}

@Composable
fun PartiallySelectableText() {
    SelectionContainer {
        Column {
            Text(text = "This text is selectable")
            Text(text = "This one too")
            Text(text = "This one as well")
            DisableSelection {
                Text(text = "But not this one")
                Text(text = "Neither this one")
            }
            Text(text = "But again, you can select this one")
            Text(text = "And this one too")
        }
    }
}

@Composable
fun SimpleClickableText() {
    ClickableText(
        text = AnnotatedString("Click Me"),
        onClick = {offset ->
            Log.d("ClickableText", "SimpleClickableText: $offset")
        }
    )
}

@Composable
fun AnnotatedClickableText() {
    val annotatedText = buildAnnotatedString {
        append("Click ")
        pushStringAnnotation(tag = "URL", annotation = "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append("her")
        }

    }
    ClickableText(
        text = annotatedText,
        onClick = {offset ->
            annotatedText.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    Log.d("Clicked URL", annotation.item)
                }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextDemoScreenPreview() {
    ComposeDemoTheme {
        TextDemoScreen()
    }
}