package com.taouticc.al_dhikr.domain.hizb;

public class Hizb {

    private int hizb_num;

    public Hizb(int hizb_num) {
        this.hizb_num = hizb_num;
    }

    public int getHizb_num() {
        return hizb_num;
    }

    public void setHizb_num(int hizb_num) {
        this.hizb_num = hizb_num;
    }

    @Override
    public String toString() {
        return "Hizb{" +
                "hizb_num=" + hizb_num +
                '}';
    }
}