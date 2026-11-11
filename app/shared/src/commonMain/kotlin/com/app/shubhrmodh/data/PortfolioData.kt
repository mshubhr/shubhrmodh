package com.app.shubhrmodh.data

data class ContactInfo(
    val name: String,
    val title: String,
    val headline: String,
    val email: String,
    val phone: String,
    val location: String,
    val portfolioUrl: String,
    val githubUrl: String,
    val linkedinUrl: String,
    val gitlabUrl: String,
    val mediumUrl: String,
    val twitterUrl: String,
    val summary: String
)

data class ExperienceItem(
    val company: String,
    val role: String,
    val period: String,
    val location: String,
    val bullets: List<String>,
    val tags: List<String>
)

data class ProjectItem(
    val name: String,
    val date: String,
    val subtitle: String,
    val bullets: List<String>,
    val tags: List<String>,
    val githubUrl: String
)

data class SkillCategory(
    val title: String, val skills: List<String>
)

data class AchievementItem(
    val title: String, val project: String, val description: String, val tag: String
)

data class EducationItem(
    val institution: String,
    val degree: String,
    val period: String,
    val cgpa: String,
    val location: String
)

object PortfolioRepository {
    val contact = ContactInfo(
        name = "Shubhr Modh",
        title = "Android Developer & KMP Specialist",
        headline = "Crafting high-performance Android, Navigation 3 & Kotlin Multiplatform experiences",
        email = "modhshubhr53@gmail.com",
        phone = "+91 8734854335",
        location = "Surat, Gujarat, India",
        portfolioUrl = "https://portfolio-mshubhr.vercel.app/",
        githubUrl = "https://github.com/mshubhr",
        linkedinUrl = "https://www.linkedin.com/in/shubhr-modh-05b232222",
        gitlabUrl = "https://gitlab.com/mshubhr",
        mediumUrl = "https://medium.com/@mshubhr",
        twitterUrl = "https://x.com/ShubhrM",
        summary = "Android Developer with 2+ years of hands-on experience building production Android applications using Kotlin, Jetpack Compose, Navigation 3, ExoPlayer Media3, and REST APIs. Experienced in publishing Android apps on Google Play Store with 16KB page-size alignment and SDK 35/37 target compatibility. Passionate about Clean Architecture, MVVM, and high-performance cross-platform KMP engines."
    )

    val education = EducationItem(
        institution = "L. D. College of Engineering",
        degree = "Bachelor of Engineering (B.E.) in Computer Engineering",
        period = "2020 – 2024",
        cgpa = "CGPA: 7.87 / 10",
        location = "Ahmedabad, Gujarat"
    )

    val experiences = listOf(
        ExperienceItem(
            company = "Helsy Infotech Pvt Ltd",
            role = "Android Developer",
            period = "Sep '26 – Present",
            location = "Surat, Gujarat",
            bullets = listOf(
                "Developed production Android apps from scratch using Kotlin, Jetpack Compose, Navigation 3, and MVVM architecture.",
                "Implemented sticky notifications with customized compact & expanded layouts to improve notification delivery.",
                "Built reusable UI components, including MotionLayout collapsing headers, gradient progress indicators, and custom scene transitions.",
                "Implemented Android system-level APIs: overlay permissions, full-screen intents, Wake Locks, and runtime permissions.",
                "Utilized Git and GitLab for version control, code reviews, and continuous team collaboration."
            ),
            tags = listOf(
                "Kotlin",
                "Jetpack Compose",
                "Navigation 3",
                "MVVM",
                "Sticky Notifications",
                "MotionLayout",
                "System Permissions",
                "GitLab"
            )
        ),
        ExperienceItem(
            company = "Differenz System Pvt Ltd",
            role = "Junior Software Engineer",
            period = "July '24 – Aug '26",
            location = "Surat, Gujarat",
            bullets = listOf(
                "Worked on Agilis, a dating application featuring match-based chat and real-time audio/video calling with Agora SDK.",
                "Implemented Firebase Cloud Messaging (FCM) notifications and AWS Rekognition biometric profile verification.",
                "Improved app startup performance by 20% and reduced production crash rates by 30% via R8 rules and profiling.",
                "Integrated Google Maps, Places, and Directions APIs for location-based search and routing.",
                "Resolved Android SDK 35/37 compatibility issues, including 16 KB page-size alignment, R8 warnings, and deprecated APIs.",
                "Fixed critical issues across 5+ production Android apps involving Room Database, ExoPlayer Media3, and WebViews."
            ),
            tags = listOf(
                "Kotlin",
                "Agora RTC",
                "FCM",
                "AWS Rekognition",
                "Google Maps",
                "SDK 35 / 37",
                "16KB Page Alignment",
                "Room",
                "ExoPlayer Media3"
            )
        )
    )

    val achievements = listOf(
        AchievementItem(
            title = "Grand Finale Winner",
            project = "Parivartan — NFT Marketplace for Cultural Artifacts",
            description = "Won the grand finale of Azadi ka Amrit Mahotsav Hackathon 2022 out of 2000+ teams across Gujarat. Funded under SSIP 2.0 (Student Startup and Innovation Policy) by the Government of Gujarat.",
            tag = "SSIP 2.0 Funded"
        )
    )

    val projects = listOf(
        ProjectItem(
            name = "ShopFlow",
            date = "Nov 2026",
            subtitle = "Modern E-Commerce Mobile App",
            bullets = listOf(
                "Developed an e-commerce Android application using Jetpack Compose, Navigation 3, Material 3, and MVVM.",
                "Built a custom REST backend service using Ktor and integrated it with asynchronous Kotlin Coroutines & Flow.",
                "Implemented offline-first product catalog and persistent shopping cart using Room DB, sorting, and pagination."
            ),
            tags = listOf(
                "Kotlin", "Jetpack Compose", "Navigation 3", "Material 3", "Ktor", "Room", "Flow"
            ),
            githubUrl = "https://github.com/mshubhr"
        ),
        ProjectItem(
            name = "FieldSync",
            date = "Mar 2026",
            subtitle = "Cross-Platform Networking & Sync Engine",
            bullets = listOf(
                "Built a cross-platform mobile application using Kotlin Multiplatform (KMP) with a shared networking layer and Ktor sync engine.",
                "Achieved 85%+ code reuse across Android and iOS platforms.",
                "Implemented backend services for OTP authentication (Twilio/Firebase), offline caching, and device registration."
            ),
            tags = listOf(
                "Kotlin Multiplatform",
                "KMP",
                "Ktor",
                "Coroutines",
                "Compose Multiplatform",
                "Firebase"
            ),
            githubUrl = "https://github.com/mshubhr/FieldSync"
        ),
        ProjectItem(
            name = "Clink",
            date = "Jan 2026",
            subtitle = "High-Throughput Mobile App with FastAPI",
            bullets = listOf(
                "Designed the Flutter UI in Dart and initially integrated APIs using Flask.",
                "Migrated backend services to FastAPI for high-throughput concurrency, async processing, and scalability."
            ),
            tags = listOf("Flutter", "Dart", "Python", "FastAPI", "REST APIs"),
            githubUrl = "https://github.com/mshubhr"
        )
    )

    val skillCategories = listOf(
        SkillCategory(
            title = "Modern Android & Jetpack Compose",
            skills = listOf(
                "Kotlin",
                "Java",
                "Android SDK 35/37",
                "Jetpack Compose M3",
                "Navigation 3",
                "MotionLayout",
                "MVVM & MVI",
                "Room Database",
                "WorkManager",
                "Hilt / DI",
                "Flutter & Dart"
            )
        ),
        SkillCategory(
            title = "Architecture & Kotlin Multiplatform",
            skills = listOf(
                "Clean Architecture",
                "Kotlin Multiplatform (KMP)",
                "Compose Multiplatform",
                "Coroutines & Flow",
                "Ktor Client/Server",
                "Retrofit",
                "RESTful APIs",
                "Offline-First Storage"
            )
        ),
        SkillCategory(
            title = "Media, AI & Cloud Services",
            skills = listOf(
                "ExoPlayer Media3",
                "LiteRT & GenAI Agent",
                "AppFunctions API",
                "Agora RTC SDK",
                "Firebase (FCM, Auth, DB)",
                "AWS Rekognition",
                "Google Maps & Places"
            )
        ),
        SkillCategory(
            title = "Performance, 16KB Page & Tools",
            skills = listOf(
                "16 KB Page-Size Migration",
                "Android SDK 35/37 Target",
                "R8 / ProGuard Keep Rules",
                "Perfetto & Profiling",
                "JUnit & Espresso",
                "Git & GitLab",
                "Gradle Kotlin DSL",
                "Python & FastAPI"
            )
        )
    )
}
