package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

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
  private String username = "";

  @NonNull
  @ColumnInfo(name = "email")
  private String email = "";

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
  private Instant createdAt = Instant.now();

  @ColumnInfo(name = "last_login")
  private Instant lastLogin;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getUsername() {
    return username;
  }

  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public int getDefaultCanvasWidth() {
    return defaultCanvasWidth;
  }

  public void setDefaultCanvasWidth(int defaultCanvasWidth) {
    this.defaultCanvasWidth = defaultCanvasWidth;
  }

  public int getDefaultCanvasHeight() {
    return defaultCanvasHeight;
  }

  public void setDefaultCanvasHeight(int defaultCanvasHeight) {
    this.defaultCanvasHeight = defaultCanvasHeight;
  }

  public String getThemePreference() {
    return themePreference;
  }

  public void setThemePreference(String themePreference) {
    this.themePreference = themePreference;
  }

  public boolean isGridVisibility() {
    return gridVisibility;
  }

  public void setGridVisibility(boolean gridVisibility) {
    this.gridVisibility = gridVisibility;
  }

  public float getZoomSensitivity() {
    return zoomSensitivity;
  }

  public void setZoomSensitivity(float zoomSensitivity) {
    this.zoomSensitivity = zoomSensitivity;
  }

  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Instant lastLogin) {
    this.lastLogin = lastLogin;
  }
}
