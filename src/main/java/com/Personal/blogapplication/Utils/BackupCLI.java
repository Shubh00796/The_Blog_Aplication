package com.Personal.blogapplication.Utils;

import com.Personal.blogapplication.Service.BackupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BackupCLI implements CommandLineRunner {
    private final BackupService backupService;

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            log.info("Usage: backup-cli <command> [options]");
            return;
        }
        String command = args[0];
        switch (command) {
            case "backup":
                backupService.performBackup();
                break;
            case "restore":
                backupService.restoreBackup();
                break;
            default:
                System.out.println("Invalid command.");
        }
    }
}
