---
title: Technical Requirements & Dependencies
description: "Hardware requirements, dependencies, permissions, and services used by Pixel Create"
order: 55
---

## Technical Requirements & Dependencies

This document outlines the technical requirements, third-party libraries, permissions, and external services required to build and run the Pixel Create application.

---

## Tested Environments

### Android API Versions

The application has been tested on the following Android API levels:

- **API 30** (Android 11) - Minimum supported version
- **API 33** (Android 13) - Tested on emulator
- **API 34** (Android 14) - Tested on emulator
- **API 36** (Android 15) - Target SDK version, tested on emulator

### Hardware Tested

- **Emulator**: Pixel 5 API 30, Pixel 7 API 33, Pixel 8 API 34 (all x86_64 system images)
- **Physical Devices**: Not yet tested on physical hardware

### Minimum Requirements

- **Minimum SDK**: API 30 (Android 11)
- **Target SDK**: API 36 (Android 15)
- **RAM**: 2GB minimum (4GB recommended for smooth performance)
- **Storage**: 50MB for app installation, additional space for saved projects
- **Screen Resolution**: 1080x1920 (FHD) or higher recommended for best experience

---

## Build Requirements

### Development Environment

- **Java Development Kit (JDK)**: Version 17 or higher
- **Android Studio**: Arctic Fox (2020.3.1) or later (Iguana recommended)
- **Gradle**: Version 9.3.1 (included via Gradle Wrapper)
- **Kotlin**: Version 2.1.0 (configured in build files)

### Build Tools

- **Android Gradle Plugin**: Version 8.8.0
- **R8/D8**: For code shrinking and dexing
- **AAPT2**: For resource compilation

---

## Third-Party Libraries

The following external libraries are used in the Pixel Create application (beyond the Android standard and support libraries):

### Dependency Injection

- **Hilt (Dagger)** - Version 2.52
  - `com.google.dagger:hilt-android`
  - `com.google.dagger:hilt-compiler` (annotation processor)
  - Purpose: Dependency injection for repositories, services, and view models
  - License: Apache 2.0

### Database

- **Room Persistence Library** - Version 2.7.0-alpha12
  - `androidx.room:room-runtime`
  - `androidx.room:room-compiler` (annotation processor)
  - Purpose: SQLite database abstraction layer for local data persistence
  - License: Apache 2.0

### JSON Serialization

- **Gson** - Version 2.11.0
  - `com.google.code.gson:gson`
  - Purpose: JSON parsing and serialization (for autosave snapshots and data export)
  - License: Apache 2.0

### Authentication

- **Google Play Services - Auth** - Version 21.2.0
  - `com.google.android.gms:play-services-auth`
  - Purpose: Google Sign-In authentication
  - License: Google Play Services Terms of Service

### Networking (for future use)

- **Retrofit** - Version 2.11.0
  - `com.squareup.retrofit2:retrofit`
  - `com.squareup.retrofit2:converter-gson`
  - Purpose: REST API client (planned for cloud sync features)
  - License: Apache 2.0

- **OkHttp Logging Interceptor** - Version 4.12.0
  - `com.squareup.okhttp3:logging-interceptor`
  - Purpose: HTTP request/response logging for debugging
  - License: Apache 2.0

### AndroidX Libraries

- `androidx.core:core-ktx` - Kotlin extensions for Android APIs
- `androidx.appcompat:appcompat` - Backward compatibility for newer Android features
- `androidx.activity:activity` - Activity APIs and lifecycle management
- `androidx.fragment:fragment` - Fragment APIs and lifecycle management
- `androidx.constraintlayout:constraintlayout` - Flexible layout system
- `androidx.recyclerview:recyclerview` - Efficient list/grid views
- `androidx.navigation:navigation-fragment` - Navigation Component
- `androidx.navigation:navigation-ui` - Navigation UI helpers
- `androidx.lifecycle:lifecycle-viewmodel` - ViewModel architecture component
- `androidx.lifecycle:lifecycle-livedata` - LiveData reactive data holder
- `androidx.preference:preference` - Settings/preferences UI
- `com.google.android.material:material` - Material Design components

### Testing Libraries

- **JUnit 5** - Version 5.11.4
  - `org.junit.jupiter:junit-jupiter-api`
  - `org.junit.jupiter:junit-jupiter-params`
  - `org.junit.jupiter:junit-jupiter-engine`
  - Purpose: Unit testing framework

- **Espresso** - Version 3.6.1
  - `androidx.test.espresso:espresso-core`
  - Purpose: UI testing framework for Android

- **AndroidX Test** - Version 1.6.1
  - `androidx.test:runner`
  - Purpose: Test runner for instrumented tests

### Desugaring

- **Android Desugar JDK Libs** - Version 2.1.4
  - Purpose: Backport Java 8+ APIs to older Android versions
  - License: Apache 2.0

---

## Android Permissions

### Declared Permissions

The application declares the following permissions in `AndroidManifest.xml`:

#### Safe Permissions (No User Prompt Required)

1. **`android.permission.INTERNET`**
   - **Purpose**: Used for Google Sign-In authentication and future cloud sync features
   - **Impact if Unavailable**: Google Sign-In will not work; app cannot authenticate users
   - **Can App Run Without It?**: No - authentication is required for app functionality

#### Dangerous Permissions (Require User Opt-In)

The application does not currently request any dangerous permissions. The following permissions are planned for future features:

2. **`android.permission.READ_MEDIA_IMAGES`** (API 33+) / **`READ_EXTERNAL_STORAGE`** (API 30-32)
   - **Purpose**: Planned for importing images from device storage as reference layers
   - **Status**: Not yet implemented
   - **Impact if Unavailable**: Users cannot import external images; drawing and project management remain functional
   - **Can App Run Without It?**: Yes - this is a stretch goal feature

3. **`android.permission.POST_NOTIFICATIONS`** (API 33+)
   - **Purpose**: Planned for autosave notifications and export completion alerts
   - **Status**: Not yet implemented
   - **Impact if Unavailable**: Users won't receive visual notifications for background operations
   - **Can App Run Without It?**: Yes - all core functionality works without notifications

4. **`android.permission.CAMERA`**
   - **Purpose**: Planned for photo capture and pixelation feature
   - **Status**: Not yet implemented
   - **Impact if Unavailable**: Camera-based features disabled; core drawing functionality unaffected
   - **Can App Run Without It?**: Yes - this is a stretch goal feature

---

## External Services

### Google Services

1. **Google Sign-In (OAuth 2.0)**
   - **Purpose**: User authentication
   - **API**: `com.google.android.gms.auth.api.signin`
   - **Configuration Required**: OAuth 2.0 Client ID from Google Cloud Console
   - **Impact if Unavailable**: App cannot function without authentication
   - **Documentation**: [Google Sign-In for Android](https://developers.google.com/identity/sign-in/android)

### Device Services

2. **SQLite Database**
   - **Purpose**: Local data storage for projects, layers, pixels, palettes, and user settings
   - **Implementation**: Room Persistence Library
   - **Impact if Unavailable**: App cannot function - all data persistence depends on this
   - **Documentation**: [Room Persistence Library](https://developer.android.com/training/data-storage/room)

3. **SharedPreferences**
   - **Purpose**: Store user settings (canvas size, grid visibility, theme preference)
   - **Implementation**: Android SharedPreferences API via PreferenceFragmentCompat
   - **Impact if Unavailable**: User preferences will not persist; default values will be used
   - **Documentation**: [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences)

### Planned External Services (Not Yet Implemented)

4. **MediaStore (External Storage)**
   - **Purpose**: Export PNG files to device gallery
   - **Impact**: Users cannot save artwork to gallery; projects remain accessible within app

5. **Google Drive API** (Stretch Goal)
   - **Purpose**: Cloud backup and cross-device sync
   - **Impact**: Users limited to local storage only

6. **CameraX** (Stretch Goal)
   - **Purpose**: Photo capture for pixelation feature
   - **Impact**: Camera-based features unavailable

---

## Device Restrictions

### Orientation

- **Supported**: Portrait and landscape
- **Optimized For**: Portrait mode
- **Restriction**: None - the app should adapt to both orientations

### Language

- **Default Language**: English (en-US)
- **Localization**: Not yet implemented
- **Restriction**: UI text is in English only

### Screen Size

- **Minimum**: Small (320dp width)
- **Recommended**: Normal or larger (360dp+ width)
- **Restriction**: Tablet layouts are not optimized; app designed for phone screens

### Hardware Features

- **Required**: Touchscreen
- **Optional**: None currently; future features may use camera or GPS

---

## Network Requirements

- **Internet Connection**: Required for initial Google Sign-In authentication
- **Offline Mode**: Most features (drawing, saving, loading projects) work offline after authentication
- **Background Data**: Not used (no sync or background services currently implemented)

---

## Storage Requirements

- **App Installation**: ~20-30 MB
- **User Data**: Variable based on number and size of projects
  - Small project (32x32 canvas): ~10-50 KB
  - Large project (128x128 canvas): ~500 KB - 2 MB (depending on pixel density)
- **Cache**: Minimal (thumbnail previews)
- **Estimated Total**: 50-100 MB for typical usage

---

## Performance Notes

- **Minimum RAM**: 2 GB (app may experience slowdowns with large canvases on low-memory devices)
- **Recommended RAM**: 4 GB or higher
- **GPU**: Not required, but hardware acceleration improves canvas rendering performance
- **Battery Impact**: Low during normal use; medium during extended drawing sessions

---

## Known Limitations

1. **Emulator Only**: App has not been tested on physical devices
2. **Large Canvas Performance**: Canvases larger than 64x64 pixels have not been performance-tested
3. **Memory Management**: No optimization for large projects; potential out-of-memory issues with many layers or large canvases
4. **Kotlin Metadata Warnings**: Build produces non-critical D8 warnings about Kotlin metadata rewriting (does not affect functionality)

---

## Future Requirements (Planned Features)

The following requirements are anticipated for planned stretch goal features:

- **Photo Picker API**: For importing images without storage permissions (API 33+)
- **Vibration/Haptics**: For tactile feedback on drawing actions
- **Google Drive API**: For cloud sync functionality
- **Firebase**: For analytics and crash reporting (optional)

---

## Related Documentation

- [Build Instructions](build-instructions.md) - Step-by-step build guide
- [Current State](current-state.md) - Implementation status and known issues
- [User Instructions](user-instructions.md) - How to use the app
- [Entity Classes](entities.md) - Database schema and entity documentation
