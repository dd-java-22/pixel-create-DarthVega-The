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
 * Represents a pixel art project. Contains metadata about canvas dimensions, creation and
 * modification timestamps, and thumbnail preview for gallery display.
 */
@Entity(
    tableName = "project",
    indices = {
        @Index(value = "user_id"),
        @Index(value = "project_name")
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
public class Project {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "project_id")
  private long id;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @NonNull
  @ColumnInfo(name = "project_name")
  private String projectName;

  @ColumnInfo(name = "canvas_width")
  private int canvasWidth;

  @ColumnInfo(name = "canvas_height")
  private int canvasHeight;

  @NonNull
  @ColumnInfo(name = "created_at")
  private Instant createdAt;

  @NonNull
  @ColumnInfo(name = "last_edited_at")
  private Instant lastEditedAt;

  @ColumnInfo(name = "thumbnail_image", typeAffinity = ColumnInfo.BLOB)
  private byte[] thumbnailImage;

  @ColumnInfo(name = "is_deleted")
  private boolean isDeleted;

  @ColumnInfo(name = "tags")
  private String tags;

  /**
   * Gets the unique identifier for this project.
   *
   * @return Project ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for this project.
   *
   * @param id Project ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID of the user who owns this project.
   *
   * @return User ID.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets the ID of the user who owns this project.
   *
   * @param userId User ID.
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Gets the project name.
   *
   * @return Project name.
   */
  @NonNull
  public String getProjectName() {
    return projectName;
  }

  /**
   * Sets the project name.
   *
   * @param projectName Project name.
   */
  public void setProjectName(@NonNull String projectName) {
    this.projectName = projectName;
  }

  /**
   * Gets the canvas width in pixels.
   *
   * @return Canvas width.
   */
  public int getCanvasWidth() {
    return canvasWidth;
  }

  /**
   * Sets the canvas width in pixels.
   *
   * @param canvasWidth Canvas width.
   */
  public void setCanvasWidth(int canvasWidth) {
    this.canvasWidth = canvasWidth;
  }

  /**
   * Gets the canvas height in pixels.
   *
   * @return Canvas height.
   */
  public int getCanvasHeight() {
    return canvasHeight;
  }

  /**
   * Sets the canvas height in pixels.
   *
   * @param canvasHeight Canvas height.
   */
  public void setCanvasHeight(int canvasHeight) {
    this.canvasHeight = canvasHeight;
  }

  /**
   * Gets the project creation timestamp.
   *
   * @return Creation timestamp.
   */
  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the project creation timestamp.
   *
   * @param createdAt Creation timestamp.
   */
  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the last edited timestamp.
   *
   * @return Last edited timestamp.
   */
  @NonNull
  public Instant getLastEditedAt() {
    return lastEditedAt;
  }

  /**
   * Sets the last edited timestamp.
   *
   * @param lastEditedAt Last edited timestamp.
   */
  public void setLastEditedAt(@NonNull Instant lastEditedAt) {
    this.lastEditedAt = lastEditedAt;
  }

  /**
   * Gets the thumbnail image data.
   *
   * @return Thumbnail image as byte array, or {@code null} if no thumbnail exists.
   */
  public byte[] getThumbnailImage() {
    return thumbnailImage;
  }

  /**
   * Sets the thumbnail image data.
   *
   * @param thumbnailImage Thumbnail image as byte array.
   */
  public void setThumbnailImage(byte[] thumbnailImage) {
    this.thumbnailImage = thumbnailImage;
  }

  /**
   * Checks whether this project is marked as deleted.
   *
   * @return {@code true} if project is deleted (soft delete), {@code false} otherwise.
   */
  public boolean isDeleted() {
    return isDeleted;
  }

  /**
   * Sets the deleted status of this project.
   *
   * @param deleted {@code true} to mark as deleted, {@code false} otherwise.
   */
  public void setDeleted(boolean deleted) {
    isDeleted = deleted;
  }

  /**
   * Gets the project tags (comma-separated or JSON).
   *
   * @return Tags string, or {@code null} if no tags.
   */
  public String getTags() {
    return tags;
  }

  /**
   * Sets the project tags.
   *
   * @param tags Tags string (comma-separated or JSON).
   */
  public void setTags(String tags) {
    this.tags = tags;
  }
}
