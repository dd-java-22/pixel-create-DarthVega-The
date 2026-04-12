### 🎨 Pixel Create — User Guide
- PixelCreate is a lightweight, layer‑based pixel art editor designed for precision drawing, animation planning, and project‑based editing. This guide explains how to navigate the interface, draw on the canvas, manage layers, and save or load project files.

### 🧭 Interface Overview
- PixelCreate is divided into four main areas:

- Top Toolbar — Undo, Redo, Pencil, Eraser, Fill, Eyedropper, Reset Zoom, Export

- Left Sidebar — Color Palette + Save/Load buttons

- Main Canvas — Zoomable, pannable pixel grid

- Bottom Layer Panel — Add, delete, reorder, duplicate, toggle visibility, and select layers

### ✏️ Drawing on the Canvas
- Pencil Tool:
Tap the Pencil icon in the top toolbar.

- Select a color from the color palette.

- Tap or drag on the canvas to draw.

- Each pixel is placed exactly where your finger touches.

- Zoom & Pan:
Pinch to zoom in or out.

- Drag with one finger to pan the canvas.

- Tap Reset Zoom to return to the default view.

### 🎨 Color Selection
- The color palette appears on the left side of the screen.

- Tap any swatch to set the active drawing color.

- The selected color is applied immediately to the Pencil tool.

### 📚 Layer Management
- The layer panel at the bottom of the screen allows full control over your artwork’s structure.

#### Available Layer Actions:
- Select Layer — Tap a layer to make it active.

- Toggle Visibility — Show or hide a layer.

- Duplicate Layer — Creates an exact copy.

- Delete Layer — Removes the selected layer.

- Move Layer Up/Down — Reorder layers.

- Opacity Slider — Adjust transparency.

- Each layer maintains its own pixel data and undo/redo history.

### ↩️ Undo & Redo
- Tap Undo to revert the last change on the active layer.

- Tap Redo to reapply an undone change.

- Undo/redo is tracked per layer, not globally.

### 💾 Saving Projects
#### PixelCreate uses a custom .pcp (PixelCreate Project) format that preserves:

- All layers

- Pixel data

- Visibility

- Opacity

- Active layer index

- To Save a Project:
Tap the Save button under the color palette.

- The system file picker will open.

- Choose a location and filename (e.g., my_art.pcp).

- PixelCreate writes the full project file.

### 📂 Loading Projects
#### To Load a Project
- Tap the Load button under the color palette.

- Select a .pcp file from your device.

#### PixelCreate restores:

- All layers

- Pixel colors

- Active layer

- The canvas updates instantly.

### 🖼️ Exporting Artwork (Flattened PNG)
- Tap the Export button in the top toolbar.

- PixelCreate will generate a flattened PNG of all visible layers.

- This file can be shared, posted, or used in other apps.

### 🛠️ Tools (Current & Upcoming)
#### Available Tools
- Pencil (active)

- Reset Zoom

- Export PNG

### Coming Soon

- Brush Sizes

- Animation Timeline

- Custom Color Picker

### 📁 Project File Format (.pcp)
#### PixelCreate uses a custom binary format that stores:

- Grid size

- Layer count

- Active layer index

- Per‑layer:

- Visibility

- Opacity

- Pixel colors (ARGB)

This ensures projects load exactly as they were saved.

### 🧩 Tips for Best Results
- Zoom in for precise pixel placement.

- Use multiple layers to separate line art, shading, and effects.

- Save often when working on large projects.

- Export PNGs for sharing or posting online.