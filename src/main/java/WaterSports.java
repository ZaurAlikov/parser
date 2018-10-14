import java.util.ArrayList;
import java.util.List;

public class WaterSports implements Category {

    private List<String> waterSports = new ArrayList<>();

    public WaterSports() {
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/k-guard/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/niagara/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/wb400/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/wb401/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/hull-a-port/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/hull-a-port-pro/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/kayak-support/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/kreplenie-thule-579-canoe-carrier-dlya-perevozki-kanoe/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/kreplenie-thule-832-dlya-perevozok-dosok-dlya-serfinga/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/kreplenie-thule-855-dlya-perevozki-vesel-i-macht/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/kreplenie-thule-873-hydroglide-dlya-perevozki-kayaka/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/kreplenie-thule-874-kayak-carrier-dlya-perevozki-kayaka/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/kreplenie-thule-sup-taxi-810-dlya-perevozki-doski-dlya-serfinga/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/kreplenie-yakima-sweetroll-dlya-perevozki-lodok-i-baydarok/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/krepleniya-thule-533-dlya-perevozki-serfinga/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/krepleniya-thule-833-dlya-perevozki-dosok-dlya-serfinga/");
        waterSports.add("https://es-auto.ru/water-sports-eq/sku/krepleniya-yakima-dlya-lodok-dosok-dlya-serfinga-i-kayakov/");
    }

    @Override
    public List<String> getUrlList() {
        return waterSports;
    }
}
