package com.wyy.compose.simple

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyy.compose.R
import com.wyy.compose.ui.theme.ComposeDemoTheme

/**
 * 一个简单的列表
 */
class SimpleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(msg = Message("Android", "Jetpack Compose"))
                }
            }
        }
    }
}
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}
data class Message(val author: String, val body: String)
val conversationSample = listOf(
    Message("Colleague 1", "Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 2", "Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 3", "Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 4", "Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 5", "Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 6", "Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 7", "Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 8", "Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 9", "Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 10", "Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 11", "Hey, take a look at Jetpack Compose, it's great!"),
    Message("Colleague 12", "Hey, take a look at Jetpack Compose, it's great!"),
)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.picture),
            contentDescription = "图片描述",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember {
            mutableStateOf(false)
        }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )
        Column(
            modifier = Modifier.clickable {
                isExpanded = !isExpanded
            }
        ) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true)
@Composable
fun PreviewMessageCard(): Unit {
    ComposeDemoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageCard(msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!"))
        }
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true)
@Composable
fun PreviewConversation() {
    ComposeDemoTheme {
        Conversation(conversationSample)
    }
}