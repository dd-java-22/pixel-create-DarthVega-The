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
 * Represents a drawing layer within a project. Supports complex artwork with layer stacking,
 * visibility controls, locking, and opacity settings.
 */
@Entity(
    tableName = "layer",
    indices = {
        @Index(value = "project_id"),
        @Index(value = {"project_id", "layer_order"})
    },
    foreignKeys = {
        @ForeignKey(
            entity = Project.class,
            parentColumns = "project_id",
            childColumns = "project_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Layer {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "layer_id")
  private long id;

  @ColumnInfo(name = "project_id", index = true)
  private long projectId;

  @NonNull
  @ColumnInfo(name = "layer_name")
  private String layerName;

  @ColumnInfo(name = "layer_order")
  private int layerOrder;

  @ColumnInfo(name = "is_visible")
  private boolean isVisible;

  @ColumnInfo(name = "is_locked")
  private boolean isLocked;

  @ColumnInfo(name = "opacity")
  private float opacity;

  @NonNull
  @ColumnInfo(name = "created_at")
  private Instant createdAt;

  /**
   * Gets the unique identifier for this layer.
   *
   * @return Layer ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for this layer.
   *
   * @param id Layer ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID of the project this layer belongs to.
   *
   * @return Project ID.
   */
  public long getProjectId() {
    return projectId;
  }

  /**
   * Sets the ID of the project this layer belongs to.
   *
   * @param projectId Project ID.
   */
  public void setProjectId(long projectId) {
    this.projectId = projectId;
  }

  /**
   * Gets the layer name.
   *
   * @return Layer name.
   */
  @NonNull
  public String getLayerName() {
    return layerName;
  }

  /**
   * Sets the layer name.
   *
   * @param layerName Layer name.
   */
  public void setLayerName(@NonNull String layerName) {
    this.layerName = layerName;
  }

  /**
   * Gets the layer order (Z-index stacking position).
   *
   * @return Layer order value (lower values are below higher values).
   */
  public int getLayerOrder() {
    return layerOrder;
  }

  /**
   * Sets the layer order (Z-index stacking position).
   *
   * @param layerOrder Layer order value.
   */
  public void setLayerOrder(int layerOrder) {
    this.layerOrder = layerOrder;
  }

  /**
   * Checks whether this layer is visible.
   *
   * @return {@code true} if layer is visible, {@code false} otherwise.
   */
  public boolean isVisible() {
    return isVisible;
  }

  /**
   * Sets the visibility of this layer.
   *
   * @param visible {@code true} to make layer visible, {@code false} to hide.
   */
  public void setVisible(boolean visible) {
    isVisible = visible;
  }

  /**
   * Checks whether this layer is locked (prevents editing).
   *
   * @return {@code true} if layer is locked, {@code false} otherwise.
   */
  public boolean isLocked() {
    return isLocked;
  }

  /**
   * Sets the locked status of this layer.
   *
   * @param locked {@code true} to lock layer, {@code false} to unlock.
   */
  public void setLocked(boolean locked) {
    isLocked = locked;
  }

  /**
   * Gets the opacity level of this layer.
   *
   * @return Opacity value between 0.0 (fully transparent) and 1.0 (fully opaque).
   */
  public float getOpacity() {
    return opacity;
  }

  /**
   * Sets the opacity level of this layer.
   *
   * @param opacity Opacity value between 0.0 and 1.0.
   */
  public void setOpacity(float opacity) {
    this.opacity = opacity;
  }

  /**
   * Gets the layer creation timestamp.
   *
   * @return Creation timestamp.
   */
  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the layer creation timestamp.
   *
   * @param createdAt Creation timestamp.
   */
  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }
}
