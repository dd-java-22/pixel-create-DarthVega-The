/*
 *  Copyright 2026 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
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

  private void setupListeners() {
    binding.newProjectButton.setOnClickListener((v) -> navigateToDrawingCanvas());
    binding.paletteManagementButton.setOnClickListener((v) -> navigateToPaletteManagement());
  }

  private void navigateToDrawingCanvas() {
    NavController navController = Navigation.findNavController(requireView());
    navController.navigate(R.id.navigate_to_drawing_canvas);
  }

  private void navigateToPaletteManagement() {
    NavController navController = Navigation.findNavController(requireView());
    navController.navigate(R.id.navigate_to_palette_management);
  }

  private void navigateToSettings() {
    NavController navController = Navigation.findNavController(requireView());
    navController.navigate(R.id.navigate_to_settings);
  }

  private void signOut() {
    client.signOut().addOnCompleteListener((task) -> {
      NavController navController = Navigation.findNavController(requireView());
      navController.navigate(R.id.navigate_to_login);
    });
  }

}
