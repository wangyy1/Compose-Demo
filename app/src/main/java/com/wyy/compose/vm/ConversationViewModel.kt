package com.wyy.compose.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wyy.compose.demo.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConversationViewModel: ViewModel() {

    val conversationSample = mutableStateListOf(
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

}