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
 * Represents a periodic backup snapshot of a project's state. Used to prevent data loss
 * and enable recovery functionality.
 */
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

  @ColumnInfo(name = "project_id", index = true)
  private long projectId;

  @NonNull
  @ColumnInfo(name = "snapshot_data", typeAffinity = ColumnInfo.BLOB)
  private byte[] snapshotData;

  @NonNull
  @ColumnInfo(name = "created_at")
  private Instant createdAt;

  @ColumnInfo(name = "file_size")
  private int fileSize;

  /**
   * Gets the unique identifier for this autosave snapshot.
   *
   * @return Snapshot ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for this autosave snapshot.
   *
   * @param id Snapshot ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID of the project this snapshot belongs to.
   *
   * @return Project ID.
   */
  public long getProjectId() {
    return projectId;
  }

  /**
   * Sets the ID of the project this snapshot belongs to.
   *
   * @param projectId Project ID.
   */
  public void setProjectId(long projectId) {
    this.projectId = projectId;
  }

  /**
   * Gets the serialized snapshot data (JSON or binary format).
   *
   * @return Snapshot data as byte array.
   */
  @NonNull
  public byte[] getSnapshotData() {
    return snapshotData;
  }

  /**
   * Sets the serialized snapshot data.
   *
   * @param snapshotData Snapshot data as byte array.
   */
  public void setSnapshotData(@NonNull byte[] snapshotData) {
    this.snapshotData = snapshotData;
  }

  /**
   * Gets the snapshot creation timestamp.
   *
   * @return Creation timestamp.
   */
  @NonNull
  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the snapshot creation timestamp.
   *
   * @param createdAt Creation timestamp.
   */
  public void setCreatedAt(@NonNull Instant createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Gets the file size of the snapshot data in bytes.
   *
   * @return File size in bytes.
   */
  public int getFileSize() {
    return fileSize;
  }

  /**
   * Sets the file size of the snapshot data in bytes.
   *
   * @param fileSize File size in bytes.
   */
  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }
}
