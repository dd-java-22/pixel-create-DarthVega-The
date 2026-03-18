package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

@Entity(
    tableName = "autosave_snapshot",
    indices = {
        @Index(value = "project_id"),
        @Index(value = {"project_id", "created_at"})
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
public class AutosaveSnapshot {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "autosave_snapshot_id")
  private long id;

  @ColumnInfo(name = "project_id")
  private long projectId;

  @NonNull
  @ColumnInfo(name = "snapshot_data", typeAffinity = ColumnInfo.BLOB)
  private byte[] snapshotData = new byte[0];

  @NonNull
  @ColumnInfo(name = "created_at")
  private Instant createdAt = Instant.now();

  @ColumnInfo(name = "file_size")
  private int fileSize;

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
  public byte[] getSnapshotData() {
    return snapshotData;
  }

  public void setSnapshotData(@NonNull byte[] snapshotData) {
    this.snapshotData = snapshotData;
  }

  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }

  public int getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }
}
