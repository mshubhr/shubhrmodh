package com.app.shubhrmodh.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.theme.*

@Composable
fun FooterSection(modifier: Modifier = Modifier) {
    val contact = PortfolioRepository.contact
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier.fillMaxWidth().background(BgDark)
            .padding(top = 40.dp, bottom = 32.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            color = BorderDark,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth().widthIn(max = 980.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Shubhr Modh",
            color = TextPrimaryDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Keeping the app lightweight and optimized.",
            color = PrimaryCyan,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialIconButton(
                icon = CustomIcons.GitHub,
                contentDesc = "GitHub",
                onClick = { uriHandler.openUri(contact.githubUrl) })
            SocialIconButton(
                icon = CustomIcons.LinkedIn,
                contentDesc = "LinkedIn",
                onClick = { uriHandler.openUri(contact.linkedinUrl) })
            SocialIconButton(
                icon = CustomIcons.Mail,
                contentDesc = "Email",
                onClick = { uriHandler.openUri("mailto:${contact.email}") })
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Built with Kotlin Multiplatform & Compose • Deployed at ${contact.portfolioUrl}",
            color = TextMutedDark,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}
