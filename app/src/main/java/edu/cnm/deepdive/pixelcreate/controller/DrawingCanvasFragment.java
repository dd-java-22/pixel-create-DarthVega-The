package edu.cnm.deepdive.pixelcreate.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.pixelcreate.adapter.ColorSwatchAdapter;
import edu.cnm.deepdive.pixelcreate.adapter.LayerAdapter;
import edu.cnm.deepdive.pixelcreate.databinding.FragmentCanvasBinding;

@AndroidEntryPoint
public class DrawingCanvasFragment extends Fragment {

  private FragmentCanvasBinding binding;
  private LayerAdapter layerAdapter;
  private ColorSwatchAdapter colorAdapter;

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

    setupLayerPanel();
    setupColorPalette();
    setupToolbar();
  }

  private void setupLayerPanel() {
    layerAdapter = new LayerAdapter(binding.pixelCanvas);
    binding.layerList.setAdapter(layerAdapter);
  }

  private void setupColorPalette() {
    int[] colors = new int[]{
        Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE,
        Color.YELLOW, Color.CYAN, Color.MAGENTA, 0xFFFF8800, 0xFFAA66CC
    };

    colorAdapter = new ColorSwatchAdapter(colors, color -> {
      binding.pixelCanvas.setColor(color);
    });

    binding.colorPalette.setAdapter(colorAdapter);
  }

  private void setupToolbar() {
    binding.undoButton.setOnClickListener(v -> {
      binding.pixelCanvas.undo();
      layerAdapter.notifyDataSetChanged();
    });

    binding.redoButton.setOnClickListener(v -> {
      binding.pixelCanvas.redo();
      layerAdapter.notifyDataSetChanged();
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
