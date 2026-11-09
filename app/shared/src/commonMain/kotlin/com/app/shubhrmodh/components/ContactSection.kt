package com.app.shubhrmodh.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.theme.BorderWhite
import com.app.shubhrmodh.theme.PureBlack
import com.app.shubhrmodh.theme.PureWhite
import com.app.shubhrmodh.theme.White08
import com.app.shubhrmodh.theme.White70

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    val contact = PortfolioRepository.contact
    val uriHandler = LocalUriHandler.current

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        val isMobile = maxWidth < 800.dp

        Column(
            modifier = Modifier.widthIn(max = 980.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SectionBadge(title = "CONTACT")

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Let's Connect & Collaborate",
                color = PureWhite,
                fontSize = if (isMobile) 24.sp else 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Open to Android, Kotlin Multiplatform, and Mobile Lead engineering roles",
                color = White70,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            val contactCards = listOf(
                Triple("Email", contact.email, "mailto:${contact.email}"),
                Triple("Phone", contact.phone, "tel:${contact.phone}"),
                Triple("Location", contact.location, null),
                Triple("Portfolio / DNS", "portfolio-mshubhr.vercel.app", contact.portfolioUrl)
            )

            if (isMobile) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    contactCards.forEach { (label, value, actionUri) ->
                        ContactInfoCard(
                            label = label,
                            value = value,
                            actionUri = actionUri,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    contactCards.forEach { (label, value, actionUri) ->
                        ContactInfoCard(
                            label = label,
                            value = value,
                            actionUri = actionUri,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { uriHandler.openUri("mailto:${contact.email}") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PureWhite, contentColor = PureBlack
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Icon(
                    imageVector = CustomIcons.Mail,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = PureBlack
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Send an Email", fontSize = 15.sp, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ContactInfoCard(
    label: String, value: String, actionUri: String?, modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = modifier.border(1.dp, BorderWhite, RoundedCornerShape(14.dp))
            .then(
                if (actionUri != null) {
                    Modifier.clickable { uriHandler.openUri(actionUri) }
                } else Modifier),
        colors = CardDefaults.cardColors(containerColor = White08),
        shape = RoundedCornerShape(14.dp)) {
        Column(
            modifier = Modifier.padding(18.dp), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = label,
                color = White70,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                color = PureWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            if (actionUri != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Click to open ↗",
                    color = PureWhite,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
