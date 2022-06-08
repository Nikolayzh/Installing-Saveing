package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        String ROOT_PREFIX = "C:/";
        String GAME_DIR_PREFIX = ROOT_PREFIX + "Games/";

        StringBuilder logFile = new StringBuilder();
        // В папке Games создаем несколько папок: src, res, savegames, temp.
        String resDir = GAME_DIR_PREFIX + "res";
        String srcDir = GAME_DIR_PREFIX + "src";
        String savegamesDir = GAME_DIR_PREFIX + "savegames";
        String tempDir = GAME_DIR_PREFIX + "temp";
        createDirWithLogMessahe(logFile, srcDir);
        createDirWithLogMessahe(logFile, resDir);
        createDirWithLogMessahe(logFile, savegamesDir);
        createDirWithLogMessahe(logFile, tempDir);

        // В каталоге src создаю две папки: main, test.
        String mainDir = srcDir + File.separator + "main";
        String testDir = srcDir + File.separator + "test";
        createDirWithLogMessahe(logFile, mainDir);
        createDirWithLogMessahe(logFile, testDir);

        // В папке main создайте два файла: Main.java, Utils.java.
        String mainFile = mainDir + File.separator + "Main.java";
        String utilsFile = mainDir + File.separator + "Utils.java";

        createFile(mainFile, logFile);
        createFile(utilsFile, logFile);

        // В каталог res создайте три папки: drawables, vectors, icons.
        createDirWithLogMessahe(logFile, resDir + File.separator + "drawables");
        createDirWithLogMessahe(logFile, resDir + File.separator + "vectors");
        createDirWithLogMessahe(logFile, resDir + File.separator + "icons");

        // В папке temp создаю файл temp.txt.
        String tempFile = tempDir + File.separator + "temp.txt";
        //createFile(tempFile, logFile);

        // Файл temp.txt должен содержать информацию о том, что все файлы были созданы успешно.
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(tempFile))) {
            bufferedWriter.write(logFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirWithLogMessahe(StringBuilder logFile, String dirName) {
        File srcDir = new File(dirName);
        logFile
                .append("Directory ")
                .append(dirName)
                .append(srcDir.mkdir() ? " is" : " is not")
                .append(" " + "created\n");
    }

    private static void createFile(String fileName, StringBuilder logFile) {
        File newFile = new File(fileName);
        try {
            logFile
                    .append("File ")
                    .append(fileName)
                    .append(newFile.createNewFile() ? " is" : " is not")
                    .append(" created\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}