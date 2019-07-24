package parser;

import com.ibm.icu.text.Transliterator;
import model.Product;
import model.category.Category;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.*;

import static utils.Utils.downloadImg;
import static utils.Utils.filterSeoUrl;
import static utils.Utils.writeCsv;

public class THStoreParserImpl implements Parser {
    @Override
    public void urlManager(Map<String, Category> categories) throws InterruptedException, IOException {
        Map<String, List<String>> catToUrlsMap = new HashMap<>();
        catToUrlsMap.put("Городские рюкзаки для ноутбуков", getUrls("https://thstore.ru/catalog/bags-and-suitcases/urban-backpacks/"));
        catToUrlsMap.put("Спортивные сумки", getUrls("https://thstore.ru/catalog/bags-and-suitcases/suitcases/sportivnye-sumki/"));
        List<String> chemodans = new ArrayList<>();
        chemodans.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/suitcases/chemodany-na-kolesakh/"));
        chemodans.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/suitcases/bags-thule-revolve/"));
        chemodans.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/suitcases/bags-thule-subterra/"));
        catToUrlsMap.put("Чемоданы", chemodans);
        catToUrlsMap.put("Велорюкзаки", getUrls("https://thstore.ru/catalog/bags-and-suitcases/veloryukzaki/"));
        List<String> touristsBag = new ArrayList<>();
        touristsBag.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-alltrail/"));
        touristsBag.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/thule-capstone-backpacks//"));
        touristsBag.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-guidepost/"));
        touristsBag.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-landmark/"));
        touristsBag.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/ryukzaki-perenoski/"));
        touristsBag.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-stir/"));
        touristsBag.addAll(getUrls("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-versant/"));
        catToUrlsMap.put("Туристические рюкзаки", touristsBag);
        catToUrlsMap.put("Чехлы для лыж", getUrls("https://thstore.ru/catalog/bags-and-suitcases/covers-for-winter/dlya-lyzh/"));
        catToUrlsMap.put("Чехлы для сноубордов", getUrls("https://thstore.ru/catalog/bags-and-suitcases/covers-for-winter/dlya-snoubordov/"));
        catToUrlsMap.put("Рюкзаки для обуви и одежды", getUrls("https://thstore.ru/catalog/bags-and-suitcases/covers-for-winter/for-shoes-and-apparel/"));
        catToUrlsMap.put("Бампера для Macbook", getUrls("https://thstore.ru/catalog/bags-and-cases/for-laptops-and-tablets/for-tablets/"));
        catToUrlsMap.put("Чехлы-сумки для ноутбуков", getUrls("https://thstore.ru/catalog/bags-and-cases/for-laptops-and-tablets/for-laptops/"));
        catToUrlsMap.put("Чехлы для iPhone", getUrls("https://thstore.ru/catalog/bags-and-cases/for-smartphones/chekhly-dlya-iphone/"));
        catToUrlsMap.put("Чехлы для Samsung", getUrls("https://thstore.ru/catalog/bags-and-cases/for-smartphones/chekhly-dlya-samsung/"));
        catToUrlsMap.put("Чехлы для фотоаппаратов", getUrls("https://thstore.ru/catalog/bags-and-cases/for-cameras/"));
        catToUrlsMap.put("Аксессуары для рюкзаков", getUrls("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/accessories-for-backpacks/"));
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<String, List<String>> category : catToUrlsMap.entrySet()) {
            for (String url : category.getValue()) {
                try {
                    Document doc = Jsoup.connect(url).get();
                    System.out.println("Fetching %s..." + doc.baseUri());
                    productList.add(parse(doc, category.getKey()));
                } catch (HttpStatusException e) {
                    System.err.println("Fetching %s..." + url + " code: " + e.getStatusCode());
                } catch (SocketTimeoutException e) {
                    System.err.println("Fetching %s..." + url + " SocketTimeoutException");
                }
            }
        }
        writeCsv(productList);
    }

    @Override
    public Product parse(Document doc, String category) throws IOException {
        Product product = new Product();
        product.setSiteUrl("https://thstore.ru");
        product.setCategory(category);
        setProductPrice(doc, product);
        String title = doc.getElementsByClass("mainheader").text();
        product.setTitle(title);
        if (title.contains("(")) {
            title = title.substring(0, title.lastIndexOf("(")).trim();
        }
        product.setShortTitle(title);
        Elements elementsByClass = doc.getElementsByClass("tov-detail ");
        String artnumber = "";
        for (Element byClass : elementsByClass) {
            if (byClass.getElementsByClass("artnumber").size() > 0) {
                artnumber = byClass.getElementsByClass("artnumber").text().toLowerCase().replace("артикул:", "").trim();
                break;
            }
        }
        product.setArticule(artnumber);
        String videoUrl = "";
        for (Element iframe : doc.select("iframe")) {
            if (iframe.attr("src").contains("https://www.youtube")) {
                videoUrl = iframe.attr("src").substring(0, iframe.attr("src").indexOf("?")).trim();
            }
        }
        product.setVideoUrl(videoUrl);
        List<String> photosUrl = new ArrayList<>();
        for (Element element : doc.select("li.swiper-slide")) {
            for (Element element1 : element.select("a[href]")) {
                if (!element1.attr("href").equals("#video-box")) {
                    photosUrl.add(product.getSiteUrl() + element1.select("img[src]").get(0).attr("src"));
                }
            }
        }
        product.setPhotosUrl(photosUrl);
        product.setDescription(doc.select("section.tov-about").html());
        product.setManufacturer("Thule");
        product.setCountry("Швеция");
        product.setBaseUrl(doc.baseUri());
        Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
        product.setSeoUrl(filterSeoUrl(toLatinTrans.transliterate(product.getShortTitle())));
        product.setFunction(doc.select("div#tabs-1").html().replace("\n", "").replace("\"", "''"));
        Map<String, String> characteristics = new TreeMap<>();
        for (Element tr : doc.select("div#tabs-2").select("tr")) {
            characteristics.put(tr.select("th").text(), tr.select("td").text());
        }
        characteristics.put("Артикул:", product.getArticule());
        product.setCharacteristics(characteristics);
        downloadImg(product);
        return product;
    }

    private void setProductPrice(Document doc, Product product) {
        Elements left = doc.getElementsByClass("left");
        String strPrice = "";
        for (Element element : left) {
            if (element.getElementsByClass("price").size() > 0) {
                strPrice = element.getElementsByClass("price").text();
                break;
            }
        }
        if (strPrice.contains("\u20BD")) {
            strPrice = strPrice.replace("\u20BD", "").replace(" ", "").trim();
        }
        if (NumberUtils.isDigits(strPrice)) {
            product.setPrice(new BigDecimal(strPrice));
        } else {
            product.setPrice(BigDecimal.ZERO);
        }
    }

    private List<String> getUrls(String parentUrl) {
        List<String> urls = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(parentUrl).get();
            Elements catalog = doc.getElementsByClass("catalog").select("a[href]");
            for (Element element : catalog) {
                if (element.children().size() > 1) {
                    if (element.getElementsByClass("l-colors").size() > 0) {
                        for (Element element1 : element.getElementsByClass("l-colors")) {
                            for (Element element2 : element1.children()) {
                                urls.add("https://thstore.ru" + element2.select("span").attr("data-href"));
                            }
                        }
                    } else {
                        urls.add("https://thstore.ru" + element.attr("href"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
