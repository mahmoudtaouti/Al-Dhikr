package com.taouticc.al_dhikr.domain.sorah;

public class SorahInfo{

    private int sorah_num;
    private String name;
    private int ayah_count;
    private String tanzil_place;
    private int tanzil_order;

    public SorahInfo(int sorah_num, String name, int ayah_count, String tanzil_place, int tanzil_order) {
        this.sorah_num = sorah_num;
        this.name = name;
        this.ayah_count = ayah_count;
        this.tanzil_place = tanzil_place;
        this.tanzil_order = tanzil_order;
    }

    public int getSorah_num() {
        return sorah_num;
    }

    public void setSorah_num(int sorah_num) {
        this.sorah_num = sorah_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAyah_count() {
        return ayah_count;
    }

    public void setAyah_count(int ayah_count) {
        this.ayah_count = ayah_count;
    }

    public String getTanzil_place() {
        return tanzil_place;
    }

    public void setTanzil_place(String tanzil_place) {
        this.tanzil_place = tanzil_place;
    }

    public int getTanzil_order() {
        return tanzil_order;
    }

    public void setTanzil_order(int tanzil_order) {
        this.tanzil_order = tanzil_order;
    }

    @Override
    public String toString() {
        return "Sorah{" +
                "sorah_num=" + sorah_num +
                ", name='" + name + '\'' +
                ", ayah_count=" + ayah_count +
                ", tanzil_place='" + tanzil_place + '\'' +
                ", tanzil_order=" + tanzil_order +
                '}';
    }
}
