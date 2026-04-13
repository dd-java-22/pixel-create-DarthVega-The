package edu.cnm.deepdive.pixelcreate.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.OutputStream;

import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.pixelcreate.PixelCanvasView;
import edu.cnm.deepdive.pixelcreate.adapter.ColorSwatchAdapter;
import edu.cnm.deepdive.pixelcreate.adapter.LayerAdapter;
import edu.cnm.deepdive.pixelcreate.databinding.FragmentCanvasBinding;

@AndroidEntryPoint
public class DrawingCanvasFragment extends Fragment {

  private FragmentCanvasBinding binding;
  private LayerAdapter layerAdapter;
  private ColorSwatchAdapter colorAdapter;
  private ActivityResultLauncher<Intent> saveLauncher;
  private ActivityResultLauncher<Intent> loadLauncher;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState
  ) {
    binding = FragmentCanvasBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // ---------------------------------------------------------
    // 1. Read canvas size from navigation arguments
    // ---------------------------------------------------------
    int canvasSize = getArguments() != null
        ? getArguments().getInt("canvas_size", 32)
        : 32;

    // ---------------------------------------------------------
    // 2. File Picker Launchers
    // ---------------------------------------------------------
    saveLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
          if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            Uri uri = result.getData().getData();
            if (uri != null) {
              binding.pixelCanvas.saveToUri(uri);
            }
          }
        }
    );

    loadLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
          if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            Uri uri = result.getData().getData();
            if (uri != null) {
              binding.pixelCanvas.loadFromUri(uri);
              layerAdapter.notifyDataSetChanged();
            }
          }
        }
    );

    // ---------------------------------------------------------
    // 3. Initialize canvas with chosen size
    // ---------------------------------------------------------
    binding.pixelCanvas.setGridSize(canvasSize);

    // ---------------------------------------------------------
    // 4. Layer Panel Setup
    // ---------------------------------------------------------
    layerAdapter = new LayerAdapter(binding.pixelCanvas);
    binding.layerList.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.layerList.setAdapter(layerAdapter);

    // ---------------------------------------------------------
    // 5. Color Palette Setup
    // ---------------------------------------------------------
    int[] colors = new int[]{
        Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE,
        Color.YELLOW, Color.CYAN, Color.MAGENTA, 0xFFFF8800, 0xFFAA66CC
    };

    colorAdapter = new ColorSwatchAdapter(colors, color -> {
      binding.pixelCanvas.setColor(color);
    });

    binding.colorPalette.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.colorPalette.setAdapter(colorAdapter);

    // ---------------------------------------------------------
    // 6. Save Button
    // ---------------------------------------------------------
    binding.saveButton.setOnClickListener(v -> {
      Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
      intent.addCategory(Intent.CATEGORY_OPENABLE);
      intent.setType("application/octet-stream");
      intent.putExtra(Intent.EXTRA_TITLE, "project.pcp");
      saveLauncher.launch(intent);
    });

    // ---------------------------------------------------------
    // 7. Load Button
    // ---------------------------------------------------------
    binding.loadButton.setOnClickListener(v -> {
      Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
      intent.addCategory(Intent.CATEGORY_OPENABLE);
      intent.setType("*/*");
      loadLauncher.launch(intent);
    });

    // ---------------------------------------------------------
    // 8. Setup Toolbar Buttons (TOOLS, UNDO/REDO, EXPORT, ZOOM)
    // ---------------------------------------------------------
    setupToolbar();
  }

  // ---------------------------------------------------------
  // Toolbar Setup
  // ---------------------------------------------------------
  private void setupToolbar() {

    // Undo/Redo
    binding.undoButton.setOnClickListener(v -> {
      binding.pixelCanvas.undo();
      layerAdapter.notifyDataSetChanged();
    });

    binding.redoButton.setOnClickListener(v -> {
      binding.pixelCanvas.redo();
      layerAdapter.notifyDataSetChanged();
    });

    // Tool selection
    binding.pencilButton.setOnClickListener(v -> {
      binding.pixelCanvas.setTool(PixelCanvasView.Tool.PENCIL);
      highlightSelectedTool(binding.pencilButton);
    });

    binding.eraserButton.setOnClickListener(v -> {
      binding.pixelCanvas.setTool(PixelCanvasView.Tool.ERASER);
      highlightSelectedTool(binding.eraserButton);
    });

    binding.fillBucketButton.setOnClickListener(v -> {
      binding.pixelCanvas.setTool(PixelCanvasView.Tool.FILL_BUCKET);
      highlightSelectedTool(binding.fillBucketButton);
    });

    binding.eyedropperButton.setOnClickListener(v -> {
      binding.pixelCanvas.setTool(PixelCanvasView.Tool.EYEDROPPER);
      highlightSelectedTool(binding.eyedropperButton);
    });

    // Zoom reset
    binding.resetZoomButton.setOnClickListener(v -> {
      binding.pixelCanvas.resetZoom();
    });

    // Export PNG
    binding.exportButton.setOnClickListener(v -> {
      exportToPng();
    });

    // Default tool highlight
    highlightSelectedTool(binding.pencilButton);
  }

  private void highlightSelectedTool(View selectedButton) {
    binding.pencilButton.setAlpha(0.5f);
    binding.eraserButton.setAlpha(0.5f);
    binding.fillBucketButton.setAlpha(0.5f);
    binding.eyedropperButton.setAlpha(0.5f);

    selectedButton.setAlpha(1.0f);
  }

  // ---------------------------------------------------------
  // Export PNG
  // ---------------------------------------------------------
  private void exportToPng() {
    try {
      Bitmap bitmap = binding.pixelCanvas.exportToBitmap();

      ContentValues values = new ContentValues();
      values.put(MediaStore.Images.Media.DISPLAY_NAME, "pixel_art_" + System.currentTimeMillis() + ".png");
      values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
      values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

      Uri uri = requireContext().getContentResolver().insert(
          MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

      if (uri != null) {
        try (OutputStream out = requireContext().getContentResolver().openOutputStream(uri)) {
          bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
          Toast.makeText(requireContext(), "Exported to Pictures", Toast.LENGTH_SHORT).show();
        }
      }
    } catch (Exception e) {
      Toast.makeText(requireContext(), "Export failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
      e.printStackTrace();
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
