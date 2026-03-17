package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

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

  @ColumnInfo(name = "user_id")
  private long userId;

  @NonNull
  @ColumnInfo(name = "project_name")
  private String projectName = "";

  @ColumnInfo(name = "canvas_width")
  private int canvasWidth;

  @ColumnInfo(name = "canvas_height")
  private int canvasHeight;

  @NonNull
  @ColumnInfo(name = "created_at")
  private Instant createdAt = Instant.now();

  @NonNull
  @ColumnInfo(name = "last_edited_at")
  private Instant lastEditedAt = Instant.now();

  @ColumnInfo(name = "thumbnail_image", typeAffinity = ColumnInfo.BLOB)
  private byte[] thumbnailImage;

  @ColumnInfo(name = "is_deleted")
  private boolean isDeleted;

  @ColumnInfo(name = "tags")
  private String tags;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  @NonNull
  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(@NonNull String projectName) {
    this.projectName = projectName;
  }

  public int getCanvasWidth() {
    return canvasWidth;
  }

  public void setCanvasWidth(int canvasWidth) {
    this.canvasWidth = canvasWidth;
  }

  public int getCanvasHeight() {
    return canvasHeight;
  }

  public void setCanvasHeight(int canvasHeight) {
    this.canvasHeight = canvasHeight;
  }

  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }

  @NonNull
  public Instant getLastEditedAt() {
    return lastEditedAt;
  }

  public void setLastEditedAt(@NonNull Instant lastEditedAt) {
    this.lastEditedAt = lastEditedAt;
  }

  public byte[] getThumbnailImage() {
    return thumbnailImage;
  }

  public void setThumbnailImage(byte[] thumbnailImage) {
    this.thumbnailImage = thumbnailImage;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean deleted) {
    isDeleted = deleted;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }
}
