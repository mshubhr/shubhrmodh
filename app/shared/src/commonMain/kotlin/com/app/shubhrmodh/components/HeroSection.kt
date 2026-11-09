package com.app.shubhrmodh.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.theme.BorderWhite
import com.app.shubhrmodh.theme.BorderWhiteBold
import com.app.shubhrmodh.theme.PureBlack
import com.app.shubhrmodh.theme.PureWhite
import com.app.shubhrmodh.theme.White08
import com.app.shubhrmodh.theme.White70

@Composable
fun HeroSection(
    onExploreProjects: () -> Unit, onContactClick: () -> Unit, modifier: Modifier = Modifier
) {
    val contact = PortfolioRepository.contact
    val uriHandler = LocalUriHandler.current

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        val isMobile = maxWidth < 768.dp

        Column(
            modifier = Modifier.widthIn(max = 980.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                shape = RoundedCornerShape(30.dp),
                color = White08,
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderWhiteBold),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(10.dp).clip(CircleShape).background(PureWhite)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Available for Android & Mobile Engineering Roles",
                        color = PureWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Text(
                text = "Hi, I'm ${contact.name}",
                color = PureWhite,
                fontSize = if (isMobile) 32.sp else 54.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                lineHeight = if (isMobile) 40.sp else 62.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Android Developer & KMP Specialist",
                fontSize = if (isMobile) 22.sp else 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = White70
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = contact.summary,
                color = White70,
                fontSize = if (isMobile) 15.sp else 17.sp,
                textAlign = TextAlign.Center,
                lineHeight = 26.sp,
                modifier = Modifier.widthIn(max = 760.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onContactClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PureWhite, contentColor = PureBlack
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "Get In Touch", fontWeight = FontWeight.Bold, fontSize = 15.sp
                    )
                }

                OutlinedButton(
                    onClick = onExploreProjects,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp, PureWhite
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = PureWhite
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "View Projects", fontWeight = FontWeight.SemiBold, fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

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
                SocialIconButton(
                    icon = CustomIcons.Phone,
                    contentDesc = "Phone",
                    onClick = { uriHandler.openUri("tel:${contact.phone}") })
            }

            Spacer(modifier = Modifier.height(48.dp))

            val stats = listOf(
                "2+ Years" to "Android Experience",
                "20%" to "Startup Time Boost",
                "30%" to "Fewer Crashes",
                "85%+" to "KMP Code Reuse",
                "SSIP 2.0" to "Hackathon Winner"
            )

            Row(
                modifier = Modifier.fillMaxWidth().widthIn(max = 940.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isMobile) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        stats.chunked(2).forEach { rowStats ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                rowStats.forEach { (value, label) ->
                                    StatCard(
                                        value = value, label = label, modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                } else {
                    stats.forEach { (value, label) ->
                        StatCard(
                            value = value, label = label, modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(
    value: String, label: String, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.border(1.dp, BorderWhite, RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = White08),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                color = PureWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                color = White70,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SocialIconButton(
    icon: ImageVector, contentDesc: String, onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(42.dp).clip(CircleShape).background(White08)
            .border(1.dp, BorderWhite, CircleShape).clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDesc,
            tint = PureWhite,
            modifier = Modifier.size(20.dp)
        )
    }
}
