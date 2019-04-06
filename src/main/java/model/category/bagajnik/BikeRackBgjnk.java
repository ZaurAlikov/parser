package model.category.bagajnik;

import model.category.Category;

import java.util.ArrayList;
import java.util.List;

public class BikeRackBgjnk implements Category {

    private List<String> bikeRack = new ArrayList<>();

    public BikeRackBgjnk() {
        bikeRack.add("http://bagazhnik.ru/691028_lux_dlya_perevozki_velosipedov_universalnoe_luxbike_1_art._691028");
        bikeRack.add("http://bagazhnik.ru/846257_velokreplenie_lux_smart_art._846257");
        bikeRack.add("http://bagazhnik.ru/846240_velokreplenie_lux_profi_art._846240");
    }

    @Override
    public List<String> getUrlList() {
        return bikeRack;
    }
}
