package com.app.shubhrmodh.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.data.ExperienceItem
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.theme.BorderWhite
import com.app.shubhrmodh.theme.PureBlack
import com.app.shubhrmodh.theme.PureWhite
import com.app.shubhrmodh.theme.White08
import com.app.shubhrmodh.theme.White70

@Composable
fun ExperienceSection(modifier: Modifier = Modifier) {
    val experiences = PortfolioRepository.experiences

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        val isMobile = maxWidth < 800.dp

        Column(
            modifier = Modifier.widthIn(max = 980.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SectionBadge(title = "EXPERIENCE")

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Professional Work Experience",
                color = PureWhite,
                fontSize = if (isMobile) 24.sp else 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                experiences.forEach { exp ->
                    ExperienceCard(exp = exp, isMobile = isMobile)
                }
            }
        }
    }
}

@Composable
fun ExperienceCard(exp: ExperienceItem, isMobile: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, BorderWhite, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = White08),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            if (isMobile) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = exp.role,
                        color = PureWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${exp.company} • ${exp.location}",
                        color = White70,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = PureWhite
                    ) {
                        Text(
                            text = exp.period,
                            color = PureBlack,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = exp.role,
                            color = PureWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${exp.company} • ${exp.location}",
                            color = White70,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PureWhite
                    ) {
                        Text(
                            text = exp.period,
                            color = PureBlack,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()
            ) {
                exp.bullets.forEach { bullet ->
                    Row(
                        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "•",
                            color = PureWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 10.dp, top = 1.dp)
                        )
                        Text(
                            text = bullet,
                            color = White70,
                            fontSize = 14.sp,
                            lineHeight = 21.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                exp.tags.forEach { tag ->
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = PureWhite
                    ) {
                        Text(
                            text = tag,
                            color = PureBlack,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
