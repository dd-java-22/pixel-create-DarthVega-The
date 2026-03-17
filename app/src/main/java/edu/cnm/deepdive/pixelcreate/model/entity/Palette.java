package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

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

  @ColumnInfo(name = "user_id")
  private long userId;

  @NonNull
  @ColumnInfo(name = "palette_name")
  private String paletteName = "";

  @ColumnInfo(name = "is_default")
  private boolean isDefault;

  @NonNull
  @ColumnInfo(name = "created_at")
  private Instant createdAt = Instant.now();

  @ColumnInfo(name = "last_used")
  private Instant lastUsed;

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
  public String getPaletteName() {
    return paletteName;
  }

  public void setPaletteName(@NonNull String paletteName) {
    this.paletteName = paletteName;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean isDefault) {
    this.isDefault = isDefault;
  }

  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getLastUsed() {
    return lastUsed;
  }

  public void setLastUsed(Instant lastUsed) {
    this.lastUsed = lastUsed;
  }
}
