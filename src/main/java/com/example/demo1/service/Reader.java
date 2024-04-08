package com.example.demo1.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private File files;

    public void writeAndSave(String note, String name, String path) throws IOException {

        files = new File(path + "/" + name + ".txt");

        files.createNewFile();

        FileOutputStream stream = new FileOutputStream(files);

        stream.write(note.getBytes(StandardCharsets.UTF_8));

        stream.close();
    }


    public String createMainDirectory(String path){
        String mainDirectory = path + "/" + "sacred";

        File file = new File(mainDirectory);

        file.mkdir();

        return mainDirectory;
    }

    public String createNoteDirectory(String mainPath, String noteName){
        String noteDirectory = mainPath + "/" + noteName;

        File file = new File(noteDirectory);

        file.mkdir();

        return noteDirectory;
    }

    public void saveConfigMemoryFile(String path) throws IOException {
        String configReplacementPath = "src/main/java/com/example/demo1";

        File config = new File(configReplacementPath + "/" + "memory_path_config.txt");

        config.createNewFile();

        FileOutputStream stream = new FileOutputStream(config);

        stream.write(path.getBytes(StandardCharsets.UTF_8));

        stream.close();
    }

    public static String readConfigMemoryFile() {
        File file = new File("src/main/java/com/example/demo1/memory_path_config.txt");

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line.trim()); // Зчитуємо і видаляємо зайві пробіли
                }
                return stringBuilder.toString(); // Повертаємо зчитаний шлях як рядок
            } catch (IOException e) {
                e.printStackTrace(); // Обробляємо виняток відповідно до потреб вашої програми
            }
        }
        return null; // Повертаємо null, якщо файл не існує або виникла помилка під час зчитування
    }


}
