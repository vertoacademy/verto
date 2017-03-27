package com.example.sharma.vertosacademy;

/**
 * Created by sharma on 3/3/2017.
 */

public class ProgramDataPicker {

    private String title;
    private String id;

    public ProgramDataPicker() {
        this.title = title;
    }

    public ProgramDataPicker(String title, String id) {
        this.title = title;
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public String getId() {
        return id;
    }

}
