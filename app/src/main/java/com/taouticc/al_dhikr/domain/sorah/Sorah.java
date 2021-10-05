package com.taouticc.al_dhikr.domain.sorah;

import com.taouticc.al_dhikr.domain.ayah.Ayah;

import java.util.ArrayList;

public class Sorah {

    private SorahInfo info;
    private ArrayList<Ayah> ayaat;
    ArrayList<Ayah> bookMarksAyaat;



    public SorahInfo getInfo() {
        return info;
    }

    public void setInfo(SorahInfo info) {
        this.info = info;
    }

    public ArrayList<Ayah> getAyaat() {
        return ayaat;
    }

    public void setAyaat(ArrayList<Ayah> ayaat) {
        this.ayaat = ayaat;
    }

    public ArrayList<Ayah> getBookMarksAyaat() {
        return bookMarksAyaat;
    }

    public void setBookMarksAyaat(ArrayList<Ayah> bookMarksAyaat) {
        this.bookMarksAyaat = bookMarksAyaat;
    }

}
