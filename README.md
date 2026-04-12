# Pixel Create

A mobile Android pixel art creation application designed for retro-styled digital artwork.

---

## Summary

Pixel Create is a mobile Android application designed to let users create retro-styled pixel art through an intuitive touch-based interface. The app centers on a customizable drawing canvas where each pixel can be edited individually, allowing for precise artwork reminiscent of classic video game graphics. Users can start new projects, reopen saved projects, and even duplicate existing pieces to experiment with different styles without losing their work. The drawing experience includes essential tools such as tap‑to‑color, drag‑to‑draw, fill, erase, and a color picker, supported by undo/redo and zooming for fine‑detail work. A flexible color system allows users to build and reuse custom palettes, ensuring consistency across their artwork.

**Note**: This application is currently in active development as part of the Deep Dive Coding Java + Android Bootcamp. Core drawing functionality is under development. See [Current State](https://dd-java-22.github.io/pixel-create-DarthVega-The/current-state) for implementation status.

---

## Features

### Implemented
- ✅ Google Sign-In authentication
- ✅ Room Database persistence
- ✅ Multi-screen navigation
- ✅ Settings management (SharedPreferences)
- ✅ Layer management UI components
- ✅ Color palette selection UI

### In Development
- 🚧 Drawing canvas functionality
- 🚧 Project creation and loading
- 🚧 Save/load operations
- 🚧 Undo/redo system
- 🚧 PNG export

### Planned
- 📋 Animation frames
- 📋 Image import and pixelation
- 📋 Cloud backup
- 📋 Advanced drawing tools

---

## Documentation

- **[Project Website](https://dd-java-22.github.io/pixel-create-DarthVega-The/)** - Complete documentation and project overview
- **[Build Instructions](https://dd-java-22.github.io/pixel-create-DarthVega-The/build-instructions)** - How to build and run the app
- **[User Instructions](https://dd-java-22.github.io/pixel-create-DarthVega-The/user-instructions)** - Guide for using the app
- **[API Documentation (Javadoc)](https://dd-java-22.github.io/pixel-create-DarthVega-The/api/client/)** - Generated code documentation
- **[Technical Requirements](https://dd-java-22.github.io/pixel-create-DarthVega-The/technical-requirements)** - Dependencies and system requirements
- **[Current State](https://dd-java-22.github.io/pixel-create-DarthVega-The/current-state)** - Development status and known issues
- **[Copyrights & Licenses](https://dd-java-22.github.io/pixel-create-DarthVega-The/copyrights-licenses)** - License information

---

## Quick Start

### Prerequisites

- Android Studio Iguana or later
- JDK 17+
- Android SDK (API 30-36)
- Google OAuth 2.0 Client ID (for authentication)

### Build Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/dd-java-22/pixel-create-DarthVega-The.git
   cd pixel-create-DarthVega-The
   ```

2. **Configure Google Sign-In**:
   - Obtain a Client ID from [Google Cloud Console](https://console.cloud.google.com/)
   - Create `app/src/main/res/values/secrets.xml`:
     ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <resources>
       <string name="default_web_client_id">YOUR_CLIENT_ID_HERE</string>
     </resources>
     ```

3. **Build and run**:
   ```bash
   ./gradlew assembleDebug
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

For detailed instructions, see the [Build Instructions](https://dd-java-22.github.io/pixel-create-DarthVega-The/build-instructions) documentation.

---

## Technology Stack

- **Language**: Java 17, Kotlin 2.1.0
- **UI Framework**: Android Views with View Binding
- **Database**: Room Persistence Library
- **Dependency Injection**: Hilt (Dagger)
- **Navigation**: Android Navigation Component
- **Authentication**: Google Sign-In
- **Minimum SDK**: API 30 (Android 11)
- **Target SDK**: API 36 (Android 15)

---

## Project Structure

```
pixel-create-DarthVega-The/
├── app/
│   ├── src/main/java/edu/cnm/deepdive/pixelcreate/
│   │   ├── controller/        # Fragments and Activities
│   │   ├── model/
│   │   │   ├── entity/        # Room entities
│   │   │   └── dao/           # Data Access Objects
│   │   ├── service/
│   │   │   └── repository/    # Repository pattern implementations
│   │   ├── adapter/           # RecyclerView adapters
│   │   └── view/              # Custom views
│   └── src/main/res/          # Resources (layouts, drawables, etc.)
├── docs/                      # GitHub Pages documentation
└── NOTICE                     # Copyright and license information
```

---

## Contributing

This is an educational project developed as part of the Deep Dive Coding bootcamp. Contributions are not currently being accepted.

---

## Credits, Copyrights, and License Information

**Copyright © 2026 Mark Vega**
All rights reserved.

This application is provided for educational purposes as part of the Deep Dive Coding Java + Android Bootcamp program.

### Third-Party Libraries

This project uses the following open-source libraries under the Apache License 2.0:

- Android SDK & AndroidX Libraries
- Room Persistence Library
- Hilt (Dagger) Dependency Injection
- Navigation Component
- Material Design Components
- Kotlin Standard Library
- Google Play Services - Auth
- Gson
- Retrofit & OkHttp

For complete copyright and license information, see:
- [NOTICE](NOTICE) - Plain text copyright notices
- [Copyrights & Licenses](https://dd-java-22.github.io/pixel-create-DarthVega-The/copyrights-licenses) - Formatted documentation

---

## Contact

- **Developer**: Mark Vega
- **Project Repository**: [https://github.com/dd-java-22/pixel-create-DarthVega-The](https://github.com/dd-java-22/pixel-create-DarthVega-The)
- **Documentation**: [https://dd-java-22.github.io/pixel-create-DarthVega-The/](https://dd-java-22.github.io/pixel-create-DarthVega-The/)

---

## Acknowledgments

- Deep Dive Coding Java + Android Bootcamp instructors and staff
- Android Open Source Project
- All open-source library contributors
