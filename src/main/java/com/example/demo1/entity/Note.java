package com.example.demo1.entity;


import javax.persistence.*;

@Entity
@Table(name = "note_info")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    private String name;

    private String path;

    private String rate;

    public Note() {}

    public Note(String name, String path, String rate) {
        this.name = name;
        this.path = path;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

//    public Rate getRate() {
//        return rate;
//    }
//
//    public void setRate(Rate rate) {
//        this.rate = rate;
//    }
//
    public void setRate(String rate) {
        this.rate = String.valueOf(Rate.valueOf(rate)); // Отримання enum з рядкового значення
    }
}
