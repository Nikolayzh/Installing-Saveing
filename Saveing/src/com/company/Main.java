package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    private static final String FILE_PATH = "C:" + File.separator + "Games" + File.separator +
            "savegames" + File.separator;

    public static void main(String[] args) {
        // Создать три экземпляра класса GameProgress.
        GameProgress g1 = new GameProgress(1, 1, 1, 1.1);
        GameProgress g2 = new GameProgress(2, 2, 3, 2.2);
        GameProgress g3 = new GameProgress(3, 3, 3, 3.3);


        var g1file = "g1.dat";
        var g2file = "g2.dat";
        var g3file = "g3.dat";

        saveProgress(g1, FILE_PATH + g1file);
        saveProgress(g2, FILE_PATH + g2file);
        saveProgress(g3, FILE_PATH + g3file);

        // упакуем в архив zip.
        zipFiles(FILE_PATH + "gameprogress.zip");
    }

    private static void zipFiles(String zipFile) {
        File path = new File(zipFile);
        // Get folder path
        File folder = new File(path.getParent());
        // Get all files in folder before any zipping
        File[] files = folder.listFiles();

        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            assert files != null;
            for (File file : files) {
                if (file.isFile() && !file.getName().endsWith(".zip")) {
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry entry = new ZipEntry(file.getName());
                    zipOut.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zipOut.write(buffer);
                    zipOut.closeEntry();
                    fis.close();

                    // Удалить файлы не в архиве.
                    file.delete();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveProgress(GameProgress gameProgress, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}