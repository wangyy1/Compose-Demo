package com.wyy.compose.demo

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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wyy.compose.R
import com.wyy.compose.vm.ConversationViewModel
import kotlinx.coroutines.launch

/**
 * 测试状态提升
 * LazyColumn
 */
class Demo3Activity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SavedStateHandle()
            ConversationScreen()
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
private fun ConversationScreen(
    conversationViewModel: ConversationViewModel = viewModel()
) {
    val lazyListScope = rememberLazyListState()

    MessagesList(messages = conversationSample, lazyListScope)
}

@Composable
private fun MessageCard(msg: Message) {
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

@Composable
private fun MessagesList(
    messages: List<Message>,
    lazyListScope: LazyListState = rememberLazyListState()
) {
    val scope = rememberCoroutineScope()
    LazyColumn(
        state = lazyListScope
    ) {
        items(messages, key = {message -> message.author}) {
            MessageCard(it)
        }
    }
    Button(
        onClick = {
        scope.launch {
            lazyListScope.scrollToItem(0)
            lazyListScope.firstVisibleItemScrollOffset
        }
    }) {
        Text(text = "Jump to bottom")
    }
}


@Preview
@Composable
fun ConversationScreenPreview(): Unit {
    ConversationScreen()
}