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
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

/**
 * Represents a color palette - a user-created collection of colors for consistent artwork styling.
 */
@Entity(
    tableName = "palette",
    indices = {
        @Index(value = "user_id"),
        @Index(value = "palette_name")
    },
    foreignKeys = {
        @ForeignKey(
            entity = User.class,
            parentColumns = "user_id",
            childColumns = "user_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Palette {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "palette_id")
  private long id;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @NonNull
  @ColumnInfo(name = "palette_name")
  private String paletteName;

  @ColumnInfo(name = "is_default")
  private boolean isDefault;

  @NonNull
  @ColumnInfo(name = "created_at")
  private Instant createdAt;

  @ColumnInfo(name = "last_used")
  private Instant lastUsed;

  /**
   * Gets the unique identifier for this palette.
   *
   * @return Palette ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for this palette.
   *
   * @param id Palette ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID of the user who owns this palette.
   *
   * @return User ID.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets the ID of the user who owns this palette.
   *
   * @param userId User ID.
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Gets the palette name.
   *
   * @return Palette name.
   */
  @NonNull
  public String getPaletteName() {
    return paletteName;
  }

  /**
   * Sets the palette name.
   *
   * @param paletteName Palette name.
   */
  public void setPaletteName(@NonNull String paletteName) {
    this.paletteName = paletteName;
  }

  /**
   * Checks whether this is a default system-provided palette.
   *
   * @return {@code true} if this is a default palette, {@code false} for user-created palettes.
   */
  public boolean isDefault() {
    return isDefault;
  }

  /**
   * Sets whether this is a default system-provided palette.
   *
   * @param isDefault {@code true} for default palette, {@code false} for user-created.
   */
  public void setDefault(boolean isDefault) {
    this.isDefault = isDefault;
  }

  /**
   * Gets the palette creation timestamp.
   *
   * @return Creation timestamp.
   */
  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the palette creation timestamp.
   *
   * @param createdAt Creation timestamp.
   */
  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the last used timestamp for this palette.
   *
   * @return Last used timestamp, or {@code null} if never used.
   */
  public Instant getLastUsed() {
    return lastUsed;
  }

  /**
   * Sets the last used timestamp for this palette.
   *
   * @param lastUsed Last used timestamp.
   */
  public void setLastUsed(Instant lastUsed) {
    this.lastUsed = lastUsed;
  }
}
