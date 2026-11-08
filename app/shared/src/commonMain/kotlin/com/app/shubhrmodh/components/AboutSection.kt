package com.app.shubhrmodh.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.theme.AccentEmerald
import com.app.shubhrmodh.theme.AccentIndigo
import com.app.shubhrmodh.theme.BorderDark
import com.app.shubhrmodh.theme.BorderGlow
import com.app.shubhrmodh.theme.CardDark
import com.app.shubhrmodh.theme.CardDarkElevated
import com.app.shubhrmodh.theme.PrimaryCyan
import com.app.shubhrmodh.theme.TextPrimaryDark
import com.app.shubhrmodh.theme.TextSecondaryDark

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
    val education = PortfolioRepository.education

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        val isMobile = maxWidth < 800.dp

        Column(
            modifier = Modifier.widthIn(max = 980.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SectionBadge(title = "ABOUT ME")

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Building Scalable & Delightful Mobile Experiences",
                color = TextPrimaryDark,
                fontSize = if (isMobile) 24.sp else 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(28.dp))

            if (isMobile) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    BioCard(modifier = Modifier.fillMaxWidth())
                    EducationCard(education = education, modifier = Modifier.fillMaxWidth())
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    BioCard(modifier = Modifier.weight(1.3f))
                    EducationCard(education = education, modifier = Modifier.weight(0.9f))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            val pillars = listOf(
                Triple(
                    "⚡ Modern Jetpack Compose",
                    "Declarative UIs, smooth animations, M3 theming, MotionLayout, and custom components.",
                    PrimaryCyan
                ), Triple(
                    "🌐 Kotlin Multiplatform",
                    "KMP shared networking and business logic with Ktor, achieving 85%+ reuse across Android & iOS.",
                    AccentIndigo
                ), Triple(
                    "🛡️ High Reliability",
                    "Deep experience optimizing 16KB page-sizes, SDK 35 migrations, R8 rules, and crash rate reduction.",
                    AccentEmerald
                )
            )

            if (isMobile) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    pillars.forEach { (title, desc, color) ->
                        PillarCard(
                            title = title,
                            desc = desc,
                            color = color,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    pillars.forEach { (title, desc, color) ->
                        PillarCard(
                            title = title,
                            desc = desc,
                            color = color,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BioCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.border(1.dp, BorderDark, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Professional Background",
                color = TextPrimaryDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "I am an Android Developer with 2+ years of hands-on experience building, scaling, and publishing Android applications on Google Play Store. I focus on Clean Architecture, MVVM, asynchronous state handling with Kotlin Coroutines & Flow, and rock-solid offline-first storage with Room.",
                color = TextSecondaryDark,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "My work spans real-time interactive apps with Agora audio/video calling, FCM push notifications, AWS Rekognition biometric verification, Google Maps integrations, and full Android system features such as sticky notifications, overlay permissions, and Wake Locks.",
                color = TextSecondaryDark,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun EducationCard(
    education: com.app.shubhrmodh.data.EducationItem, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.border(1.dp, BorderDark, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = CardDark),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = CustomIcons.School,
                    contentDescription = "Education",
                    tint = PrimaryCyan,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Education",
                    color = TextPrimaryDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = education.institution,
                color = PrimaryCyan,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = education.degree,
                color = TextPrimaryDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = education.period, color = TextSecondaryDark, fontSize = 13.sp
                )
                Surface(
                    color = CardDarkElevated,
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderGlow)
                ) {
                    Text(
                        text = education.cgpa,
                        color = AccentEmerald,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PillarCard(
    title: String,
    desc: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.border(1.dp, BorderDark, RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = CardDarkElevated),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = title, color = color, fontSize = 15.sp, fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = desc, color = TextSecondaryDark, fontSize = 13.sp, lineHeight = 19.sp
            )
        }
    }
}

@Composable
fun SectionBadge(title: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = CardDarkElevated,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderGlow)
    ) {
        Text(
            text = title,
            color = PrimaryCyan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
        )
    }
}
