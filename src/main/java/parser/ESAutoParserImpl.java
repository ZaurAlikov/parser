package parser;

import com.ibm.icu.text.Transliterator;
import model.Product;
import model.UrlList;
import org.apache.commons.lang3.StringUtils;
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

public class ESAutoParserImpl extends MainParser {

    private final List<String> usedBaseUrls = new ArrayList<>();

    private boolean anotherColor = false;
    private String color = "";

    @Override
    public List<Product> processingUrls(UrlList urlList) throws IOException {
        List<Product> productList = new ArrayList<>();
        for (String url : urlList.getUrlList()) {
            Document doc = null;
            String baseUrl = "https://es-auto.ru";
            try {
                doc = Jsoup.connect(baseUrl + url).get();
            } catch (HttpStatusException e) {
                System.err.println("Fetching %s..." + url + " code: " + e.getStatusCode());
                if (e.getStatusCode() != 200) continue;
            } catch (SocketTimeoutException e) {
                System.err.println("Fetching %s..." + url + " SocketTimeoutException");
                continue;
            }
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
                    try {
                        productList.add(parse(doc, urlList.getCategoryName()));
                        usedBaseUrls.add(doc.baseUri());
                        Thread.sleep(100);
                    } catch (IllegalArgumentException e) {
                        System.err.printf("У товара на странице %s отсутствует артикул%n", doc.baseUri());
                        usedBaseUrls.add(doc.baseUri());
                    } catch (Exception e) {
                        System.err.println("При парсинге что-то пошло не так");
                        e.printStackTrace();
                    }
                    color = "";
                    anotherColor = true;
                }
                anotherColor = false;
            } else {
                if (usedBaseUrls.contains(doc.baseUri())) {
                    continue;
                }
                System.out.println("Fetching %s..." + doc.baseUri());
                try {
                    productList.add(parse(doc, urlList.getCategoryName()));
                    usedBaseUrls.add(doc.baseUri());
                    Thread.sleep(100);
                } catch (IllegalArgumentException e) {
                    System.err.printf("У товара на странице %s отсутствует артикул%n", doc.baseUri());
                    usedBaseUrls.add(doc.baseUri());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return productList;
    }

    @Override
    public void extractProductLinks(UrlList urlList) throws IOException {
        Document doc;
        String urlParamPagen = "?PAGEN_1=";
        List<String> productUrlList = new ArrayList<>();
        for (String url : urlList.getUrlList()) {
            int repeatCount = 0;
            int page = 1;
            while (repeatCount <= productUrlList.size()*3) {
                doc = Jsoup.connect(url + urlParamPagen + page).get();
                Elements elements = doc.getElementsByClass("relative product-list-item__link").select("a");
                for (Element element : elements) {
                    String href = element.attr("href");
                    if (!productUrlList.contains(href)) {
                        productUrlList.add(href);
                    } else {
                        ++repeatCount;
                    }
                }
                ++page;
            }
            System.out.println(page);
        }
        urlList.getUrlList().clear();
        urlList.getUrlList().addAll(productUrlList);
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
        if (characteristics.get("Артикул:") == null) {
            String art = doc.getElementsByClass("art").select("span").text().replace("Артикул:", "").trim();
            if (StringUtils.isBlank(art)) {
                throw new IllegalArgumentException("Отсутствует артикул");
            }
            characteristics.put("Артикул:", art);
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
