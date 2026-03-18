package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

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

  @ColumnInfo(name = "layer_id")
  private long layerId;

  @ColumnInfo(name = "x_coordinate")
  private int xCoordinate;

  @ColumnInfo(name = "y_coordinate")
  private int yCoordinate;

  @ColumnInfo(name = "color_value")
  private int colorValue;

  @NonNull
  @ColumnInfo(name = "last_modified")
  private Instant lastModified = Instant.now();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getLayerId() {
    return layerId;
  }

  public void setLayerId(long layerId) {
    this.layerId = layerId;
  }

  public int getXCoordinate() {
    return xCoordinate;
  }

  public void setXCoordinate(int xCoordinate) {
    this.xCoordinate = xCoordinate;
  }

  public int getYCoordinate() {
    return yCoordinate;
  }

  public void setYCoordinate(int yCoordinate) {
    this.yCoordinate = yCoordinate;
  }

  public int getColorValue() {
    return colorValue;
  }

  public void setColorValue(int colorValue) {
    this.colorValue = colorValue;
  }

  @NonNull
  public Instant getLastModified() {
    return lastModified;
  }

  public void setLastModified(@NonNull Instant lastModified) {
    this.lastModified = lastModified;
  }
}
