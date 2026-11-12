package com.app.shubhrmodh.components.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.navigation.NavKey

private val Paper = Color(0xFFF4F1E9)
private val Ink = Color(0xFF1D211E)
private val Muted = Color(0xFF686C65)
private val Accent = Color(0xFF9A4F35)

@Composable
fun WebPortfolioScreen(onNavigate: (NavKey) -> Unit) {
    val data = PortfolioRepository
    Column(Modifier.fillMaxSize().background(Paper).verticalScroll(rememberScrollState())) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("SM.  /  SHUBHR MODH", color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                listOf("About", "Work", "Projects", "Contact").forEachIndexed { i, label ->
                    Text(label, Modifier.clickable { onNavigate(listOf(NavKey.About, NavKey.Experience, NavKey.Projects, NavKey.Contact)[i]) }, color = Ink, fontSize = 13.sp)
                }
            }
        }
        Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp).padding(top = 54.dp, bottom = 72.dp)) {
            Text("INDEPENDENT SOFTWARE ENGINEER  ·  SURAT, INDIA", color = Accent, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(22.dp))
            Text("Building thoughtful\nAndroid experiences.", color = Ink, fontSize = 54.sp, lineHeight = 58.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(20.dp))
            Text("Android Developer  /  Kotlin  /  Kotlin Multiplatform", color = Muted, fontSize = 17.sp)
            Spacer(Modifier.height(28.dp))
            Text("Explore selected work  ↓", Modifier.clickable { onNavigate(NavKey.Projects) }, color = Accent, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
        Section("A little about me", "ABOUT") {
            Text(data.contact.summary, color = Muted, fontSize = 16.sp, lineHeight = 26.sp)
            Spacer(Modifier.height(16.dp))
            Text("More about Shubhr  ↗", Modifier.clickable { onNavigate(NavKey.About) }, color = Accent)
        }
        Section("Selected work", "PROJECTS") {
            data.projects.filter { it.name in listOf("FieldSync", "ShopFlow") }.forEachIndexed { index, project ->
                Column(Modifier.fillMaxWidth().clickable { onNavigate(NavKey.Projects) }.padding(vertical = 20.dp)) {
                    Text("0${index + 1}   /   ${project.name}", color = Ink, fontSize = 24.sp, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(6.dp))
                    Text(project.subtitle, color = Muted, fontSize = 15.sp)
                    Spacer(Modifier.height(10.dp))
                    Text(project.tags.take(5).joinToString("   ·   "), color = Accent, fontSize = 12.sp)
                }
            }
            Text("All projects  ↗", Modifier.clickable { onNavigate(NavKey.Projects) }, color = Accent)
        }
        Section("Tools of the trade", "CAPABILITIES") {
            data.skillCategories.forEach { category ->
                Text(category.title, color = Ink, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(6.dp))
                Text(category.skills.joinToString("  /  "), color = Muted, fontSize = 14.sp, lineHeight = 24.sp)
                Spacer(Modifier.height(20.dp))
            }
        }
        Section("Experience & education", "BACKGROUND") {
            data.experiences.forEach { item ->
                Text(item.role + "  ·  " + item.company, color = Ink, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                Text(item.period + "  /  " + item.location, color = Muted, fontSize = 13.sp)
                Spacer(Modifier.height(20.dp))
            }
            Text(data.education.institution, color = Ink, fontWeight = FontWeight.SemiBold)
            Text("${data.education.degree}  ·  ${data.education.period}  ·  ${data.education.cgpa}", color = Muted, fontSize = 14.sp)
            Spacer(Modifier.height(16.dp))
            data.achievements.forEach { Text("${it.title} — ${it.description}", color = Muted, fontSize = 14.sp, lineHeight = 22.sp) }
        }
        Section("Let’s build something useful.", "CONTACT") {
            Text(data.contact.email, color = Accent, fontSize = 18.sp)
            Spacer(Modifier.height(8.dp))
            Text("${data.contact.location}  ·  GitHub: ${data.contact.githubUrl}", color = Muted, fontSize = 13.sp)
        }
        Text("© Shubhr Modh  ·  Android Developer | Kotlin | Kotlin Multiplatform", Modifier.padding(24.dp), color = Muted, fontSize = 12.sp)
    }
}

@Composable
private fun Section(title: String, eyebrow: String, content: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 38.dp)) {
        Text(eyebrow, color = Accent, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(12.dp))
        Text(title, color = Ink, fontSize = 30.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(20.dp))
        content()
    }
}
