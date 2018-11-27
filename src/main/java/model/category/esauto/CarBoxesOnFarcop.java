package model.category.esauto;

import model.category.Category;

import java.util.ArrayList;
import java.util.List;

public class CarBoxesOnFarcop implements Category {

    private List<String> carBoxesOnFarcop = new ArrayList<>();

    public CarBoxesOnFarcop() {
//        carBoxesOnFarcop.add("https://es-auto.ru/car-boxes/sku/thule-xt-9383-backspace-on-the-tow-bar/");

//        carBoxesOnFarcop.add("https://es-auto.ru/car-boxes/sku/thule-backspace-9171-na-farkop/");
//        carBoxesOnFarcop.add("https://es-auto.ru/car-boxes/sku/thule-backup-900-na-farkop/");
//        carBoxesOnFarcop.add("https://es-auto.ru/car-boxes/sku/menabo-boxxy-na-farkop/");
    }

    @Override
    public List<String> getUrlList() {
        return carBoxesOnFarcop;
    }
}
