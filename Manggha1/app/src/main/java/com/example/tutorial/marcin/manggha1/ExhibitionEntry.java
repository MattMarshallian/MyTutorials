package com.example.tutorial.marcin.manggha1;

/**
 * Created by Marcin on 23.02.2016.
 */
public class ExhibitionEntry {
    public int imageResId;
    public int titleResId;
    public String description = "";

    public ExhibitionEntry(int imageResId, int titleResId, String description){
        this.imageResId = imageResId;
        this.titleResId = titleResId;
        this.description = description;
    }
}
