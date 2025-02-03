package com.Personal.blogapplication.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressionService {
    public static String compressFile(String filePath) throws IOException {
        String compressedFile = filePath + ".zip";
        FileOutputStream fos = new FileOutputStream(compressedFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(filePath);

        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
        zipOut.close();
        fos.close();
        return compressedFile;
    }

}
