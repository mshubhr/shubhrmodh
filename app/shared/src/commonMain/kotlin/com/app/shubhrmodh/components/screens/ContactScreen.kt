package com.app.shubhrmodh.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.components.CustomIcons
import com.app.shubhrmodh.components.motion.MotionCollapsingHeader
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.theme.BorderWhite
import com.app.shubhrmodh.theme.PureBlack
import com.app.shubhrmodh.theme.PureWhite
import com.app.shubhrmodh.theme.White08
import com.app.shubhrmodh.theme.White50
import com.app.shubhrmodh.theme.White70

@Composable
fun ContactScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contact = PortfolioRepository.contact
    val uriHandler = LocalUriHandler.current
    val scrollState = rememberScrollState()

    var dialedNumber by remember { mutableStateOf(contact.phone) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PureBlack)
    ) {
        MotionCollapsingHeader(
            title = "Phone & Dialer",
            subtitle = "Call, Email & Network",
            icon = CustomIcons.Phone,
            scrollOffset = scrollState.value,
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(White08)
                    .border(1.dp, BorderWhite, RoundedCornerShape(16.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "DIALER",
                    color = White50,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = dialedNumber,
                    color = PureWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = contact.name,
                    color = White70,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        try {
                            uriHandler.openUri("tel:${dialedNumber.replace(" ", "")}")
                        } catch (_: Exception) {
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PureWhite, contentColor = PureBlack),
                    shape = RoundedCornerShape(24.dp),
                    contentPadding = PaddingValues(horizontal = 28.dp, vertical = 12.dp)
                ) {
                    Icon(
                        imageVector = CustomIcons.Phone,
                        contentDescription = "Call",
                        tint = PureBlack,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Call Now", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }

            val contactCards = listOf(
                Triple("Email", contact.email, CustomIcons.Mail to "mailto:${contact.email}"),
                Triple("LinkedIn", "shubhr-modh-05b232222", CustomIcons.LinkedIn to contact.linkedinUrl),
                Triple("GitHub", "github.com/mshubhr", CustomIcons.GitHub to contact.githubUrl),
                Triple("Location", contact.location, CustomIcons.Location to null)
            )

            contactCards.forEach { (label, value, action) ->
                val (icon, uri) = action
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(White08)
                        .border(1.dp, BorderWhite, RoundedCornerShape(12.dp))
                        .clickable(enabled = uri != null) {
                            if (uri != null) uriHandler.openUri(uri)
                        }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(PureWhite),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = label,
                            tint = PureBlack,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = label,
                            color = White70,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = value,
                            color = PureWhite,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (uri != null) {
                        Text(
                            text = "OPEN ↗",
                            color = PureWhite,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}
