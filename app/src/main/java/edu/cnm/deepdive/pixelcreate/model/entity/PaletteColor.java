package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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

  @ColumnInfo(name = "palette_id")
  private long paletteId;

  @ColumnInfo(name = "color_value")
  private int colorValue;

  @ColumnInfo(name = "color_order")
  private int colorOrder;

  @ColumnInfo(name = "color_name")
  private String colorName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPaletteId() {
    return paletteId;
  }

  public void setPaletteId(long paletteId) {
    this.paletteId = paletteId;
  }

  public int getColorValue() {
    return colorValue;
  }

  public void setColorValue(int colorValue) {
    this.colorValue = colorValue;
  }

  public int getColorOrder() {
    return colorOrder;
  }

  public void setColorOrder(int colorOrder) {
    this.colorOrder = colorOrder;
  }

  public String getColorName() {
    return colorName;
  }

  public void setColorName(String colorName) {
    this.colorName = colorName;
  }
}
