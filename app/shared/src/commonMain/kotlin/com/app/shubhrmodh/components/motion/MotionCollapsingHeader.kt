package com.app.shubhrmodh.components.motion

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.components.CustomIcons
import com.app.shubhrmodh.theme.BorderWhite
import com.app.shubhrmodh.theme.PureBlack
import com.app.shubhrmodh.theme.PureWhite
import com.app.shubhrmodh.theme.White08
import com.app.shubhrmodh.theme.White70

@Composable
fun MotionCollapsingHeader(
    title: String,
    subtitle: String,
    icon: ImageVector,
    scrollOffset: Int,
    maxScrollOffset: Float = 140f,
    onBack: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    actionIcon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    val rawProgress = (scrollOffset / maxScrollOffset).coerceIn(0f, 1f)
    val progress by animateFloatAsState(targetValue = rawProgress, label = "MotionProgress")

    val headerHeight = (120 - (progress * 56)).dp
    val titleFontSize = (26 - (progress * 8)).sp
    val subtitleAlpha = (1f - (progress * 1.5f)).coerceIn(0f, 1f)
    val iconSize = (40 - (progress * 14)).dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(headerHeight)
            .background(PureBlack)
            .border(
                width = if (progress > 0.4f) 1.dp else 0.dp,
                color = if (progress > 0.4f) BorderWhite else PureBlack,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBack != null) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(White08)
                ) {
                    Icon(
                        imageVector = CustomIcons.ArrowBack,
                        contentDescription = "Navigate Back",
                        tint = PureWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }

            Box(
                modifier = Modifier
                    .size(iconSize)
                    .clip(RoundedCornerShape(8.dp))
                    .background(PureWhite),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = PureBlack,
                    modifier = Modifier.size(iconSize * 0.55f)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = PureWhite,
                    fontSize = titleFontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                if (subtitleAlpha > 0.05f) {
                    Text(
                        text = subtitle,
                        color = White70,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.alpha(subtitleAlpha),
                        maxLines = 1
                    )
                }
            }

            if (actionIcon != null && onActionClick != null) {
                IconButton(
                    onClick = onActionClick,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(White08)
                ) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = "Action",
                        tint = PureWhite,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
