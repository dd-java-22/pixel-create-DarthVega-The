---
title: User Instructions
description: "Guide for using the Pixel Create application"
order: 65
---

## User Instructions

This guide provides instructions for using the Pixel Create pixel art application. It assumes you are familiar with standard Android navigation (back button, overflow menus) and focuses on app-specific operations.

**Note**: The application is currently in active development. Many features described in this guide are planned but not yet fully implemented. See the [Current State](current-state.md) document for implementation status.

---

## Getting Started

### First Launch - Authentication

1. **Sign In with Google**
   - On first launch, you'll see the login screen
   - Tap the **"Sign in with Google"** button
   - Select your Google account from the system picker
   - Grant permissions when prompted
   - You'll be automatically redirected to the Project Gallery

2. **Automatic Sign-In**
   - After the first successful sign-in, the app will remember your account
   - Future launches will skip the login screen and go directly to your gallery

---

## Project Gallery

The Project Gallery is your home screen where you can view, create, and manage your pixel art projects.

### Creating a New Project (Not Yet Implemented)

1. Tap the **"New Project"** button in the gallery
2. The app will navigate to the drawing canvas
3. *Note: Project creation with custom dimensions is planned but not yet implemented*

### Opening an Existing Project (Not Yet Implemented)

1. Projects from the database will appear as thumbnails in the gallery
2. Tap a project to open it in the drawing canvas
3. *Note: Project loading functionality is planned but not yet implemented*

### Managing Projects (Not Yet Implemented)

- **Rename**: Planned for future release
- **Duplicate**: Planned for future release
- **Delete**: Planned for future release

---

## Drawing Canvas

The canvas is where you create and edit your pixel art.

### Interface Overview

The drawing canvas includes:

- **Top Toolbar**: Undo, Redo, Save, Load buttons
- **Canvas Area**: Main drawing surface with zoom and pan support
- **Color Palette**: Bottom horizontal scroll view with color swatches
- **Layer Panel**: Bottom panel for layer management

### Basic Drawing (Partially Implemented)

1. **Select a Tool**:
   - *Note: Drawing tools are planned but not yet fully functional*
   - **Pencil**: Draw individual pixels
   - **Eraser**: Remove pixels (makes them transparent)
   - **Fill Bucket**: Fill connected areas with the selected color
   - **Color Picker**: Sample a color from the canvas

2. **Select a Color**:
   - Tap any color in the palette at the bottom
   - The selected color is highlighted
   - Use the color picker tool to sample colors from your artwork

3. **Draw**:
   - **Tap**: Draw a single pixel
   - **Drag**: Draw continuously as you move your finger
   - *Note: Drawing functionality is currently under development*

### Canvas Navigation

- **Zoom In/Out**: Pinch gesture (two-finger pinch)
- **Pan**: Drag with two fingers to move the canvas
- **Reset Zoom**: Tap the reset zoom button to return to default scale

### Undo and Redo (Not Yet Implemented)

- **Undo**: Tap the undo button (↶ icon) to reverse the last action
- **Redo**: Tap the redo button (↷ icon) to reapply an undone action
- *Note: Undo/redo functionality is planned but not yet implemented*

### Saving Your Work (Partially Implemented)

- **Save Button**: Tap the save button (💾 icon) to save
- *Note: Save functionality is under development; pixels may not persist correctly*

---

## Layers (Partially Implemented)

Layers allow you to separate elements of your artwork for easier editing.

### Managing Layers

1. **View Layers**: The layer panel appears at the bottom of the canvas
2. **Select Layer**: Tap a layer to make it active
3. **Toggle Visibility**: Tap the eye icon to show/hide a layer
4. **Add Layer**: Tap the + button to create a new layer
5. **Delete Layer**: Long-press a layer and select delete
6. **Reorder Layers**: Use up/down arrows to change layer order

*Note: Layer functionality is partially implemented; some features may not work as expected*

---

## Color Palettes (Partially Implemented)

Palettes help you maintain consistent colors across your artwork.

### Using Palettes

1. **Default Palette**: A color palette is loaded by default in the drawing canvas
2. **Select Color**: Tap any color swatch to set it as the active drawing color

### Creating Custom Palettes (Not Yet Implemented)

1. Navigate to **Project Gallery** → Tap **"Palette Management"** button
2. *Note: Palette creation and management UI exists but is not yet functional*

---

## Settings

Access settings from the Project Gallery overflow menu (⋮).

### Available Settings

#### Canvas Settings

- **Default Canvas Width**: Width in pixels for new projects (default: 32)
- **Default Canvas Height**: Height in pixels for new projects (default: 32)
- **Show Grid**: Toggle grid lines on the canvas for easier pixel alignment

#### Appearance Settings

- **Theme**:
  - **Light**: Light background with dark text
  - **Dark**: Dark background with light text
  - **System Default**: Follows device theme setting

*Note: Settings are saved but not yet utilized in the app's behavior*

---

## Exporting Your Artwork (Not Yet Implemented)

- Export functionality is planned but not yet implemented
- Future versions will support PNG export to device gallery

---

## Signing Out

1. Navigate to the **Project Gallery**
2. Tap the overflow menu (⋮) in the top-right
3. Select **"Sign Out"**
4. Confirm sign-out
5. You'll be returned to the login screen

**Note**: All projects are stored locally on your device. Signing out does not delete your projects.

---

## Known Limitations

Please refer to the [Current State](current-state.md) document for a comprehensive list of:

- Unimplemented features
- Known bugs
- Planned enhancements

---

## Getting Help

If you encounter issues:

1. Check the [Current State](current-state.md) document for known bugs and limitations
2. Review the [Technical Requirements](technical-requirements.md) for compatibility information
3. Report bugs via the project's GitHub Issues page

---

## Related Documentation

- [Build Instructions](build-instructions.md) - How to build the app from source
- [Technical Requirements](technical-requirements.md) - System requirements and dependencies
- [Current State](current-state.md) - Implementation status and known issues