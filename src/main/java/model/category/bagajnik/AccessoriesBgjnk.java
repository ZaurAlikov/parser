package model.category.bagajnik;

import model.category.Category;

import java.util.ArrayList;
import java.util.List;

public class AccessoriesBgjnk implements Category {

    private List<String> accessories = new ArrayList<>();

    public AccessoriesBgjnk() {
        accessories.add("http://bagazhnik.ru/690298_stopor_muravey_2_sht._art._690298");
        accessories.add("http://bagazhnik.ru/694494_nabor_boltov_sekretnyh_bagajnoy_sistemy_lux_50_mm_art._694494");
        accessories.add("http://bagazhnik.ru/849104_nabor_perehodnikov_dlya_ustanovki_lk_lux_yeti_na_pryamougolnye_poperechiny");
        accessories.add("http://bagazhnik.ru/843157_nabor_lichinok_s_klyuchami_bagajnoy_sistemy_lux_4_sht._art._843157");
        accessories.add("http://bagazhnik.ru/zamki_dlya_boksa_lux_2_sht_zamki_dlya_boksa_lux_2_sht");
        accessories.add("http://bagazhnik.ru/690304_kreplenie_dlya_perevozki_lodok_muravey_2_sht._art._690304");
        accessories.add("http://bagazhnik.ru/693527_stopor_dlya_fiksatsii_gruzov_lux_vysota_9_sm_komplekt_2_sht._art._693527");
        accessories.add("http://bagazhnik.ru/845588_sumka_dlya_boksa_lux");
    }

    @Override
    public List<String> getUrlList() {
        return accessories;
    }
}
