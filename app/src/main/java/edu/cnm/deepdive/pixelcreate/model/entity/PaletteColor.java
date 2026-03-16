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

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Represents an individual color within a palette. Each palette color has a color value,
 * display order, and optional user-defined name.
 */
@Entity(
    tableName = "palette_color",
    indices = {
        @Index(value = "palette_id"),
        @Index(value = {"palette_id", "color_order"}, unique = true)
    },
    foreignKeys = {
        @ForeignKey(
            entity = Palette.class,
            parentColumns = "palette_id",
            childColumns = "palette_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class PaletteColor {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "palette_color_id")
  private long id;

  @ColumnInfo(name = "palette_id", index = true)
  private long paletteId;

  @ColumnInfo(name = "color_value")
  private int colorValue;

  @ColumnInfo(name = "color_order")
  private int colorOrder;

  @ColumnInfo(name = "color_name")
  private String colorName;

  /**
   * Gets the unique identifier for this palette color.
   *
   * @return Palette color ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for this palette color.
   *
   * @param id Palette color ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID of the palette this color belongs to.
   *
   * @return Palette ID.
   */
  public long getPaletteId() {
    return paletteId;
  }

  /**
   * Sets the ID of the palette this color belongs to.
   *
   * @param paletteId Palette ID.
   */
  public void setPaletteId(long paletteId) {
    this.paletteId = paletteId;
  }

  /**
   * Gets the color value (ARGB format).
   *
   * @return Color value as an integer.
   */
  public int getColorValue() {
    return colorValue;
  }

  /**
   * Sets the color value (ARGB format).
   *
   * @param colorValue Color value as an integer.
   */
  public void setColorValue(int colorValue) {
    this.colorValue = colorValue;
  }

  /**
   * Gets the display order of this color within the palette.
   *
   * @return Color order (position in the palette).
   */
  public int getColorOrder() {
    return colorOrder;
  }

  /**
   * Sets the display order of this color within the palette.
   *
   * @param colorOrder Color order (position in the palette).
   */
  public void setColorOrder(int colorOrder) {
    this.colorOrder = colorOrder;
  }

  /**
   * Gets the optional user-defined name for this color.
   *
   * @return Color name (e.g., "Sky Blue"), or {@code null} if no name is set.
   */
  public String getColorName() {
    return colorName;
  }

  /**
   * Sets the optional user-defined name for this color.
   *
   * @param colorName Color name (e.g., "Sky Blue").
   */
  public void setColorName(String colorName) {
    this.colorName = colorName;
  }
}
