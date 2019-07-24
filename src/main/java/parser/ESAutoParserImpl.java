package parser;

import com.ibm.icu.text.Transliterator;
import model.Product;
import model.category.Category;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.*;

import static utils.Utils.*;

public class ESAutoParserImpl implements Parser {

    private boolean anotherColor = false;
    private String color = "";
    private List<String> usedBaseUrls = new ArrayList<>();

    @Override
    public void urlManager(Map<String, Category> categories) throws InterruptedException, IOException {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<String, Category> category : categories.entrySet()) {
            for (String url : category.getValue().getUrlList()) {
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (HttpStatusException e) {
                    System.err.println("Fetching %s..." + url + " code: " + e.getStatusCode());
                    if (e.getStatusCode() != 200) continue;
                } catch (SocketTimeoutException e) {
                    System.err.println("Fetching %s..." + url + " SocketTimeoutException");
                    continue;
                }
                String baseUrl = "https://es-auto.ru";
                Elements select = doc.getElementsByClass("sel-color").select("a[href]");
                if (select.size() > 0) {
                    for (Element element : select) {
                        String href = element.attr("href");
                        color = "-" + href.substring(href.substring(0, href.length()-1).lastIndexOf("/")+1, href.length()-1);
                        try {
                            doc = Jsoup.connect(baseUrl + href).get();
                        } catch (HttpStatusException e) {
                            System.err.println("Fetching %s..." + baseUrl + href + " code: " + e.getStatusCode());
                            if (e.getStatusCode() != 200) continue;
                        } catch (SocketTimeoutException e) {
                            System.err.println("Fetching %s..." + url + " SocketTimeoutException");
                            continue;
                        }
                        if (usedBaseUrls.contains(doc.baseUri())) {
                            continue;
                        }
                        System.out.println("Fetching %s..." + doc.baseUri());
                        productList.add(parse(doc, category.getKey()));
                        usedBaseUrls.add(doc.baseUri());
                        Thread.sleep(100);
                        color = "";
                        anotherColor = true;
                    }
                    anotherColor = false;
                } else {
                    if (usedBaseUrls.contains(doc.baseUri())) {
                        continue;
                    }
                    System.out.println("Fetching %s..." + doc.baseUri());
                    productList.add(parse(doc, category.getKey()));
                    usedBaseUrls.add(doc.baseUri());
                    Thread.sleep(100);
                }
            }
        }
        writeCsv(productList);
    }

    @Override
    public Product parse(Document doc, String category) throws IOException {
        Product product = new Product();
        product.setSiteUrl("https://es-auto.ru");
        product.setCategory(category);
        String title = doc.getElementsByClass("main-header").text();
        title = title.substring(0,1).toUpperCase() + title.substring(1);
        product.setTitle(title);
        String text = doc.getElementsByClass("top-info").text();
        if (text.length() > (text.indexOf("Производитель"))+14 && text.indexOf(",") > 0) {
            String man = text.substring(text.indexOf("Производитель")+15, text.indexOf(","));
            String country = text.substring(text.indexOf(",")+2);
            product.setManufacturer(man);
            product.setCountry(country);
        }
        String text1 = doc.getElementsByClass("tech-box").select("h2").text().substring(15);
        product.setShortTitle(trimNew(text1));
        String text2 = doc.getElementsByClass("price new-price").get(0).text().trim().replaceAll(" ", "");
        product.setPrice(new BigDecimal(text2.substring(0, text2.length() - 1)));
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
            photos.add(product.getSiteUrl() + element.attr("src"));
        }
        product.setPhotosUrl(photos);
        Elements select = doc.getElementsByClass("forcars-box").select("div[class]").val("p").select("a[href]");
        if (select != null) {
            List<String> autos = new ArrayList<>();
            for (Element element : select) {
                autos.add(element.text());
            }
            product.setAutos(autos);
        }
        product.setVideoUrl(doc.select("div.art-card-detail").select("iframe").attr("src"));
        String href = doc.getElementsByClass("dwnl-links").select("a[href]").attr("href");
        if (href != null && !href.equals("") && !anotherColor) {
            if (href.indexOf(".jpg") > 0) {
                System.err.println("Вместо pdf jpg скачать вручную");
            } else {
                downloadPdf(product, product.getSiteUrl() + href);
            }
        }
        Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
        if (!color.equals("")) product.setSeoUrl(filterSeoUrl(toLatinTrans.transliterate(product.getShortTitle()+color)));
        else product.setSeoUrl(filterSeoUrl(toLatinTrans.transliterate(product.getShortTitle())));
        product.setBaseUrl(doc.baseUri());
        downloadImg(product);
        return product;
    }
}
