package edu.cnm.deepdive.pixelcreate.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.pixelcreate.R;
import edu.cnm.deepdive.pixelcreate.databinding.FragmentProjectGalleryBinding;

/**
 * Fragment displaying the user's project gallery. Shows a list of pixel art projects
 * and provides navigation to create new projects or edit existing ones.
 */
@AndroidEntryPoint
public class ProjectGalleryFragment extends Fragment {

  private FragmentProjectGalleryBinding binding;
  private GoogleSignInClient client;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Configure Google Sign In client for sign out
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
        .build();

    client = GoogleSignIn.getClient(requireContext(), options);
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState
  ) {
    binding = FragmentProjectGalleryBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupMenu();
    setupListeners();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  // ------------------------------------------------------------
  // Menu Setup
  // ------------------------------------------------------------
  private void setupMenu() {
    requireActivity().addMenuProvider(new MenuProvider() {
      @Override
      public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.project_gallery_menu, menu);
      }

      @Override
      public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        boolean handled;
        int itemId = menuItem.getItemId();

        if (itemId == R.id.sign_out) {
          signOut();
          handled = true;

        } else if (itemId == R.id.settings) {
          navigateToSettings();
          handled = true;

        } else {
          handled = false;
        }

        return handled;
      }
    }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
  }

  // ------------------------------------------------------------
  // Button Listeners
  // ------------------------------------------------------------
  private void setupListeners() {

    // NEW PROJECT → show canvas size dialog
    binding.newProjectButton.setOnClickListener((v) -> showCanvasSizeDialog());

    // Palette management
    binding.paletteManagementButton.setOnClickListener((v) -> navigateToPaletteManagement());
  }

  // ------------------------------------------------------------
  // NEW: Canvas Size Dialog
  // ------------------------------------------------------------
  private void showCanvasSizeDialog() {
    String[] sizes = {"16", "32", "64", "128"};

    new AlertDialog.Builder(requireContext())
        .setTitle("New Project")
        .setSingleChoiceItems(sizes, 1, null) // default = 32
        .setPositiveButton("Create", (dialog, which) -> {
          int selected = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
          int newSize = Integer.parseInt(sizes[selected]);
          navigateToDrawingCanvas(newSize);
        })
        .setNegativeButton("Cancel", null)
        .show();
  }

  // ------------------------------------------------------------
  // Navigation
  // ------------------------------------------------------------
  private void navigateToDrawingCanvas(int canvasSize) {
    Bundle args = new Bundle();
    args.putInt("canvas_size", canvasSize);

    NavController navController = Navigation.findNavController(requireView());
    navController.navigate(R.id.navigate_to_drawing_canvas, args);
  }

  private void navigateToPaletteManagement() {
    NavController navController = Navigation.findNavController(requireView());
    navController.navigate(R.id.navigate_to_palette_management);
  }

  private void navigateToSettings() {
    NavController navController = Navigation.findNavController(requireView());
    navController.navigate(R.id.navigate_to_settings);
  }

  // ------------------------------------------------------------
  // Sign Out
  // ------------------------------------------------------------
  private void signOut() {
    client.signOut().addOnCompleteListener((task) -> {
      NavController navController = Navigation.findNavController(requireView());
      navController.navigate(R.id.navigate_to_login);
    });
  }
}
