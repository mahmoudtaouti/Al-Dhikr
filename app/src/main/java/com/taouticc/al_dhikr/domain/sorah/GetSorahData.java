package com.taouticc.al_dhikr.domain.sorah;

import com.taouticc.al_dhikr.domain.ayah.Ayah;

import java.util.ArrayList;

public interface GetSorahData {

    ArrayList<Ayah> getAyaatOfSorah(int sorahNum);
    ArrayList<Ayah> getBooksMarksAyaatOfSorah(int sorahNum);
    SorahInfo getSorahInfo(int sorahNum);

}
