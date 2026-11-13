package com.app.shubhrmodh.components.launcher

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val StudyBg = Color(0xFF0A100D)
private val StudyLine = Color(0xFF30443A)
private val StudyInk = Color(0xFFE9E8DF)
private val StudyMuted = Color(0xFFA9AEA7)
private val StudyAccent = Color(0xFF7C9E86)

@Composable
internal fun ProjectTechnicalStudy(
    name: String,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource,
    onOpen: (() -> Unit)? = null
) {
    val hovered by interactionSource.collectIsHoveredAsState()
    val clickModifier = if (onOpen == null) Modifier else Modifier.clickable(
        role = Role.Button,
        interactionSource = interactionSource,
        indication = null,
        onClick = onOpen
    )
    Column(
        modifier.fillMaxWidth()
            .height(250.dp)
            .background(StudyBg)
            .border(1.dp, if (hovered) StudyAccent else StudyLine)
            .then(clickModifier)
            .padding(18.dp)
            .semantics {
                contentDescription = if (onOpen == null) {
                    "$name engineering diagram"
                } else {
                    "Show $name project contributions. Engineering system diagram, not a product screenshot."
                }
            }
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("ENGINEERING STUDY", color = StudyAccent, fontSize = 9.sp, letterSpacing = 1.5.sp)
            Text(
                "DIAGRAM  /  0${when (name) { "FieldSync" -> "1"; "ShopFlow" -> "2"; else -> "3" }}",
                color = StudyMuted,
                fontSize = 8.sp,
                letterSpacing = 1.sp
            )
        }
        Spacer(Modifier.height(23.dp))
        when (name) {
            "FieldSync" -> FieldSyncDiagram()
            "ShopFlow" -> ShopFlowDiagram()
            else -> VideoAppDiagram()
        }
        Spacer(Modifier.weight(1f))
        Text("CONCEPTUAL SYSTEM MAP  ·  NOT A PRODUCT SCREEN", color = StudyMuted, fontSize = 8.sp, letterSpacing = .8.sp)
    }
}

@Composable
private fun FieldSyncDiagram() {
    Text("Shared foundation, two native apps.", color = StudyInk, fontSize = 15.sp, fontWeight = FontWeight.Medium)
    Spacer(Modifier.height(22.dp))
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp), verticalAlignment = Alignment.CenterVertically) {
        StudyNode("ANDROID", Modifier.weight(1f))
        StudyArrow()
        StudyNode("KMP SHARED", Modifier.weight(1.15f), accent = true)
        StudyArrow()
        StudyNode("iOS", Modifier.weight(.7f))
    }
    Spacer(Modifier.height(15.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.width(1.dp).height(18.dp).background(StudyAccent))
        Spacer(Modifier.width(14.dp))
        Text("Ktor sync engine  /  offline caching  /  device registration", color = StudyMuted, fontSize = 10.sp, lineHeight = 16.sp)
    }
}

@Composable
private fun ShopFlowDiagram() {
    Text("Catalog stays useful offline.", color = StudyInk, fontSize = 15.sp, fontWeight = FontWeight.Medium)
    Spacer(Modifier.height(22.dp))
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp), verticalAlignment = Alignment.CenterVertically) {
        StudyNode("CATALOG", Modifier.weight(1f))
        StudyArrow()
        StudyNode("ROOM", Modifier.weight(.8f), accent = true)
        StudyArrow()
        StudyNode("CART", Modifier.weight(.8f))
    }
    Spacer(Modifier.height(15.dp))
    Text("Ktor REST service   ·   sorting   ·   pagination", color = StudyMuted, fontSize = 10.sp, lineHeight = 16.sp)
}

@Composable
private fun VideoAppDiagram() {
    Text("A video application, server-backed.", color = StudyInk, fontSize = 15.sp, fontWeight = FontWeight.Medium)
    Spacer(Modifier.height(22.dp))
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp), verticalAlignment = Alignment.CenterVertically) {
        StudyNode("VIDEO APP", Modifier.weight(1f))
        StudyArrow()
        StudyNode("GO", Modifier.weight(.65f), accent = true)
        StudyArrow()
        StudyNode("POSTGRESQL", Modifier.weight(1f))
    }
    Spacer(Modifier.height(15.dp))
    Text("SERVER LAYER  /  GO · POSTGRESQL", color = StudyMuted, fontSize = 10.sp, lineHeight = 16.sp)
}

@Composable
private fun StudyNode(label: String, modifier: Modifier = Modifier, accent: Boolean = false) {
    Box(
        modifier.height(38.dp)
            .background(if (accent) Color(0xFF17271F) else Color.Transparent)
            .border(1.dp, if (accent) StudyAccent else StudyLine, RoundedCornerShape(1.dp))
            .padding(horizontal = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(label, color = if (accent) StudyInk else StudyMuted, fontSize = 7.sp, letterSpacing = .3.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Medium, maxLines = 1)
    }
}

@Composable
private fun StudyArrow() {
    Canvas(Modifier.width(15.dp).height(9.dp)) {
        val color = StudyAccent
        drawLine(color, Offset.Zero.copy(y = size.height / 2), Offset(size.width - 3f, size.height / 2), 1.2f, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width - 6f, 1f), Offset(size.width - 2f, size.height / 2), 1.2f)
        drawLine(color, Offset(size.width - 6f, size.height - 1f), Offset(size.width - 2f, size.height / 2), 1.2f)
    }
}
