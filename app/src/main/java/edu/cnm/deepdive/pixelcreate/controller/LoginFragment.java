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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.pixelcreate.R;
import edu.cnm.deepdive.pixelcreate.databinding.FragmentLoginBinding;
import edu.cnm.deepdive.pixelcreate.model.entity.User;
import edu.cnm.deepdive.pixelcreate.service.repository.UserRepository;
import java.time.Instant;
import javax.inject.Inject;

/**
 * Fragment for handling Google Sign In authentication. Provides a sign-in button that
 * launches the Google Sign In flow and navigates to the main app screen upon success.
 */
@AndroidEntryPoint
public class LoginFragment extends Fragment {

  private static final String TAG = LoginFragment.class.getSimpleName();

  private FragmentLoginBinding binding;
  private GoogleSignInClient client;
  private ActivityResultLauncher<Intent> launcher;

  @Inject
  UserRepository userRepository;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Configure Google Sign In
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
        .build();
    client = GoogleSignIn.getClient(requireContext(), options);
    // Register activity result launcher for sign-in
    launcher = registerForActivityResult(
        new StartActivityForResult(),
        result -> {
          try {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
            GoogleSignInAccount account = task.getResult(ApiException.class);
            handleSignInSuccess(account);
          } catch (ApiException e) {
            Log.e(TAG, "Sign in failed", e);
            Toast.makeText(requireContext(), R.string.sign_in_failed, Toast.LENGTH_LONG).show();
          }
        }
    );
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentLoginBinding.inflate(inflater, container, false);
    binding.signInButton.setOnClickListener((v) -> launcher.launch(client.getSignInIntent()));
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // Check if already signed in
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireContext());
    if (account != null) {
      handleSignInSuccess(account);
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  private void handleSignInSuccess(GoogleSignInAccount account) {
    // Create or update user in database
    User user = new User();
    user.setOauthKey(account.getId());
    user.setUsername(account.getDisplayName() != null ? account.getDisplayName() : "User");
    user.setEmail(account.getEmail() != null ? account.getEmail() : "");
    user.setCreatedAt(Instant.now());
    user.setLastLogin(Instant.now());
    
    // Save user and navigate to main screen
    userRepository.save(user)
        .thenAccept((savedUser) -> {
          requireActivity().runOnUiThread(() -> {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.navigate_to_project_gallery);
          });
        })
        .exceptionally((throwable) -> {
          requireActivity().runOnUiThread(() -> {
            Log.e(TAG, "Failed to save user", throwable);
            Toast.makeText(requireContext(), R.string.sign_in_failed, Toast.LENGTH_LONG).show();
          });
          return null;
        });
  }

}
