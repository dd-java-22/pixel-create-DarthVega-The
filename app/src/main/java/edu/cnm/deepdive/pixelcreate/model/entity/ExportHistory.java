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
 * Represents a record of an exported image. Logs export operations including file details,
 * format, resolution, and export timestamp for tracking and re-export functionality.
 */
@Entity(
    tableName = "export_history",
    indices = {
        @Index(value = "project_id"),
        @Index(value = {"project_id", "exported_at"})
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
public class ExportHistory {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "export_history_id")
  private long id;

  @ColumnInfo(name = "project_id", index = true)
  private long projectId;

  @NonNull
  @ColumnInfo(name = "file_name")
  private String fileName;

  @NonNull
  @ColumnInfo(name = "file_path")
  private String filePath;

  @NonNull
  @ColumnInfo(name = "file_format")
  private String fileFormat;

  @ColumnInfo(name = "resolution")
  private int resolution;

  @ColumnInfo(name = "scale_factor")
  private int scaleFactor;

  @NonNull
  @ColumnInfo(name = "exported_at")
  private Instant exportedAt;

  @ColumnInfo(name = "file_size_bytes")
  private int fileSizeBytes;

  /**
   * Gets the unique identifier for this export history record.
   *
   * @return Export history ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for this export history record.
   *
   * @param id Export history ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID of the project that was exported.
   *
   * @return Project ID.
   */
  public long getProjectId() {
    return projectId;
  }

  /**
   * Sets the ID of the project that was exported.
   *
   * @param projectId Project ID.
   */
  public void setProjectId(long projectId) {
    this.projectId = projectId;
  }

  /**
   * Gets the exported file name.
   *
   * @return File name.
   */
  @NonNull
  public String getFileName() {
    return fileName;
  }

  /**
   * Sets the exported file name.
   *
   * @param fileName File name.
   */
  public void setFileName(@NonNull String fileName) {
    this.fileName = fileName;
  }

  /**
   * Gets the file path where the export was saved.
   *
   * @return File path.
   */
  @NonNull
  public String getFilePath() {
    return filePath;
  }

  /**
   * Sets the file path where the export was saved.
   *
   * @param filePath File path.
   */
  public void setFilePath(@NonNull String filePath) {
    this.filePath = filePath;
  }

  /**
   * Gets the file format (e.g., "PNG", "JPG").
   *
   * @return File format.
   */
  @NonNull
  public String getFileFormat() {
    return fileFormat;
  }

  /**
   * Sets the file format.
   *
   * @param fileFormat File format (e.g., "PNG", "JPG").
   */
  public void setFileFormat(@NonNull String fileFormat) {
    this.fileFormat = fileFormat;
  }

  /**
   * Gets the export resolution in pixels.
   *
   * @return Resolution.
   */
  public int getResolution() {
    return resolution;
  }

  /**
   * Sets the export resolution in pixels.
   *
   * @param resolution Resolution.
   */
  public void setResolution(int resolution) {
    this.resolution = resolution;
  }

  /**
   * Gets the scale factor applied during export.
   *
   * @return Scale factor (e.g., 1x, 2x, 4x).
   */
  public int getScaleFactor() {
    return scaleFactor;
  }

  /**
   * Sets the scale factor applied during export.
   *
   * @param scaleFactor Scale factor (e.g., 1x, 2x, 4x).
   */
  public void setScaleFactor(int scaleFactor) {
    this.scaleFactor = scaleFactor;
  }

  /**
   * Gets the export timestamp.
   *
   * @return Export timestamp.
   */
  @NonNull
  public Instant getExportedAt() {
    return exportedAt;
  }

  /**
   * Sets the export timestamp.
   *
   * @param exportedAt Export timestamp.
   */
  public void setExportedAt(@NonNull Instant exportedAt) {
    this.exportedAt = exportedAt;
  }

  /**
   * Gets the exported file size in bytes.
   *
   * @return File size in bytes.
   */
  public int getFileSizeBytes() {
    return fileSizeBytes;
  }

  /**
   * Sets the exported file size in bytes.
   *
   * @param fileSizeBytes File size in bytes.
   */
  public void setFileSizeBytes(int fileSizeBytes) {
    this.fileSizeBytes = fileSizeBytes;
  }
}
