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
package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

/**
 * Represents a user account in the Pixel Create application. Stores user credentials,
 * authentication details, and personalized preferences that persist across devices.
 */
@Entity(
    tableName = "user",
    indices = {
        @Index(value = "username", unique = true),
        @Index(value = "email", unique = true)
    }
)
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "username")
  private String username;

  @NonNull
  @ColumnInfo(name = "email")
  private String email;

  @ColumnInfo(name = "auth_token")
  private String authToken;

  @ColumnInfo(name = "default_canvas_width")
  private int defaultCanvasWidth;

  @ColumnInfo(name = "default_canvas_height")
  private int defaultCanvasHeight;

  @ColumnInfo(name = "theme_preference")
  private String themePreference;

  @ColumnInfo(name = "grid_visibility")
  private boolean gridVisibility;

  @ColumnInfo(name = "zoom_sensitivity")
  private float zoomSensitivity;

  @NonNull
  @ColumnInfo(name = "created_at")
  private Instant createdAt;

  @ColumnInfo(name = "last_login")
  private Instant lastLogin;

  /**
   * Gets the unique identifier for this user.
   *
   * @return User ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for this user.
   *
   * @param id User ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the username.
   *
   * @return Username.
   */
  @NonNull
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username.
   *
   * @param username Username.
   */
  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  /**
   * Gets the email address.
   *
   * @return Email address.
   */
  @NonNull
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address.
   *
   * @param email Email address.
   */
  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  /**
   * Gets the authentication token.
   *
   * @return Authentication token, or {@code null} if not authenticated.
   */
  public String getAuthToken() {
    return authToken;
  }

  /**
   * Sets the authentication token.
   *
   * @param authToken Authentication token.
   */
  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  /**
   * Gets the default canvas width preference.
   *
   * @return Default canvas width in pixels.
   */
  public int getDefaultCanvasWidth() {
    return defaultCanvasWidth;
  }

  /**
   * Sets the default canvas width preference.
   *
   * @param defaultCanvasWidth Default canvas width in pixels.
   */
  public void setDefaultCanvasWidth(int defaultCanvasWidth) {
    this.defaultCanvasWidth = defaultCanvasWidth;
  }

  /**
   * Gets the default canvas height preference.
   *
   * @return Default canvas height in pixels.
   */
  public int getDefaultCanvasHeight() {
    return defaultCanvasHeight;
  }

  /**
   * Sets the default canvas height preference.
   *
   * @param defaultCanvasHeight Default canvas height in pixels.
   */
  public void setDefaultCanvasHeight(int defaultCanvasHeight) {
    this.defaultCanvasHeight = defaultCanvasHeight;
  }

  /**
   * Gets the theme preference (e.g., "light", "dark").
   *
   * @return Theme preference.
   */
  public String getThemePreference() {
    return themePreference;
  }

  /**
   * Sets the theme preference.
   *
   * @param themePreference Theme preference (e.g., "light", "dark").
   */
  public void setThemePreference(String themePreference) {
    this.themePreference = themePreference;
  }

  /**
   * Gets the grid visibility preference.
   *
   * @return {@code true} if grid should be visible by default, {@code false} otherwise.
   */
  public boolean isGridVisibility() {
    return gridVisibility;
  }

  /**
   * Sets the grid visibility preference.
   *
   * @param gridVisibility {@code true} to show grid by default, {@code false} otherwise.
   */
  public void setGridVisibility(boolean gridVisibility) {
    this.gridVisibility = gridVisibility;
  }

  /**
   * Gets the zoom sensitivity setting.
   *
   * @return Zoom sensitivity multiplier.
   */
  public float getZoomSensitivity() {
    return zoomSensitivity;
  }

  /**
   * Sets the zoom sensitivity setting.
   *
   * @param zoomSensitivity Zoom sensitivity multiplier.
   */
  public void setZoomSensitivity(float zoomSensitivity) {
    this.zoomSensitivity = zoomSensitivity;
  }

  /**
   * Gets the account creation timestamp.
   *
   * @return Creation timestamp.
   */
  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the account creation timestamp.
   *
   * @param createdAt Creation timestamp.
   */
  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the last login timestamp.
   *
   * @return Last login timestamp, or {@code null} if never logged in.
   */
  public Instant getLastLogin() {
    return lastLogin;
  }

  /**
   * Sets the last login timestamp.
   *
   * @param lastLogin Last login timestamp.
   */
  public void setLastLogin(Instant lastLogin) {
    this.lastLogin = lastLogin;
  }
}
