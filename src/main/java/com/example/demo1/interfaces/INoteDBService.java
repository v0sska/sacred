package com.example.demo1.interfaces;

import com.example.demo1.entity.Note;

import java.util.List;

public interface INoteDBService {

    void saveNote(Note note);

    List<String> getNoteName();

    String getNotePathByName(String name);

    void clearData();

    boolean noteExists(String path);

    Long getCountByGoodRate();

    Long getCountByNormalRate();

    Long getCountByBadRate();

    List<String> getNoteNameByGoodRate();

    List<String> getNoteNameByNormalRate();

    List<String> getNoteNameByBadRate();

}
