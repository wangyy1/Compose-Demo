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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
 * 输入框相关属性
 */
class TextFieldDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface() {
                    TextFieldDemoScreen()
                }
            }
        }
    }
}

@Composable
fun TextFieldDemoScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        SimpleFilledTextFieldSample()
        SimpleOutlinedTextFieldSample()
        StyledTextField()
    }
}

@Composable
fun SimpleFilledTextFieldSample() {
    var text by remember {
        mutableStateOf("Hello")
    }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = {
            Text(text = "Label")
        }
    )
}

@Composable
fun SimpleOutlinedTextFieldSample() {
    var text by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = {
            Text(text = "Label")
        }
    )
}


@Composable
fun StyledTextField() {
    var value by remember {
        mutableStateOf("Hello\nWorld\nInvisible")
    }
    TextField(
        value = value,
        onValueChange = {
            value = it
        },
        label = {
            Text(text = "Enter text")
        },
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
    )
}


@Composable
fun PasswordTextField(): Unit {
    var password by rememberSaveable() {
        mutableStateOf("")
    }
    TextField(
        value = password,
        onValueChange = {
            password = it
        },
        label = {
            Text(text = "Enter password")
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextFieldDemoScreenPreview() {
    ComposeDemoTheme {
        TextFieldDemoScreen()
    }
}