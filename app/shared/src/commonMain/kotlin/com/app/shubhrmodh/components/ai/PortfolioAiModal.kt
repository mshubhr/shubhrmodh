package com.app.shubhrmodh.components.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.ai.AiActionTarget
import com.app.shubhrmodh.ai.AiChatMessage
import com.app.shubhrmodh.ai.AiSender
import com.app.shubhrmodh.ai.PortfolioAiEngine
import com.app.shubhrmodh.components.CustomIcons
import com.app.shubhrmodh.theme.AndroidBorderDark
import com.app.shubhrmodh.theme.AndroidCardDark
import com.app.shubhrmodh.theme.AndroidCardInner
import com.app.shubhrmodh.theme.AndroidDarkBg
import com.app.shubhrmodh.theme.AndroidGreenAccent
import com.app.shubhrmodh.theme.AndroidGreenDarkContainer
import com.app.shubhrmodh.theme.AndroidSoftGrey
import com.app.shubhrmodh.theme.AndroidTextWhite
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PortfolioAiModal(
    onActionSelected: (AiActionTarget) -> Unit,
    onDismiss: () -> Unit
) {
    val messages = remember {
        mutableStateListOf(PortfolioAiEngine.getInitialGreeting())
    }
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val quickPrompts = listOf(
        "💼 Summarize Shubhr's experience",
        "🛠 What is his primary tech stack?",
        "🚀 Tell me about FieldSync & ShopFlow",
        "📩 How can I contact or hire Shubhr?"
    )

    fun sendMessage(text: String) {
        val prompt = text.trim()
        if (prompt.isBlank()) return

        messages.add(
            AiChatMessage(
                id = "user_${messages.size}",
                sender = AiSender.User,
                text = prompt
            )
        )
        inputText = ""

        val aiResponse = PortfolioAiEngine.generateResponse(prompt)
        messages.add(aiResponse)

        scope.launch {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onDismiss
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 620.dp)
                .fillMaxWidth(0.94f)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {}
                )
                .border(1.dp, AndroidBorderDark, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = AndroidCardDark),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Modal Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(AndroidGreenDarkContainer)
                                .border(1.dp, AndroidGreenAccent, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = CustomIcons.Sparkles,
                                contentDescription = null,
                                tint = AndroidGreenAccent,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = "Shubhr AI Assistant",
                                color = AndroidTextWhite,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(AndroidGreenAccent)
                                )
                                Text(
                                    text = "Online • On-device KMP AI",
                                    color = AndroidGreenAccent,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    Text(
                        text = "✕",
                        color = AndroidSoftGrey,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable(
                                role = Role.Button,
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onDismiss
                            )
                            .padding(6.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(AndroidBorderDark)
                )

                // Quick Prompt Chips
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    quickPrompts.forEach { prompt ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(AndroidCardInner)
                                .border(1.dp, AndroidBorderDark, RoundedCornerShape(6.dp))
                                .clickable { sendMessage(prompt) }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = prompt,
                                color = AndroidSoftGrey,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                // Chat Messages List
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AndroidDarkBg)
                        .border(1.dp, AndroidBorderDark, RoundedCornerShape(12.dp))
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(messages, key = { it.id }) { msg ->
                        AiChatMessageBubble(
                            message = msg,
                            onActionClick = { target ->
                                onActionSelected(target)
                                onDismiss()
                            }
                        )
                    }
                }

                // Input Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BasicTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        singleLine = true,
                        cursorBrush = SolidColor(AndroidGreenAccent),
                        textStyle = TextStyle(
                            color = AndroidTextWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = { sendMessage(inputText) }),
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(AndroidCardInner)
                            .border(1.dp, AndroidBorderDark, RoundedCornerShape(8.dp))
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        decorationBox = { innerTextField ->
                            Box {
                                if (inputText.isEmpty()) {
                                    Text(
                                        text = "Ask anything about Shubhr's work...",
                                        color = AndroidSoftGrey,
                                        fontSize = 14.sp
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (inputText.isNotBlank()) AndroidGreenAccent else AndroidCardInner)
                            .border(
                                1.dp,
                                if (inputText.isNotBlank()) AndroidGreenAccent else AndroidBorderDark,
                                RoundedCornerShape(8.dp)
                            )
                            .clickable(enabled = inputText.isNotBlank()) {
                                sendMessage(inputText)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = CustomIcons.ArrowRight,
                            contentDescription = "Send",
                            tint = if (inputText.isNotBlank()) Color(0xFF0B0F0D) else AndroidSoftGrey,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AiChatMessageBubble(
    message: AiChatMessage,
    onActionClick: (AiActionTarget) -> Unit
) {
    val isUser = message.sender == AiSender.User

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 440.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = if (isUser) 12.dp else 2.dp,
                        bottomEnd = if (isUser) 2.dp else 12.dp
                    )
                )
                .background(if (isUser) AndroidGreenDarkContainer else AndroidCardInner)
                .border(
                    1.dp,
                    if (isUser) AndroidGreenAccent else AndroidBorderDark,
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = if (isUser) 12.dp else 2.dp,
                        bottomEnd = if (isUser) 2.dp else 12.dp
                    )
                )
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = message.text,
                color = if (isUser) AndroidGreenAccent else AndroidTextWhite,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                fontWeight = FontWeight.Medium
            )

            if (!isUser && message.actionLabel != null && message.actionTarget != null) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(AndroidCardDark)
                        .border(1.dp, AndroidGreenAccent, RoundedCornerShape(6.dp))
                        .clickable { onActionClick(message.actionTarget) }
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = message.actionLabel,
                        color = AndroidGreenAccent,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
