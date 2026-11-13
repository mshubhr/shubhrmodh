# Shubhr Modh — Android & Kotlin Multiplatform Portfolio

A modern, high-performance portfolio application built with **Kotlin Multiplatform** and **Compose Multiplatform**. It features a dual-experience interface: an **Editorial Web Portfolio** for desktop and mobile web visitors, and an interactive **Android OS Simulator Launcher** showcasing native Android engineering concepts, Navigation 3 architecture, and media engines.

**Live Web Portfolio:** [https://portfolio-mshubhr.vercel.app/](https://portfolio-mshubhr.vercel.app/)

---

## 🛠 Tech Stack & Architecture

- **Language:** Kotlin 2.x
- **UI Framework:** Compose Multiplatform (WASM, JS & Android)
- **Design System:** Material 3 with custom monochrome & dark color tokens
- **Navigation:** Jetpack Navigation 3 pattern (`NavDisplay` & `rememberNav3BackStack`)
- **Backend / Networking:** Ktor Server & Ktor Client
- **Database / Caching:** Room Database & Flow
- **Build System:** Gradle Kotlin DSL with Version Catalogs (`libs.versions.toml`)

### Module Structure

```
├── app/
│   ├── androidApp/   # Native Android Application target
│   ├── shared/       # Shared Compose Multiplatform UI & portfolio domain models
│   └── webApp/       # Web entry point (Kotlin/Wasm & Kotlin/JS)
├── core/             # Shared foundational models and utilities
└── server/           # Ktor backend server
```

---

## 🚀 Running the Project

### Web Application (Kotlin/Wasm & Kotlin/JS)
Run the WebAssembly target (recommended for modern browsers):
```bash
./gradlew :app:webApp:wasmJsBrowserDevelopmentRun
```
Run the JavaScript target:
```bash
./gradlew :app:webApp:jsBrowserDevelopmentRun
```

### Android Application
Build and install the debug APK on a connected device or emulator:
```bash
./gradlew :app:androidApp:assembleDebug
```

### Ktor Backend Server
Run the local Ktor server:
```bash
./gradlew :server:run
```

---

## 📦 Deployment Configuration

The WebAssembly distribution bundle is generated via:
```bash
./gradlew :app:webApp:wasmJsBrowserDistribution
```
The production output directory is `app/webApp/build/dist/wasmJs/productionExecutable`, configured for continuous deployment on Vercel and GitHub Actions.

---

## 👤 Contact & Links

- **Name:** Shubhr Modh
- **Role:** Android Developer & KMP Specialist
- **Location:** Surat, Gujarat, India
- **GitHub:** [https://github.com/mshubhr](https://github.com/mshubhr)
- **LinkedIn:** [https://www.linkedin.com/in/shubhr-modh-05b232222](https://www.linkedin.com/in/shubhr-modh-05b232222)
- **Email:** [modhshubhr53@gmail.com](mailto:modhshubhr53@gmail.com)
