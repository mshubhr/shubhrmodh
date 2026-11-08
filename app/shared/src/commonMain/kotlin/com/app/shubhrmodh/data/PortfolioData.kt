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
        title = "Android Developer",
        headline = "Crafting high-performance Android & Kotlin Multiplatform experiences",
        email = "modhshubhr53@gmail.com",
        phone = "+91 8734854335",
        location = "Surat, Gujarat, India",
        portfolioUrl = "https://portfolio-mshubhr.vercel.app/",
        githubUrl = "https://github.com/mshubhr",
        linkedinUrl = "https://www.linkedin.com/in/shubhr-modh-05b232222",
        gitlabUrl = "https://gitlab.com/mshubhr",
        mediumUrl = "https://medium.com/@mshubhr",
        twitterUrl = "https://x.com/ShubhrM",
        summary = "Android Developer with 2+ years of experience building Android applications using Kotlin, Jetpack Compose, Java, and REST APIs. Experienced in developing and publishing Android applications on the Google Play Store. Passionate about building reliable, scalable applications with a focus on performance, clean architecture, and excellent user experience."
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
                "Developed a production Android application from scratch using Kotlin and MVVM architecture.",
                "Implemented sticky notifications with customized compact & expanded layouts to improve notification presentation.",
                "Developed reusable XML-based UI components, including gradient progress bars, MotionLayout animations, and iOS-inspired bottom navigation with smooth transitions.",
                "Implemented Android system-level features for a utility application, including overlay and alarm permissions, full-screen intents, Wake Locks, and runtime permissions.",
                "Used Git and GitLab for version control and actively participated in code reviews and team collaboration."
            ),
            tags = listOf(
                "Kotlin",
                "MVVM",
                "Sticky Notifications",
                "MotionLayout",
                "System Features",
                "GitLab"
            )
        ), ExperienceItem(
            company = "Differenz System Pvt Ltd",
            role = "Junior Software Engineer",
            period = "July '24 – Aug '26",
            location = "Surat, Gujarat",
            bullets = listOf(
                "Worked on Agilis, a dating application featuring match-based chat and real-time calling.",
                "Implemented Firebase Cloud Messaging (FCM) notifications and real-time audio/video calling using Agora SDK.",
                "Integrated AWS Rekognition for secure user profile verification and biometric safeguards.",
                "Improved application startup time by 20% and reduced production crash rates by 30%.",
                "Integrated Google Maps, Places, and Directions APIs to enable robust location-based functionality.",
                "Resolved Android SDK 35 compatibility issues, including 16 KB page-size compatibility, R8-related warnings, and deprecated APIs.",
                "Resolved critical issues across 5+ Android applications involving Room Database, ExoPlayer, WebView integrations (YouTube, MS Word), and third-party SDKs."
            ),
            tags = listOf(
                "Kotlin",
                "Agora SDK",
                "FCM",
                "AWS Rekognition",
                "Google Maps",
                "SDK 35",
                "16KB Page-Size",
                "Room",
                "ExoPlayer"
            )
        )
    )

    val achievements = listOf(
        AchievementItem(
            title = "Grand Finale Winner",
            project = "Parivartan — NFT Marketplace for Cultural Artifacts",
            description = "Won the grand finale of Azadi ka Amrit Mahotsav Hackathon 2022. Selected among 2000+ teams across Gujarat and funded under the SSIP 2.0 (Student Startup and Innovation Policy) by the Government of Gujarat.",
            tag = "SSIP 2.0 Funded"
        )
    )

    val projects = listOf(
        ProjectItem(
            name = "ShopFlow",
            date = "Nov 2026",
            subtitle = "Modern E-Commerce Mobile App",
            bullets = listOf(
                "Developed a Kotlin-based e-commerce app using Jetpack Compose, MVVM, Navigation 3, and Material 3.",
                "Built a custom REST API using Ktor and integrated it with the Android app using Kotlin Coroutines and Flow.",
                "Implemented offline-first product catalog and persistent shopping cart using Room, sorting, and pagination."
            ),
            tags = listOf(
                "Kotlin", "Jetpack Compose", "Navigation 3", "Material 3", "Ktor", "Room", "Flow"
            ),
            githubUrl = "https://github.com/mshubhr"
        ), ProjectItem(
            name = "FieldSync",
            date = "Mar 2026",
            subtitle = "Cross-Platform Networking & Sync Engine",
            bullets = listOf(
                "Developed a cross-platform application using Kotlin Multiplatform (KMP) with a shared networking layer and business logic synchronization using Ktor.",
                "Achieved over 85% code reuse across Android and iOS platforms.",
                "Implemented RESTful backend services for OTP authentication (Twilio/Firebase), offline-first caching, and device registration, ensuring secure, scalable communication."
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
        ), ProjectItem(
            name = "Clink",
            date = "Jan 2026",
            subtitle = "High-Throughput Mobile App with FastAPI",
            bullets = listOf(
                "Designed the Flutter UI in Dart and initially integrated APIs using Flask.",
                "Migrated the backend architecture to FastAPI for superior performance, concurrency, and scalability."
            ),
            tags = listOf("Flutter", "Dart", "Python", "FastAPI", "REST APIs"),
            githubUrl = "https://github.com/mshubhr"
        ), ProjectItem(
            name = "Parivartan",
            date = "Hackathon Winner",
            subtitle = "Cultural Artifacts NFT Marketplace",
            bullets = listOf(
                "Grand finale winner in Azadi ka Amrit Mahotsav Hackathon among 2000+ teams.",
                "Funded under Gujarat Government Student Startup and Innovation Policy (SSIP 2.0)."
            ),
            tags = listOf("Android", "Web3", "Cultural Preservation", "SSIP 2.0"),
            githubUrl = "https://github.com/mshubhr"
        )
    )

    val skillCategories = listOf(
        SkillCategory(
            title = "Android & Mobile", skills = listOf(
                "Kotlin",
                "Java",
                "Android SDK",
                "Jetpack Compose",
                "XML Layouts",
                "MVVM",
                "ViewModel",
                "LiveData",
                "Room Database",
                "WorkManager",
                "Hilt / DI",
                "Flutter & Dart"
            )
        ), SkillCategory(
            title = "Architecture & Reactive", skills = listOf(
                "Clean Architecture",
                "Kotlin Multiplatform (KMP)",
                "Compose Multiplatform",
                "Coroutines & Flow",
                "Retrofit",
                "Ktor Client/Server",
                "RESTful APIs",
                "Dependency Injection",
                "Offline-First Design"
            )
        ), SkillCategory(
            title = "SDKs & Cloud Services", skills = listOf(
                "Agora RTC SDK",
                "Firebase (FCM, Auth, DB)",
                "AWS Rekognition",
                "Google Maps & Places",
                "ExoPlayer / Media3",
                "MotionLayout"
            )
        ), SkillCategory(
            title = "Testing, Performance & Tools", skills = listOf(
                "JUnit & Espresso",
                "16 KB Page-Size Migration",
                "Android SDK 35 Compatibility",
                "R8 / ProGuard Optimization",
                "Git & GitLab",
                "Gradle Kotlin DSL",
                "Python & FastAPI",
                "Go"
            )
        )
    )
}
