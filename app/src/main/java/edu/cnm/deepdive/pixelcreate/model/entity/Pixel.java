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
 * Represents an individual pixel within a layer. Each pixel has a position (x, y coordinates)
 * and a color value.
 */
@Entity(
    tableName = "pixel",
    indices = {
        @Index(value = "layer_id"),
        @Index(value = {"layer_id", "x_coordinate", "y_coordinate"}, unique = true)
    },
    foreignKeys = {
        @ForeignKey(
            entity = Layer.class,
            parentColumns = "layer_id",
            childColumns = "layer_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Pixel {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "pixel_id")
  private long id;

  @ColumnInfo(name = "layer_id", index = true)
  private long layerId;

  @ColumnInfo(name = "x_coordinate")
  private int xCoordinate;

  @ColumnInfo(name = "y_coordinate")
  private int yCoordinate;

  @ColumnInfo(name = "color_value")
  private int colorValue;

  @NonNull
  @ColumnInfo(name = "last_modified")
  private Instant lastModified;

  /**
   * Gets the unique identifier for this pixel.
   *
   * @return Pixel ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for this pixel.
   *
   * @param id Pixel ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID of the layer this pixel belongs to.
   *
   * @return Layer ID.
   */
  public long getLayerId() {
    return layerId;
  }

  /**
   * Sets the ID of the layer this pixel belongs to.
   *
   * @param layerId Layer ID.
   */
  public void setLayerId(long layerId) {
    this.layerId = layerId;
  }

  /**
   * Gets the X coordinate of this pixel on the canvas.
   *
   * @return X coordinate.
   */
  public int getXCoordinate() {
    return xCoordinate;
  }

  /**
   * Sets the X coordinate of this pixel on the canvas.
   *
   * @param xCoordinate X coordinate.
   */
  public void setXCoordinate(int xCoordinate) {
    this.xCoordinate = xCoordinate;
  }

  /**
   * Gets the Y coordinate of this pixel on the canvas.
   *
   * @return Y coordinate.
   */
  public int getYCoordinate() {
    return yCoordinate;
  }

  /**
   * Sets the Y coordinate of this pixel on the canvas.
   *
   * @param yCoordinate Y coordinate.
   */
  public void setYCoordinate(int yCoordinate) {
    this.yCoordinate = yCoordinate;
  }

  /**
   * Gets the color value of this pixel (ARGB format).
   *
   * @return Color value as an integer.
   */
  public int getColorValue() {
    return colorValue;
  }

  /**
   * Sets the color value of this pixel (ARGB format).
   *
   * @param colorValue Color value as an integer.
   */
  public void setColorValue(int colorValue) {
    this.colorValue = colorValue;
  }

  /**
   * Gets the last modification timestamp for this pixel.
   *
   * @return Last modified timestamp.
   */
  @NonNull
  public Instant getLastModified() {
    return lastModified;
  }

  /**
   * Sets the last modification timestamp for this pixel.
   *
   * @param lastModified Last modified timestamp.
   */
  public void setLastModified(@NonNull Instant lastModified) {
    this.lastModified = lastModified;
  }
}
