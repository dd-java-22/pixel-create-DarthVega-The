---
title: Overview
description: "Summary of in-progress or completed project."
order: 0
---

{% include ddc-abbreviations.md %}

## Page contents
{:.no_toc}

- ToC
{:toc}

## Summary

Pixel Create is a mobile Android application designed to let users create retro-styled pixel art through an intuitive touch-based interface. The app centers on a customizable drawing canvas where each pixel can be edited individually, allowing for precise artwork reminiscent of classic video game graphics. Users can start new projects, reopen saved projects, and even duplicate existing pieces to experiment with different styles without losing their work. The drawing experience includes essential tools such as tap‑to‑color, drag‑to‑draw, fill, erase, and a color picker, supported by undo/redo and zooming for fine‑detail work. A flexible color system allows users to build and reuse custom palettes, ensuring consistency across their artwork.

## Intended users and user stories

### The Hobby Artist
People who enjoy drawing casually and want a simple, relaxing creative outlet.
Why they care:
- Pixel art feels approachable and low‑pressure.
- They want an easy way to doodle, experiment, and share creations.
- They appreciate tools like undo/redo, zoom, and palettes that make drawing feel smooth.
### Retro Game Enthusiasts
  Fans of classic games or people who want to create sprites for fun.
  Why they care:
- Pixel art is nostalgic and tied to the games they love.
- They may want to create characters, tiles, or icons.
- Exporting at different resolutions is useful for sharing or using in small personal projects.
### Indie Game Developers (Beginner Level)
  Students or hobbyists building small games who need simple sprite creation.
  Why they care:
- They need a quick way to sketch ideas or build simple assets.
- Layers, grids, and export resolution matter for game integration.
- A gallery helps them manage multiple assets.

## Functionality

### 🎨 Canvas and Drawing Capabilities
These functions define how users create and manipulate pixel art on the canvas.
- Canvas creation — Users choose width and height to start a new project.
- Pixel editing — Tap to color individual pixels with precision.
- Continuous drawing — Drag to draw lines or fill areas quickly.
- Zoom and pan — Navigate the canvas for detail work or full‑view inspection.
- Undo/redo — Reverse or reapply recent actions to support experimentation.
- Drawing tools — Fill bucket, eraser, color picker, and potentially symmetry tools.
- Layer support (optional) — Add, hide, reorder, or lock layers for complex artwork.

### 🎨 Color and Palette Management
These functions support color selection and consistency across projects.
- Color palette selection — Choose from a default palette for quick access.
- Custom palette creation — Save personalized color sets for reuse.
- Color picking from canvas — Match existing colors by sampling pixels.
- Palette persistence — Store palettes in SQLite for long‑term use.

### 💾 Project Storage and Data Persistence
These functions ensure users can save, load, and manage their artwork reliably.
- Local project saving — Store pixel, layer, and project metadata in SQLite.
- Autosave — Automatically update project state to prevent data loss.
- Project loading — Reopen saved projects to continue editing.
- Project duplication — Create a copy for experimentation or versioning.
- Project deletion and renaming — Keep the workspace organized.
- Exporting artwork — Save PNG images to device storage.
- Export resolution options — Scale pixel art for different uses.
- Export history (optional) — Log exports in SQLite for tracking.

### 🖼️ Project Gallery and Data‑Driven UI
These functions create a dynamic, user‑friendly home screen.
- Thumbnail previews — Display a small rendered version of each project.
- Project metadata display — Show name, size, and last‑edited timestamp.
- Sorting or filtering (optional) — Organize projects by date or name.
- Quick actions — Open, duplicate, rename, or delete from the gallery.

## Persistent data

### 📁 Core Project Data
This is the essential information needed to reconstruct a user’s artwork on any device.
- Project metadata — project name, canvas size, creation date, last edited timestamp.
- Pixel data — the color value of each pixel in the canvas.
- Layer data — layer order, visibility, opacity, and pixel assignments per layer.
- Autosave snapshots — periodic backups to prevent data loss across devices.
  These items allow a user to open their project anywhere and continue seamlessly.

### 🎨 Color and Palette Data
Color information supports consistent creative workflows.
- Custom palettes — user‑created color sets stored for reuse.
- Palette colors — individual color values tied to each palette.
- Recently used colors (optional) — convenience data to speed up drawing.
  This enables a user’s preferred colors to follow them across devices.

### 🖼️ Gallery and Asset Management
The server can maintain a structured view of all user projects.
- Thumbnail images — small preview images generated on save or upload.
- Project organization data — folder structure, tags, or sorting preferences.
- Deleted project logs (optional) — soft‑delete records for recovery.
  This supports a dynamic, cloud‑synced gallery.

### 💾 Export and Sharing Data
If the app supports exporting or sharing artwork online, the server may store:
- Export history — timestamps, resolution, and file type of each export.
- Exported images — PNG files stored for download or sharing.
- Share links (optional) — URLs for public or private project sharing.
  This enables users to access their exported art from anywhere.

### 👤 User Profile and Settings
These settings personalize the experience across devices.
- Account information — username, email, authentication tokens.
- Default canvas size — user preference for new projects.
- Theme preferences — light/dark mode settings.
- Tool settings — brush size, grid visibility, zoom sensitivity.
  These ensure the app feels consistent no matter where the user logs in.

## Device/external services

### 📸 Camera Access
   Required if you implement:
- Taking a photo to use as a reference layer
- Capturing an image to pixelate
- Any real‑time camera‑based pixelation feature
  Permission needed:
  android.permission.CAMERA
  This is the most significant device integration in your app.

### 📁 External Storage Access
   Required if you allow:
- Exporting PNG files to the user’s gallery
- Importing images from the device’s photo library
- Loading reference images from Downloads or Pictures
  Permissions (depending on Android version):
- READ_MEDIA_IMAGES (Android 13+)
- READ_EXTERNAL_STORAGE (older versions)
- WRITE_EXTERNAL_STORAGE (legacy only; modern apps use MediaStore instead)
  This is essential for any import/export workflow outside the app’s private storage.

### 👤 Notifications
   Required only if you implement:
- Autosave notifications
- Export‑complete notifications
- Background processing alerts
  Permission needed (Android 13+):
  POST_NOTIFICATIONS
  This is optional but improves user experience.

## Stretch goals and possible enhancements 

### 📸 Device Integration (Functionality Stretch Goal)
These functions enhance creativity by connecting with device hardware and media.
• 	Image import — Load images from device storage as reference layers.
• 	Camera capture — Take a photo and convert it into pixel art.
• 	Pixelation filter — Automatically reduce an image to pixel‑style blocks.
• 	Notifications — Alert users when autosave or export completes

### 🧩 Advanced Creative Tools (Functionality Stretch Goal)
These functions support more sophisticated pixel‑art workflows.
- 	Animation frames — Create multi‑frame pixel animations.
-	Onion skinning — See previous/next frames for smoother animation.
- 	Symmetry tools — Mirror strokes horizontally or vertically.
- 	Layer blending modes (optional) — Add transparency or overlay effects.

### 📸 Optional Device‑Integrated Content (Data Stretch Goal)
If we implement camera or import features, the server may store:
- Imported reference images — photos or images uploaded for tracing.
- Pixelated photo versions — server‑generated transformations.
- Temporary processing files — used for image conversion or filters.
  This supports advanced features like photo‑to‑pixel conversion.

### 🧩 Advanced Creative Features (Data Stretch Goal)
If we add simple animation frames or collaboration, the server may maintain:
- 	Animation frames — per‑frame pixel data and timing.
- 	Onion‑skin settings — transparency and frame‑linking metadata.
- 	Collaboration logs — version history, contributors, and merge data.

### Vibration / Haptics (Persistence Stretch Goal)
If you add tactile feedback for drawing tools or button presses.
Permission: none required on modern Android, but uses the vibrator service.

### 🌐 Internet Access (Persistence Stretch Goal)
Only needed if you add:
- Cloud sync
- Online gallery sharing
- Remote backups
  Permission:
  android.permission.INTERNET

### 📊 Analytics or Crash Reporting (Persistence Stretch Goal)
If you add:
-  If you integrate Google Analytics or similar tools.
-  If you integrate Firebase or similar tools.
Permission: none required, but uses network access.
