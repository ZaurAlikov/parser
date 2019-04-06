package model.category.bagajnik;

import model.category.Category;

import java.util.ArrayList;
import java.util.List;

public class CargoBasketsBgjnk implements Category {

    private List<String> cargoBaskets = new ArrayList<>();

    public CargoBasketsBgjnk() {
        cargoBaskets.add("http://bagazhnik.ru/845205_korzina_bagajnaya_lux_rayder_1200h950mm_art._845205");
        cargoBaskets.add("http://bagazhnik.ru/845212_korzina_bagajnaya_lux_ekselent_1600h1000_art._845212");
    }

    @Override
    public List<String> getUrlList() {
        return cargoBaskets;
    }
}
