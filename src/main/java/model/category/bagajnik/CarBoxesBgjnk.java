package model.category.bagajnik;

import model.category.Category;

import java.util.ArrayList;
import java.util.List;

public class CarBoxesBgjnk implements Category {

    private List<String> carBoxes = new ArrayList<>();

    public CarBoxesBgjnk() {
        carBoxes.add("http://bagazhnik.ru/844109_boks_lux_flagman_370l_seryy_matovyy_1392h902h392_s_dvustor._otkr._art._844109");
        carBoxes.add("http://bagazhnik.ru/844086_boks_lux_flagman_370l_chernyy_matovyy_1392h902h392_s_dvustor._otkr._art._844086");
        carBoxes.add("http://bagazhnik.ru/694999_boks_lux_600_440l_seryy_matovyy_1600h920h400_s_dvustor._otkr._art._694999");
        carBoxes.add("http://bagazhnik.ru/694982_boks_lux_600_440l_chernyy_matovyy_1600h920h400_s_dvustor._otkr._art._694982");
        carBoxes.add("http://bagazhnik.ru/844154_boks_lux_viking_460l_seryy_matovyy_1737h812h412_s_dvustor._otkr._art._844154");
        carBoxes.add("http://bagazhnik.ru/844130_boks_lux_viking_460l_chernyy_matovyy_1737h812h412_s_dvustor._otkr._art._844130");
        carBoxes.add("http://bagazhnik.ru/844093_boks_lux_flagman_370l_chernyy_metallik_1392h902h392_s_dvustor._otkr._art._844093");
        carBoxes.add("http://bagazhnik.ru/844116_boks_lux_flagman_370l_seryy_metallik_1392h902h392_s_dvustor._otkr.._art._844116");
        carBoxes.add("http://bagazhnik.ru/844123_boks_lux_flagman_370l_belyy_glyanets_1392h902h392_s_dvustor._otkr._art._844123");
        carBoxes.add("http://bagazhnik.ru/695163_boks_lux_600_440l_seryy_metallik_1600h920h400_s_dvustor._otkr._art._695163");
        carBoxes.add("http://bagazhnik.ru/695156_boks_lux_600_440l_chernyy_metallik_1600h920h400_s_dvustor._otkr._art._695156");
        carBoxes.add("http://bagazhnik.ru/694951_boks_lux_960_480l_seryy_matovyy_1960h780h420_s_dvustor._otkr._art._694951");
        carBoxes.add("http://bagazhnik.ru/694975_boks_lux_960_480l_chernyy_matovyy_1960h780h420_s_dvustor._otkr._art._694975");
        carBoxes.add("http://bagazhnik.ru/844161_boks_lux_viking_460l_seryy_metallik_1737h812h412_s_dvustor._otkr._art._844161");
        carBoxes.add("http://bagazhnik.ru/844147_boks_lux_viking_460l_chernyy_metallik_1737h812h412_s_dvustor._otkr._art._844147");
        carBoxes.add("http://bagazhnik.ru/697136_boks_lux_600_440l_belyy_glyanets_1600h920h400_s_dvustor._otkr._art._697136");
        carBoxes.add("http://bagazhnik.ru/844178_boks_lux_viking_460l_belyy_glyanets_1737h812h412_s_dvustor._otkr._art._844178");
        carBoxes.add("http://bagazhnik.ru/695170_boks_lux_960_480l_chernyy_metallik_1960h780h420_s_dvustor._otkr._art._695170");
        carBoxes.add("http://bagazhnik.ru/695187_boks_lux_960_480l_seryy_metallik_1960h780h420_s_dvustor._otkr._art._695187");
        carBoxes.add("http://bagazhnik.ru/697624_boks_lux_960_480l_belyy_glyanets_1960h780h420_s_dvustor._otkr._art._697624");
    }

    @Override
    public List<String> getUrlList() {
        return carBoxes;
    }
}
