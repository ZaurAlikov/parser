import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static void main(String[] args) throws IOException {
        String url = "https://es-auto.ru/car-trunks/sku/komplekt_dug_135_sm_dlya_avt_bagazhnika_thule_2_sht_/";
        System.out.println("Fetching %s..." + url);
        Document doc = Jsoup.connect(url).get();
        Product product = new Product();
        product.setTitle(doc.getElementsByClass("main-header").text());
        String text = doc.getElementsByClass("top-info").text();
        product.setManufacturer(text.substring(text.indexOf("Производитель")+15));
        product.setDescription(doc.getElementsByClass("arthidden").attr("itemprop", "description").html());
        Map<String, String> characteristics = new TreeMap<>();
        Elements elementsByClass = doc.getElementsByClass("tech-box").select("tr");
        for (Element byClass : elementsByClass) {
            characteristics.put(byClass.select("td").get(0).text(), byClass.select("td").get(1).text());
        }
        product.setCharacteristics(characteristics);
        Elements elementsByClass1 = doc.getElementsByClass("swiper-wrapper").get(0).select("[src]");
        List<String> photos = new ArrayList<>();
        for (Element element : elementsByClass1) {
            photos.add("https:" + element.attr("src"));
        }
        product.setPhotos(photos);

        Elements select = doc.getElementsByClass("forcars-box").select("div[class]").val("p").select("a[href]");
        if (select != null) {
            List<String> autos = new ArrayList<>();
            for (Element element : select) {
                autos.add(element.text());
            }
            product.setAutos(autos);
        }

        System.out.println("");
    }
}
