package parser;

import com.ibm.icu.text.Transliterator;
import model.Product;
import model.category.Category;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static utils.Utils.downloadImg;
import static utils.Utils.filterSeoUrl;
import static utils.Utils.writeCsv;

public class EurodetalParserImpl implements Parser {
    @Override
    public void urlManager(Map<String, Category> categories) throws InterruptedException, IOException {
        List<Product> productList = new ArrayList<>();
        Document doc = Jsoup.connect("https://bagazhniki.su/catalog/avtomobilnye-boksy").get();
        Elements elements = doc.getElementsByClass("pos_item__title text-uppercase").select("a[href]");
        for (Element element : elements) {
            String href = "https://bagazhniki.su" + element.attr("href");
            doc = Jsoup.connect(href).get();
            System.out.println("Fetching %s..." + doc.baseUri());
            productList.add(parse(doc, "Автомобильные боксы"));
            Thread.sleep(1000);
        }
        writeCsv(productList);
    }

    @Override
    public Product parse(Document doc, String category) throws IOException {
        Product product = new Product();
        product.setCategory(category);
        product.setTitle(doc.select("h1").text().replaceAll("`", ""));
        Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
        product.setSeoUrl(filterSeoUrl(toLatinTrans.transliterate(product.getTitle())));
        product.setBaseUrl(doc.baseUri());
        product.setCountry("Россия");
        product.setManufacturer("Евродеталь");
        Elements elementsByClass = doc.getElementsByClass("position_info col-md-6").select("p");
        StringBuilder desc = new StringBuilder();
        for (Element element : elementsByClass) {
            if (!element.text().equals("Заказать в 1 клик!")) {
                desc.append(element.wrap("<" + element.tag().getName() + "/>" + '\n'));
            }
        }
        product.setDescription(desc.toString());
        Elements elementsByClass1 = doc.getElementsByClass("pos_item__bottom-left i-price");
        String price = elementsByClass1.get(0).text();
        product.setPrice(new BigDecimal(price.substring(0, price.length() - 5)));

        Elements elementsByClass2 = doc.getElementsByClass("table table-bordered table-striped").select("tr");
        Map<String, String> characteristics = new TreeMap<>();
        for (Element byClass : elementsByClass2) {
            characteristics.put(byClass.select("td").get(0).text() + ":", byClass.select("td").get(1).text());
        }
        fillChar(characteristics);
        Elements elementsByClass3 = doc.getElementsByClass("position_info__list-item left-item");
        for (Element element : elementsByClass3) {
            if (element.text().contains("Артикул:")) {
                characteristics.put("Артикул:",element.text().substring(element.text().indexOf("Артикул: ")+9, element.text().length()));
            }
        }
        product.setCharacteristics(characteristics);
        Elements slides_item_item = doc.getElementsByClass("slides_item item").select("a[href]");
        List<String> images = new ArrayList<>();
        for (Element element : slides_item_item) {
            images.add("https://bagazhniki.su" + element.attr("href"));
        }
        images.add("https://bagazhniki.su" + doc.getElementsByClass("slides_item item active").select("a[href]").attr("href"));
        product.setPhotosUrl(images);
        downloadImg(product);

//        "Грузоподъемность (лыжи):|9 пар лыж"            "Количество лыж (пар):"
//        "Грузоподъемность (сноуборды):|4 сноуборда"     "Количество сноубордов (шт.):"
//        "Грузоподъемность:|50 кг"                       "Грузоподъемность (кг):"
//        "Максимальная длина лыж:|1800 мм"               "Максимальная длина лыж (см.):"
//        "Материал:|АБС-пластик"
//        "Объем:|390 л"                                  "Объем (л.):"
//        "Открытие бокса:|С двух сторон"                 "Система открытия:"  "Двухстороннее" "Одностороннее"
//        "Подходит для дуги Аэродинамика:|Да"
//        "Подходит для дуги Крыло:|Да"
//        "Подходит для дуги Сталь:|Да"
//        "Размеры:|1850х840х420 мм"                      "Внеш. размеры (длина, ширина, высота):"
//        "Система запирания:|Трехточечная"
//        "Система монтажа:|U - скоба"
//        "Способ крепления:|U-скоба"                     "Тип крепления:"
//        "Страна производства:|Россия"
//        "Цвет:|Чёрный"

        return product;
    }

    private void fillChar(Map<String, String> stringMap) {
        Map<String, String> characteristics = new TreeMap<>();
        Iterator<String> iterator = stringMap.keySet().iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals("Грузоподъемность (лыжи):")) {
                if (stringMap.get("Грузоподъемность (лыжи):").equals("Нет")) {
                    characteristics.put("Количество лыж (пар):", stringMap.get("Грузоподъемность (лыжи):"));
                } else {
                    characteristics.put("Количество лыж (пар):", stringMap.get("Грузоподъемность (лыжи):").substring(0,stringMap.get("Грузоподъемность (лыжи):").indexOf(" пар лыж")));
                }
                iterator.remove();
            }
            if (s.equals("Грузоподъемность (сноуборды):")) {
                if (stringMap.get("Грузоподъемность (сноуборды):").equals("Нет")) {
                    characteristics.put("Количество сноубордов (шт.):", stringMap.get("Грузоподъемность (сноуборды):"));
                } else {
                    characteristics.put("Количество сноубордов (шт.):", stringMap.get("Грузоподъемность (сноуборды):").substring(0,stringMap.get("Грузоподъемность (сноуборды):").indexOf(" сноуборда")));
                }
                iterator.remove();
            }
            if (s.equals("Грузоподъемность:")) {
                characteristics.put("Грузоподъемность (кг):", stringMap.get("Грузоподъемность:").substring(0,stringMap.get("Грузоподъемность:").indexOf(" кг")));
                iterator.remove();
            }
            if (s.equals("Максимальная длина лыж:")) {
                if (stringMap.get("Максимальная длина лыж:").equals("Нет")) {
                    characteristics.put("Максимальная длина лыж (мм):", stringMap.get("Максимальная длина лыж:"));
                } else {
                    String substring = stringMap.get("Максимальная длина лыж:").substring(0, stringMap.get("Максимальная длина лыж:").indexOf("мм"));
                    characteristics.put("Максимальная длина лыж (мм):", substring.replaceAll(" ", "") );
                }
                iterator.remove();
            }
            if (s.equals("Объем:")) {
                characteristics.put("Объем (л.):", stringMap.get("Объем:").substring(0,stringMap.get("Объем:").indexOf(" л")));
                iterator.remove();
            }
            if (s.equals("Открытие бокса:")) {
                characteristics.put("Система открытия:", stringMap.get("Открытие бокса:"));
                iterator.remove();
            }
            if (s.equals("Размеры:")) {
                if (stringMap.get("Размеры:").contains("мм")) {
                    characteristics.put("Внеш. размеры (длина, ширина, высота):", stringMap.get("Размеры:").substring(0,stringMap.get("Размеры:").indexOf(" мм")));
                }
                iterator.remove();
            }
            if (s.equals("Способ крепления:")) {
                characteristics.put("Тип крепления:", stringMap.get("Способ крепления:"));
                iterator.remove();
            }
        }
        stringMap.putAll(characteristics);
        characteristics.clear();
    }


}
