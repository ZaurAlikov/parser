import java.util.ArrayList;
import java.util.List;

public class SkyFasteners implements Category {

    private List<String> skyFasteners = new ArrayList<>();

    public SkyFasteners() {
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/aconcagua/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/fatcat/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/frozen-alu/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/himalaya/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/iceberg/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/igloo/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/silver-ice/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/skiclick/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/ski-rack/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/viking/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/wb300/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/white-bear-4/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/white-bear-6/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/xtender-739/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/bagazhnik-dlya-3-kh-par-lyzh-amos/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/bagazhnik-dlya-4-kh-par-lyzh-atlant-8550/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/bagazhnik-dlya-5-ti-par-lyzh-amos/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/bagazhnik-dlya-6-kh-par-lyzh-atlant-8551/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/snowpack-7322/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/snowpack-7324/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/snowpack-7326/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/thule-snowpack-extender/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/amos-ski-lock-3/silver/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/amos-ski-lock-3/black/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/amos-ski-lock-5/silver/");
        skyFasteners.add("https://es-auto.ru/ski-fasteners/sku/amos-ski-lock-5/black/");
    }

    @Override
    public List<String> getUrlList() {
        return skyFasteners;
    }
}
