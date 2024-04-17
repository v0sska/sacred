package com.example.demo1.interfaces;

import javafx.scene.control.TextArea;

import java.io.IOException;

public interface IReader {

    void writeAndSave(String note, String name, String path) throws IOException;

    String createMainDirectory(String path);

    String createNoteDirectory(String mainPath, String noteName);

    void saveConfigMemoryFile(String path) throws IOException;

    static String readConfigMemoryFile() {
        return null;
    }

    void openNote(String path, String name, TextArea textArea);

}
