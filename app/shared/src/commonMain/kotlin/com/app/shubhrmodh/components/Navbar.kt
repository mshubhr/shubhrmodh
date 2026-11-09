package com.app.shubhrmodh.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

enum class PortfolioSection(val displayName: String) {
    HOME("Home"), ABOUT("About"), EXPERIENCE("Experience"), PROJECTS("Work"), SKILLS("Skills"), ACHIEVEMENTS(
        "Achievements"
    ),
    CONTACT("Contact")
}

@Composable
fun Navbar(
    currentSection: PortfolioSection,
    onNavigate: (PortfolioSection) -> Unit,
    modifier: Modifier = Modifier
) {
    var mobileMenuOpen by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth().background(PureBlack).border(
            width = 1.dp,
            color = BorderWhite,
            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
        ).padding(horizontal = 24.dp, vertical = 14.dp)
    ) {
        val isMobile = maxWidth < 880.dp

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        .clickable { onNavigate(PortfolioSection.HOME) }
                        .padding(horizontal = 8.dp, vertical = 4.dp)) {
                    Box(
                        modifier = Modifier.size(36.dp).clip(CircleShape).background(PureWhite),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SM",
                            color = PureBlack,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Shubhr",
                        color = PureWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = ".",
                        color = PureWhite,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp
                    )
                }

                if (!isMobile) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        PortfolioSection.entries.forEach { section ->
                            val isSelected = currentSection == section
                            TextButton(
                                onClick = { onNavigate(section) },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = if (isSelected) PureWhite else White70
                                )
                            ) {
                                Text(
                                    text = section.displayName,
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { uriHandler.openUri("mailto:${PortfolioRepository.contact.email}") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PureWhite, contentColor = PureBlack
                            ),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Contact", fontSize = 13.sp, fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } else {
                    IconButton(
                        onClick = { mobileMenuOpen = !mobileMenuOpen }) {
                        Icon(
                            imageVector = if (mobileMenuOpen) CustomIcons.Close else CustomIcons.Menu,
                            contentDescription = "Toggle Menu",
                            tint = PureWhite
                        )
                    }
                }
            }

            AnimatedVisibility(visible = isMobile && mobileMenuOpen) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    PortfolioSection.entries.forEach { section ->
                        val isSelected = currentSection == section
                        Row(
                            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) White08 else PureBlack)
                                .clickable {
                                    onNavigate(section)
                                    mobileMenuOpen = false
                                }.padding(horizontal = 16.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = section.displayName,
                                color = PureWhite,
                                fontSize = 15.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            uriHandler.openUri("mailto:${PortfolioRepository.contact.email}")
                            mobileMenuOpen = false
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = PureWhite, contentColor = PureBlack
                        ), shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Get in Touch (${PortfolioRepository.contact.email})",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
