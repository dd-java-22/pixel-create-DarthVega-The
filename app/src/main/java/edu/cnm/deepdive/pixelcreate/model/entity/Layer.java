package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

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

  @ColumnInfo(name = "project_id")
  private long projectId;

  @NonNull
  @ColumnInfo(name = "layer_name")
  private String layerName = "";

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
  private Instant createdAt = Instant.now();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getProjectId() {
    return projectId;
  }

  public void setProjectId(long projectId) {
    this.projectId = projectId;
  }

  @NonNull
  public String getLayerName() {
    return layerName;
  }

  public void setLayerName(@NonNull String layerName) {
    this.layerName = layerName;
  }

  public int getLayerOrder() {
    return layerOrder;
  }

  public void setLayerOrder(int layerOrder) {
    this.layerOrder = layerOrder;
  }

  public boolean isVisible() {
    return isVisible;
  }

  public void setVisible(boolean visible) {
    isVisible = visible;
  }

  public boolean isLocked() {
    return isLocked;
  }

  public void setLocked(boolean locked) {
    isLocked = locked;
  }

  public float getOpacity() {
    return opacity;
  }

  public void setOpacity(float opacity) {
    this.opacity = opacity;
  }

  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }
}
