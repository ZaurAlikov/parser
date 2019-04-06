package model.category.bagajnik;

import model.category.Category;

import java.util.ArrayList;
import java.util.List;

public class SkyFastenersBgjnk implements Category {

    private List<String> skyFasteners = new ArrayList<>();

    public SkyFastenersBgjnk() {
        skyFasteners.add("http://bagazhnik.ru/848510_kreplenie_dlya_perevozki_lyj_3_pary_lux_yeti");
    }

    @Override
    public List<String> getUrlList() {
        return skyFasteners;
    }
}
