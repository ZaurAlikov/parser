import java.util.ArrayList;
import java.util.List;

public class CargoBaskets implements Category {
    private List<String> cargoBaskets = new ArrayList<>();

    public CargoBaskets() {
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/canyon-859/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/trail-823/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/trail-824/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/yellowstone/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/adapter-udlinitel-thule-canyon-859/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/korzina-atlant-100x90-sm/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/korzina-atlant-120x70-sm/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/korzina-atlant-120x80-sm/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/korzina-atlant-130x90-sm/");
        cargoBaskets.add("https://es-auto.ru/cargo-baskets/sku/xperience-828/");
    }

    @Override
    public List<String> getUrlList() {
        return cargoBaskets;
    }
}
