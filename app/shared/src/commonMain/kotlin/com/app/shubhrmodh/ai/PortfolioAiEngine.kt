package com.app.shubhrmodh.ai

import com.app.shubhrmodh.data.PortfolioRepository

data class AiChatMessage(
    val id: String,
    val sender: AiSender,
    val text: String,
    val timestampMs: Long = 0,
    val actionLabel: String? = null,
    val actionTarget: AiActionTarget? = null
)

enum class AiSender {
    User, Assistant
}

enum class AiActionTarget {
    Projects, Experience, Skills, Contact
}

object PortfolioAiEngine {
    private val contact = PortfolioRepository.contact
    private val projects = PortfolioRepository.projects
    private val experiences = PortfolioRepository.experiences
    private val skillCategories = PortfolioRepository.skillCategories

    fun getInitialGreeting(): AiChatMessage {
        return AiChatMessage(
            id = "greeting",
            sender = AiSender.Assistant,
            text = "Hi! I'm Shubhr's AI Assistant. I can answer questions about his experience, technical stack, projects, education, or contact details. What would you like to know?"
        )
    }

    fun generateResponse(userPrompt: String): AiChatMessage {
        val query = userPrompt.trim().lowercase()

        val (responseText, actionLabel, actionTarget) = when {
            query.contains("experience") || query.contains("job") || query.contains("work") || query.contains("company") || query.contains("career") || query.contains("history") -> {
                val expSummary = experiences.joinToString("\n• ") { "${it.role} at ${it.company} (${it.period})" }
                Triple(
                    "Shubhr has over 3 years of Android and Kotlin development experience:\n\n• $expSummary\n\nHe specializes in building high-performance mobile apps using Jetpack Compose, Kotlin Multiplatform, and Clean Architecture.",
                    "View Full Experience →",
                    AiActionTarget.Experience
                )
            }

            query.contains("project") || query.contains("fieldsync") || query.contains("shopflow") || query.contains("app") || query.contains("built") -> {
                val projectNames = projects.joinToString(", ") { it.name }
                Triple(
                    "Shubhr has authored several production and open-source projects including: $projectNames.\n\nKey highlights:\n• FieldSync: Offline-first Kotlin Multiplatform field ops suite with custom sync engine.\n• ShopFlow: E-commerce reference application showcasing Navigation 3 & MVI architecture.",
                    "Explore Projects →",
                    AiActionTarget.Projects
                )
            }

            query.contains("skill") || query.contains("tech") || query.contains("stack") || query.contains("kotlin") || query.contains("compose") || query.contains("architecture") || query.contains("language") -> {
                val topSkills = skillCategories.flatMap { it.skills }.take(8).joinToString(", ")
                Triple(
                    "Shubhr's core technical stack includes:\n\n• Top Skills: $topSkills\n• Languages & Core: Kotlin, Java, Coroutines, Flow, SQL\n• UI Frameworks: Jetpack Compose, Compose Multiplatform, Material 3\n• Architecture: MVVM, MVI, Clean Architecture, Dependency Injection\n• Networking & DB: Ktor, Room, SQLite, Retrofit\n• Specialized: On-device ML (LiteRT/ML Kit), Media3/ExoPlayer, Performance Profiling",
                    "View Skills Matrix →",
                    AiActionTarget.Skills
                )
            }

            query.contains("contact") || query.contains("email") || query.contains("reach") || query.contains("hire") || query.contains("interview") || query.contains("recruiter") || query.contains("phone") -> {
                Triple(
                    "You can reach Shubhr directly via:\n\n• Email: ${contact.email}\n• Location: ${contact.location}\n• Status: ${contact.headline}\n\nHe is available for full-time Senior Android and Kotlin Multiplatform engineering roles.",
                    "Get in Touch →",
                    AiActionTarget.Contact
                )
            }

            query.contains("education") || query.contains("degree") || query.contains("college") || query.contains("university") || query.contains("cgpa") -> {
                val edu = PortfolioRepository.education
                Triple(
                    "Shubhr holds a ${edu.degree} from ${edu.institution} (${edu.period}) with a CGPA of ${edu.cgpa}.",
                    "View About & Education →",
                    AiActionTarget.Contact
                )
            }

            query.contains("achievement") || query.contains("award") || query.contains("hackathon") || query.contains("ssip") -> {
                val ach = PortfolioRepository.achievements.firstOrNull()
                val desc = ach?.title ?: "SSIP 2.0 Grand Finale Finalist"
                Triple(
                    "One of Shubhr's key achievements is being an $desc — placing in the top teams out of 2000+ participating engineering teams nationwide.",
                    "View Achievements →",
                    AiActionTarget.Experience
                )
            }

            query.contains("who") || query.contains("about") || query.contains("shubhr") || query.contains("bio") -> {
                Triple(
                    "Shubhr Modh is a Senior Android & Kotlin Multiplatform Engineer focused on building fast, scalable mobile applications with thoughtful user experiences and solid engineering foundations.",
                    "Explore Work →",
                    AiActionTarget.Projects
                )
            }

            else -> {
                Triple(
                    "Shubhr Modh is an experienced Android & KMP Engineer skilled in Kotlin, Jetpack Compose, Navigation 3, and Clean Architecture. Feel free to ask about his experience, skills, projects, or contact details!",
                    "Explore Portfolio →",
                    AiActionTarget.Projects
                )
            }
        }

        return AiChatMessage(
            id = "msg_${userPrompt.hashCode()}",
            sender = AiSender.Assistant,
            text = responseText,
            timestampMs = 0L,
            actionLabel = actionLabel,
            actionTarget = actionTarget
        )
    }
}
