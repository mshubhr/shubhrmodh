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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.navigation.NavKey
import com.app.shubhrmodh.theme.Black90
import com.app.shubhrmodh.theme.BorderWhiteBold
import com.app.shubhrmodh.theme.PureBlack
import com.app.shubhrmodh.theme.PureWhite
import com.app.shubhrmodh.theme.White08
import com.app.shubhrmodh.theme.White70

@Composable
fun RecentsScreen(
    onSelectKey: (NavKey) -> Unit,
    onClearAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    val recentApps = listOf(
        Pair(NavKey.Projects, "ShopFlow & FieldSync KMP Projects"),
        Pair(NavKey.ExoPlayer, "ExoPlayer Media3 Audio Engine"),
        Pair(NavKey.Experience, "Helsy Infotech & Differenz System"),
        Pair(NavKey.Skills, "Kotlin, Compose & Clean Architecture"),
        Pair(NavKey.About, "Shubhr Modh Profile & Specs"),
        Pair(NavKey.Contact, "Phone Dialer & Direct Mail")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black90)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RECENT TASKS",
            color = White70,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            recentApps.forEach { (key, desc) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, BorderWhiteBold, RoundedCornerShape(16.dp))
                        .clickable { onSelectKey(key) },
                    colors = CardDefaults.cardColors(containerColor = White08),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = key.title,
                                color = PureWhite,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Box(
                                modifier = Modifier
                                    .width(6.dp)
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(PureWhite)
                            )
                        }

                        Text(
                            text = desc,
                            color = White70,
                            fontSize = 11.sp,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onClearAll,
            colors = ButtonDefaults.buttonColors(containerColor = PureWhite, contentColor = PureBlack),
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)
        ) {
            Text(text = "Clear All Tasks", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}
