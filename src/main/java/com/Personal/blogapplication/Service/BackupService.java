package com.Personal.blogapplication.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BackupService {
    private final LoggingService loggingService;



    public void performBackup() {
        try {
            String backupFile = createBackup();
            String compressedFile = CompressionService.compressFile(backupFile);
            saveBackupLocally(compressedFile);  // Save the backup locally
            loggingService.log("Backup completed successfully.");
        } catch (Exception e) {
            loggingService.log("Backup failed: " + e.getMessage());
            throw new RuntimeException("Backup operation failed.", e);
        }
    }

    private String createBackup() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "mysqldump", "-u", "root", "-pPASSWORD", "mydatabase", "--result-file=backup.sql");
        Process process = processBuilder.start();
        return "backup.sql";
    }

    private void saveBackupLocally(String backupFilePath) {
        // Updated path for saving backups on Desktop (Windows)
        File backupFile = new File(backupFilePath);
        File destination = new File("C:/Users/shuvijay/Desktop/Backups/" + backupFile.getName());

        if (!destination.exists()) {
            destination.getParentFile().mkdirs();  // Create the directory if it doesn't exist
        }

        boolean moved = backupFile.renameTo(destination);
        if (!moved) {
            throw new RuntimeException(("Failed to save backup locally."));
        }
    }

    public void restoreBackup() {
        // Implement restore logic here (if needed)
    }
}
