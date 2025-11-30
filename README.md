# Kairos - AI Interview Prep App

An Android application that generates personalized interview preparation plans using AI. Built with Jetpack Compose and Kotlin, Kairos helps job seekers create structured learning paths tailored to specific job descriptions and timelines.

## 📱 Features

- **AI-Powered Career Planning**: Generate customized interview prep plans based on job descriptions
- **Category-Based Learning**: Support for multiple tech categories (Android, Backend, Web3, etc.)
- **Timeline Management**: Create study plans based on your available preparation time
- **Progress Tracking**: Visual progress indicators with checkboxes to track completed topics
- **Priority-Based Topics**: Topics organized by priority (High, Medium, Low) for efficient learning
- **Modern UI**: Built with Material Design 3 and Jetpack Compose for a smooth user experience

## 🏗️ Architecture

This project follows **Clean Architecture** principles with a modular **MVVM (Model-View-ViewModel)** pattern, ensuring separation of concerns, testability, and scalability.

### Architecture Layers

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (UI, ViewModels, Navigation)           │
├─────────────────────────────────────────┤
│          Domain Layer                   │
│  (Use Cases, Business Logic)            │
├─────────────────────────────────────────┤
│           Data Layer                    │
│  (Repository, API, Models)              │
└─────────────────────────────────────────┘
```

### Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture Pattern**: MVVM + Clean Architecture
- **Navigation**: Navigation Compose
- **State Management**: StateFlow & Compose State
- **Networking**: Retrofit 2 + OkHttp3
- **Serialization**: Gson
- **Async Operations**: Kotlin Coroutines
- **Minimum SDK**: 29 (Android 10)
- **Target SDK**: 36

### Project Structure

```
com.aiwithab.kairos/
├── data/                                    # Data Layer
│   ├── model/
│   │   ├── request/
│   │   │   └── PlanRequest.kt              # API request models
│   │   └── response/
│   │       ├── PlanResponse.kt             # API response models
│   │       ├── Skill.kt
│   │       └── Topic.kt
│   ├── remote/
│   │   ├── ApiService.kt                   # Retrofit API interface
│   │   └── NetworkModule.kt                # Network configuration
│   └── repository/
│       └── PlanRepositoryImpl.kt           # Repository implementation
│
├── domain/                                  # Domain Layer
│   ├── model/
│   │   ├── PlanDomain.kt                   # Domain models (UI-agnostic)
│   │   ├── SkillDomain.kt
│   │   └── TopicDomain.kt
│   ├── repository/
│   │   └── PlanRepository.kt               # Repository interface
│   └── usecase/
│       └── GeneratePlanUseCase.kt          # Business logic
│
├── presentation/                            # Presentation Layer
│   ├── input/
│   │   ├── InputScreen.kt                  # Input UI screen
│   │   ├── InputViewModel.kt               # Input screen ViewModel
│   │   └── InputUiState.kt                 # Input screen state
│   ├── output/
│   │   ├── OutputScreen.kt                 # Output UI screen
│   │   ├── OutputViewModel.kt              # Output screen ViewModel
│   │   └── OutputUiState.kt                # Output screen state
│   ├── components/
│   │   ├── TopicCard.kt                    # Reusable UI components
│   │   ├── ProgressHeader.kt
│   │   └── LoadingIndicator.kt
│   └── navigation/
│       └── NavGraph.kt                     # Navigation setup
│
├── ui/theme/                                # Theme configuration
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
├── util/                                    # Utilities
│   ├── Constants.kt                        # App constants
│   ├── Result.kt                           # Result wrapper
│   └── Extensions.kt                       # Extension functions
│
└── MainActivity.kt                          # App entry point
```

### Key Components

#### Data Layer
- **Models**: DTOs for API communication with proper serialization
- **ApiService**: Retrofit interface defining API endpoints
- **NetworkModule**: Singleton providing configured Retrofit and OkHttp instances
- **PlanRepositoryImpl**: Implements repository pattern, handles API calls and data-to-domain mapping

#### Domain Layer
- **Domain Models**: Business entities with computed properties and helper methods
- **PlanRepository**: Interface defining data operations (dependency inversion)
- **GeneratePlanUseCase**: Encapsulates business logic, input validation, and orchestrates repository calls

#### Presentation Layer
- **ViewModels**: Manage UI state using StateFlow, handle user interactions
- **UI States**: Sealed classes representing different screen states (Idle, Loading, Success, Error)
- **Screens**: Composable functions for UI rendering
- **Components**: Reusable UI elements (TopicCard, ProgressHeader, LoadingIndicator)
- **Navigation**: NavGraph manages screen navigation and dependency injection

### Design Principles

✅ **Separation of Concerns**: Each layer has a single, well-defined responsibility  
✅ **Dependency Inversion**: Layers depend on abstractions, not implementations  
✅ **Single Responsibility**: Each class has one reason to change  
✅ **Testability**: Business logic isolated from Android framework  
✅ **Scalability**: Easy to add new features without modifying existing code  
✅ **Maintainability**: Clear structure makes code easy to understand and modify

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Android SDK with API level 36
- Gradle 8.x

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/aiwithab/kairos-app.git
   cd kairos-app
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory and select it

3. **Configure Backend URL**
   
   Update the `BASE_URL` in `MainActivity.kt` to point to your backend server:
   ```kotlin
   private const val BASE_URL = "http://YOUR_SERVER_IP:8000"
   ```
   
   > **Note**: For Android Emulator, use `10.0.2.2` to access localhost on your development machine. For physical devices, use your computer's local IP address.

4. **Sync Gradle**
   - Android Studio should automatically sync Gradle
   - If not, click "Sync Project with Gradle Files" in the toolbar

5. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`

## 🔧 Configuration

### Network Configuration

The app requires internet access and uses cleartext traffic for local development. This is configured in `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<application android:usesCleartextTraffic="true">
```

> **⚠️ Security Warning**: For production builds, remove `android:usesCleartextTraffic="true"` and use HTTPS endpoints only.

### Timeout Settings

Network timeouts are configured in `MainActivity.kt`:
- Connect timeout: 60 seconds
- Read timeout: 60 seconds
- Write timeout: 60 seconds
- Call timeout: 60 seconds

Adjust these values based on your backend response times.

## 📦 Dependencies

### Core Dependencies
- `androidx.core:core-ktx` - AndroidX Core KTX extensions
- `androidx.lifecycle:lifecycle-runtime-ktx` - Lifecycle components
- `androidx.activity:activity-compose` - Activity Compose integration

### Compose Dependencies
- `androidx.compose.ui` - Compose UI toolkit
- `androidx.compose.material3` - Material Design 3 components
- `androidx.compose.material3:adaptive-navigation-suite` - Adaptive navigation
- `androidx.compose.material3:window-size-class` - Window size classes

### Networking
- `com.squareup.retrofit2:retrofit` - HTTP client
- `com.squareup.retrofit2:converter-gson` - Gson converter for Retrofit
- `com.squareup.retrofit2:converter-moshi` - Moshi converter (alternative)
- `com.squareup.okhttp3:logging-interceptor` - HTTP logging

### Navigation
- `androidx.navigation:navigation-compose` - Navigation Compose for screen routing

### ViewModel
- `androidx.lifecycle:lifecycle-viewmodel-compose` - ViewModel integration with Compose
- `androidx.lifecycle:lifecycle-runtime-compose` - Lifecycle runtime for Compose

### Coroutines
- `org.jetbrains.kotlinx:kotlinx-coroutines-android` - Coroutines for Android

### Testing
- `junit:junit` - Unit testing framework
- `androidx.test.ext:junit` - AndroidX JUnit extensions
- `androidx.test.espresso:espresso-core` - UI testing
- `androidx.compose.ui:ui-test-junit4` - Compose testing

## 🎯 Usage

1. **Launch the app** on your Android device or emulator

2. **Enter job details**:
   - **Category**: Specify the tech domain (e.g., "Android", "Backend", "Web3")
   - **Job Description**: Paste the complete job description
   - **Timeline**: Enter the number of days available for preparation

3. **Generate Plan**: Tap the "Generate Plan" button

4. **Review and Track Progress**:
   - View the generated topics organized by skills
   - Each topic shows priority level and timeline
   - Check off completed topics to track progress
   - Monitor overall completion percentage with the progress bar

## 🔌 Backend Integration

This app requires a backend service that provides the `/career-plan` endpoint. The expected API contract:

### Request
```json
{
  "user_id": "123",
  "category": "Android",
  "job_description": "Looking for an Android developer with...",
  "timeline": "30"
}
```

### Response
```json
{
  "skills": [
    {
      "skill_name": "Kotlin Programming",
      "total_days": 10,
      "topics": [
        {
          "topic_name": "Coroutines",
          "study_material": "Official Kotlin docs",
          "timeline": "3 days",
          "priority": "High",
          "bonus": false
        }
      ]
    }
  ]
}
```

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Run All Tests
```bash
./gradlew check
```

## 🔨 Building

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

> **Note**: Configure signing keys in `app/build.gradle.kts` for release builds.

## 📝 Code Style

This project follows the official Kotlin coding conventions:
- Kotlin code style: `official` (configured in `gradle.properties`)
- 4 spaces for indentation
- Maximum line length: 120 characters

## 🐛 Troubleshooting

### Common Issues

**1. Network Connection Failed**
- Verify the backend server is running
- Check the `BASE_URL` configuration
- Ensure the device/emulator has internet access
- For emulator: Use `10.0.2.2` instead of `localhost`
- For physical device: Ensure both device and server are on the same network

**2. Build Errors**
- Clean and rebuild: `Build > Clean Project` then `Build > Rebuild Project`
- Invalidate caches: `File > Invalidate Caches / Restart`
- Ensure you're using JDK 11 or higher

**3. Compose Preview Not Working**
- Update Android Studio to the latest version
- Sync Gradle files
- Check that `@Preview` annotations are properly configured

## 🚧 Future Enhancements

### Features
- [ ] User authentication and profile management
- [ ] Offline mode with local caching (Room database)
- [ ] Calendar integration for scheduling study sessions
- [ ] Reminders and notifications
- [ ] Study material recommendations with links
- [ ] Progress analytics and insights
- [ ] Export plan as PDF
- [ ] Social sharing features

### Architecture Improvements
- [ ] Dependency Injection with Hilt/Koin
- [ ] Multi-module architecture (app, data, domain, presentation)
- [ ] Comprehensive unit and integration tests
- [ ] UI tests with Compose Testing
- [ ] CI/CD pipeline setup
- [ ] ProGuard/R8 optimization for release builds
- [ ] BuildConfig variants for dev/staging/prod environments

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 Contact

For questions or feedback, please open an issue on GitHub.

## 🙏 Acknowledgments

- Built with [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Networking powered by [Retrofit](https://square.github.io/retrofit/)
- Material Design 3 guidelines from [Google](https://m3.material.io/)
