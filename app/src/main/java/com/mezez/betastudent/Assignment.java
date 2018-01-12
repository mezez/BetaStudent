package com.mezez.betastudent;

/**
 * Created by Developer on 24-May-17.
 */

public class Assignment {

    private int id;
    private String title;
    private String description;

    public  Assignment(){

    }

    public Assignment(int id, String title, String description){
        this.id =  id;
        this.title = title;
        this.description = description;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getId(int id){
        return id;
    }

    public String getTitle(String title){
        return title;
    }

    public String getDescription(String description){
        return description;
    }
}
