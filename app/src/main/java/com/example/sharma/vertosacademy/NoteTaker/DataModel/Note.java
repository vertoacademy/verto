package com.example.sharma.vertosacademy.NoteTaker.DataModel;

/**
 * Created by Ashish Kumar Satyam on 4/5/2017.
 */

public class Note {
    private long Id;
    private String title;
    private String text;

    /**
     * Constructor without parameters.
     */
    public Note (){
        super();
    }

    /**
     * Constructor with parameters.
     * @param title
     * @param text
     */
    public Note(String title, String text) {
        this.title = title;
        this.text = text;
        //  this.date = date;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
