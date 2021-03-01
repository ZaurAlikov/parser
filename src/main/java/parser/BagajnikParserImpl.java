package parser;

import com.ibm.icu.text.Transliterator;
import model.Product;
import model.UrlList;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.*;

import static utils.Utils.*;

public class BagajnikParserImpl extends MainParser {

    @Override
    public List<Product> processingUrls(UrlList urlList) throws IOException {
        List<Product> productList = new ArrayList<>();
        for (String url : urlList.getUrlList()) {
            try {
                Document doc = Jsoup.connect(url).get();
                System.out.println("Fetching %s..." + doc.baseUri());
                productList.add(parse(doc, urlList.getCategoryName()));
                Thread.sleep(1000);
            } catch (HttpStatusException e) {
                System.err.println("Fetching %s..." + url + " code: " + e.getStatusCode());
            } catch (SocketTimeoutException e) {
                System.err.println("Fetching %s..." + url + " SocketTimeoutException");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    @Override
    public void extractProductLinks(UrlList urlList) {
    }

    @Override
    public Product parse(Document doc, String category) throws IOException {
        Product product = new Product();
        Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
        product.setCategory(category);
        String title = doc.select("h1").text();
        product.setTitle(title);
        if (category.equals("Автомобильные боксы")) {
            title = title.substring(0, title.indexOf("(") - 31);
            product.setShortTitle(title);
            product.setSeoUrl(filterSeoUrl(toLatinTrans.transliterate(product.getShortTitle())));
        } else {

            String shortTitle;
            if (title.contains("(арт. ")) {
                product.setShortTitle(title.substring(0, title.indexOf("(арт. ")-1));
            } else {
                product.setShortTitle(title);
            }
            product.setSeoUrl(filterSeoUrl(toLatinTrans.transliterate(product.getShortTitle())));
        }
        product.setManufacturer(doc.getElementsByClass("breadcrumbs-light").select("a[href]").get(0).text());
        product.setCountry("Россия");
        product.setBaseUrl(doc.baseUri());
        product.setDescription("");
        product.setPrice(new BigDecimal(doc.getElementsByClass("product__price").attr("content")));
        Elements elementsByClass = doc.getElementsByClass("product-parameters").select("li");
        Map<String, String> characteristics = new TreeMap<>();
        for (Element byClass : elementsByClass) {
            characteristics.put(byClass.getElementsByClass("parameter").text(), byClass.getElementsByClass("value").text());
        }
        String art = product.getTitle().substring(product.getTitle().indexOf("(арт. ")+6, product.getTitle().length()-1);
        characteristics.put("Артикул:", art);
        fillChar(characteristics);
        product.setCharacteristics(characteristics);
        Elements slides_item_item = doc.getElementsByClass("product-photo-big").select("a[href]");
        List<String> images = new ArrayList<>();
        for (Element element : slides_item_item) {
            images.add(element.attr("href"));
        }
        product.setPhotosUrl(images);
        downloadImg(product);
        String pdfUrl = doc.getElementsContainingOwnText("Инструкция по установке pdf").attr("href");
        if (!pdfUrl.equals("")){
            downloadPdf(product, pdfUrl);
        }
        String videoUrl = doc.getElementsContainingOwnText("Видеоинструкция по установке").attr("href");
        if (!videoUrl.equals("")) {
            Document urlDoc = Jsoup.connect(videoUrl).get();
            String url = urlDoc.getElementsByClass("single-video").select("iframe").attr("src");
            product.setVideoUrl(url);
        }
        return product;
    }

    private void fillChar(Map<String, String> stringMap) {
        Map<String, String> characteristics = new TreeMap<>();
        Iterator<String> iterator = stringMap.keySet().iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals("Вес бокса")) {
                if (stringMap.get("Вес бокса").contains(" кг")){
                    characteristics.put("Вес (кг):", stringMap.get("Вес бокса").substring(0,stringMap.get("Вес бокса").indexOf(" кг")));
                } else characteristics.put("Вес (кг):", stringMap.get("Вес бокса"));
                iterator.remove();
            }
            if (s.equals("Допустимая нагрузка")) {
                characteristics.put("Грузоподъемность (кг):", stringMap.get("Допустимая нагрузка").substring(0,stringMap.get("Допустимая нагрузка").indexOf(" кг")));
                iterator.remove();
            }
            if (s.equals("Открытие")) {
                characteristics.put("Система открытия:", stringMap.get("Открытие"));
                iterator.remove();
            }
            if (s.equals("Материал")) {
                characteristics.put("Материал:", stringMap.get("Материал"));
                iterator.remove();
            }
            if (s.equals("Объем бокса")) {
                characteristics.put("Объем (л.):", stringMap.get("Объем бокса").substring(0,stringMap.get("Объем бокса").indexOf(" л")));
                iterator.remove();
            }
            if (s.equals("Замок")) {
                characteristics.put("Система запирания:", stringMap.get("Замок"));
                iterator.remove();
            }
            if (s.equals("Цвет")) {
                characteristics.put("Цвет:", stringMap.get("Цвет").substring(0,1).toUpperCase() + stringMap.get("Цвет").substring(1));
                iterator.remove();
            }
            if (s.equals("Внешний размер бокса")) {
                characteristics.put("Внеш. длина (см.):", stringMap.get("Внешний размер бокса").substring(0,3));
                characteristics.put("Внеш. ширина (см.):", stringMap.get("Внешний размер бокса").substring(4,6));
                characteristics.put("Внеш. высота (см.):", stringMap.get("Внешний размер бокса").substring(7,9));
                iterator.remove();
            }
        }
        if (characteristics.get("Система открытия:") != null && characteristics.get("Система открытия:").equals("боковое (двухстороннее)")) {
            characteristics.replace("Система открытия:", "Двухсторонняя");
        }
        if (characteristics.get("Система запирания:") != null && characteristics.get("Система запирания:").equals("центральный, с ключом, фиксирует крышку в 3х местах")) {
            characteristics.replace("Система запирания:", "Трехточечная");
        }
        stringMap.putAll(characteristics);
        characteristics.clear();
    }
}


