package edu.cnm.deepdive.pixelcreate.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

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

  @ColumnInfo(name = "project_id")
  private long projectId;

  @NonNull
  @ColumnInfo(name = "file_name")
  private String fileName = "";

  @NonNull
  @ColumnInfo(name = "file_path")
  private String filePath = "";

  @NonNull
  @ColumnInfo(name = "file_format")
  private String fileFormat = "";

  @ColumnInfo(name = "resolution")
  private int resolution;

  @ColumnInfo(name = "scale_factor")
  private int scaleFactor;

  @NonNull
  @ColumnInfo(name = "exported_at")
  private Instant exportedAt = Instant.now();

  @ColumnInfo(name = "file_size_bytes")
  private int fileSizeBytes;

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
  public String getFileName() {
    return fileName;
  }

  public void setFileName(@NonNull String fileName) {
    this.fileName = fileName;
  }

  @NonNull
  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(@NonNull String filePath) {
    this.filePath = filePath;
  }

  @NonNull
  public String getFileFormat() {
    return fileFormat;
  }

  public void setFileFormat(@NonNull String fileFormat) {
    this.fileFormat = fileFormat;
  }

  public int getResolution() {
    return resolution;
  }

  public void setResolution(int resolution) {
    this.resolution = resolution;
  }

  public int getScaleFactor() {
    return scaleFactor;
  }

  public void setScaleFactor(int scaleFactor) {
    this.scaleFactor = scaleFactor;
  }

  @NonNull
  public Instant getExportedAt() {
    return exportedAt;
  }

  public void setExportedAt(@NonNull Instant exportedAt) {
    this.exportedAt = exportedAt;
  }

  public int getFileSizeBytes() {
    return fileSizeBytes;
  }

  public void setFileSizeBytes(int fileSizeBytes) {
    this.fileSizeBytes = fileSizeBytes;
  }
}
