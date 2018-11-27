package model.category.esauto;

import model.category.Category;

import java.util.ArrayList;
import java.util.List;

public class CarBoxes implements Category {

    private List<String> carBoxes = new ArrayList<>();

    public CarBoxes() {
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-force-l-xt/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-force-xt-m/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-force-xt-s/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-force-xt-sport/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-force-xl-xt/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xl-l/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xl-l/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-ocean-100/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-ocean-200/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-ocean-200/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-ocean-780/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-ocean-780/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-pacific-200/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-pacific-780/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-spirit-820/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-force-alpine-xt/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/atlant-diamond/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/atlant-diamond/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/inno-shadow-16-new/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/inno-shadow-16-new/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/inno-shadow-16-new/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/inno-shadow-14/");

//        carBoxes.add("https://es-auto.ru/car-boxes/sku/diamond-500/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/dynamic-l/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/dynamic-m/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/dynamic-m/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-320/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-320/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-320/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-400/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-400/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-400/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-580/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-580/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-l/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-l/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-m/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-m/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-s/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb751/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb751/Carbon/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb751/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb751/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb752/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb752/Carbon/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb752/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb752/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/airtek-435/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/airtek-505/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/diamond-450-duo/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/diamond-450-duo/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/diamond-500/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/diamond-500/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/diamond-500/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/diamond-500-duo-black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/discovery-classic-320/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/discovery-classic-430/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/discovery-classic-500/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/discovery-sport-431/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/discovery-sport-501/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/dynamic-l/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/dynamic-l/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/dynamic-m/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/dynamic-m/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/ficopro-carver-550/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/ficopro-ski-500/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/ficopro-zenith-390/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-320/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-400/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-460-duo/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-460-duo/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-460-duo/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-460-duo/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-580-duo/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-580-duo/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-580-duo/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/mania-580-duo/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/marathon-400/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/marathon-400/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/marathon-400/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/marathon-460/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/marathon-460/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/marathon-460/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-m/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-m/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-m/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-sport/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-sport/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-sport/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-xl/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-xl/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-xl/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-xxl/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-xxl/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/motion-xt-xxl/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/ranger-500/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/ranger-90/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-excellence-xt/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-excellence-xt/black-titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-excellence-xt/titanium-black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-flow/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-motion-xt-alpine/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-motion-xt-alpine/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/thule-motion-xt-alpine/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-alpine/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-alpine/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-alpine/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-l/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-l/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-m/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-m/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-s/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-s/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-sport/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-sport/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-sport/dark/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/touring-sport/titanium/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb751/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/wb752/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/whispbar-wb753/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/whispbar-wb753/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/whispbar-wb753/Carbon/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/whispbar-wb753/gray/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/whispbar-wb753/white/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/whispbar-wb754/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/whispbar-wb754/black/");
//        carBoxes.add("https://es-auto.ru/car-boxes/sku/whispbar-wb754/white/");
    }

    @Override
    public List<String> getUrlList() {
        return carBoxes;
    }
}
