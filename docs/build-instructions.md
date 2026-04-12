---
title: Build Instructions
description: "Instructions for building and running the Pixel Create application"
order: 50
---

## Build Instructions

This document provides step-by-step instructions for cloning, building, and running the Pixel Create Android application.

---

## Prerequisites

Before building the project, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 17 or higher
- **Android Studio**: Latest stable version (recommended: Android Studio Iguana or later)
- **Android SDK**: API Level 30 (minimum) through API Level 36 (target)
- **Git**: For cloning the repository

---

## Step 1: Clone the Repository

Clone the project repository from GitHub:

```bash
git clone https://github.com/dd-java-22/pixel-create-DarthVega-The.git
cd pixel-create-DarthVega-The
```

Alternatively, you can download the repository as a ZIP file from GitHub and extract it to your desired location.

---

## Step 2: Configure Google Sign-In (Required)

The application uses Google Sign-In for authentication. You must obtain a Google OAuth 2.0 client ID and configure it before building.

### Obtaining a Client ID

1. Go to the [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Enable the **Google Sign-In API**
4. Navigate to **Credentials** → **Create Credentials** → **OAuth 2.0 Client ID**
5. Select **Android** as the application type
6. Provide the package name: `edu.cnm.deepdive.pixelcreate`
7. Provide the SHA-1 fingerprint of your debug keystore:
   ```bash
   keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
   ```
8. Copy the generated **Client ID**

### Configuring the Client ID

Create or edit the `app/src/main/res/values/secrets.xml` file:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
  <string name="default_web_client_id">YOUR_CLIENT_ID_HERE</string>
</resources>
```

Replace `YOUR_CLIENT_ID_HERE` with your actual OAuth 2.0 Client ID.

**IMPORTANT**: The `secrets.xml` file is excluded from version control (via `.gitignore`) to protect sensitive information. Do not commit this file to the repository.

---

## Step 3: Import the Project into Android Studio

1. Open **Android Studio**
2. Select **File** → **Open**
3. Navigate to the cloned repository directory and select the root folder (`pixel-create-DarthVega-The`)
4. Click **OK** to import the project
5. Android Studio will automatically sync Gradle dependencies (this may take a few minutes)

---

## Step 4: Build the Project

### Using Android Studio

1. Wait for Gradle sync to complete
2. Select **Build** → **Make Project** (or press `Ctrl+F9` / `Cmd+F9`)
3. The build output will appear in the **Build** pane at the bottom of the IDE

### Using the Command Line

Navigate to the project root directory and run:

**Windows:**
```bash
gradlew.bat assembleDebug
```

**macOS/Linux:**
```bash
./gradlew assembleDebug
```

The compiled APK will be located at:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## Step 5: Run the Application

### Using Android Studio

1. Connect an Android device via USB **or** start an Android emulator
   - **Physical Device**: Enable **Developer Options** and **USB Debugging** on your device
   - **Emulator**: Use the **AVD Manager** in Android Studio to create a virtual device (API 30+)
2. Select your target device from the device dropdown in the toolbar
3. Click the **Run** button (green triangle) or press `Shift+F10` / `Ctrl+R`
4. The app will install and launch automatically

### Using the Command Line

Install the APK on a connected device or running emulator:

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

Launch the app manually from the device's app drawer.

---

## Step 6: Running Tests (Optional)

### Unit Tests

Run JUnit tests from the command line:

```bash
./gradlew test
```

### Instrumented Tests

Run Android instrumented tests on a connected device or emulator:

```bash
./gradlew connectedAndroidTest
```

---

## Troubleshooting

### Gradle Sync Fails

- Ensure you have a stable internet connection
- Try **File** → **Invalidate Caches / Restart** in Android Studio
- Check that your JDK version is 17 or higher

### Google Sign-In Not Working

- Verify your `secrets.xml` file contains the correct Client ID
- Ensure the SHA-1 fingerprint matches your keystore
- Check that the package name in the Google Cloud Console matches `edu.cnm.deepdive.pixelcreate`

### Build Warnings

The build may produce Kotlin metadata warnings from the D8 compiler. These are non-critical and do not affect the build output.

---

## Additional Information

- **Minimum SDK**: API Level 30 (Android 11)
- **Target SDK**: API Level 36 (Android 15)
- **Build Tools Version**: Configured automatically by Gradle
- **Gradle Version**: 9.3.1 (wrapper included)

For more information, see the [Technical Requirements](technical-requirements.md) documentation.
