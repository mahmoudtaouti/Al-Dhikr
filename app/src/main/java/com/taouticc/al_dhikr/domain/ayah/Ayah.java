package com.taouticc.al_dhikr.domain.ayah;

import com.taouticc.al_dhikr.domain.hizb.Hizb;

public class Ayah {
    private int ayah_num;
    private int sorah_num;
    private String content;
    private Hizb hizb;


    public Ayah(int ayah_num, int sorah_num, String content, Hizb hizb) {
        this.ayah_num = ayah_num;
        this.sorah_num = sorah_num;
        this.content = content;
        this.hizb = hizb;
    }

    public int getAyah_num() {
        return ayah_num;
    }

    public void setAyah_num(int ayah_num) {
        this.ayah_num = ayah_num;
    }

    public int getSorah_num() {
        return sorah_num;
    }

    public void setSorah_num(int sorah_num) {
        this.sorah_num = sorah_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Hizb getHizb() {
        return hizb;
    }

    public void setHizb(Hizb hizb) {
        this.hizb = hizb;
    }

    @Override
    public String toString() {
        return "Ayah{" +
                "ayah_num=" + ayah_num +
                ", sorah_num=" + sorah_num +
                ", content='" + content + '\'' +
                ", hizb=" + hizb +
                '}';
    }
}
