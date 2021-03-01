package parser;

import com.ibm.icu.text.Transliterator;
import model.Product;
import model.UrlList;
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

public class THStoreParserImpl extends MainParser {

    @Override
    public List<Product> processingUrls(UrlList urlList) throws IOException {
        List<Product> productList = new ArrayList<>();
        for (String url : urlList.getUrlList()) {
            try {
                Document doc = Jsoup.connect(url).get();
                System.out.println("Fetching %s..." + doc.baseUri());
                productList.add(parse(doc, urlList.getCategoryName()));
            } catch (HttpStatusException e) {
                System.err.println("Fetching %s..." + url + " code: " + e.getStatusCode());
            } catch (SocketTimeoutException e) {
                System.err.println("Fetching %s..." + url + " SocketTimeoutException");
            }
        }
        return productList;
    }

    @Override
    public void extractProductLinks(UrlList urlList) throws IOException {
        Document doc;
        List<String> productUrlList = new ArrayList<>();
        for (String url : urlList.getUrlList()) {
            doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("catalog").select("a[href]");
            for (Element element : elements) {
                if (element.children().size() > 1) {
                    if (element.getElementsByClass("l-colors").size() > 0) {
                        for (Element element1 : element.getElementsByClass("l-colors")) {
                            for (Element element2 : element1.children()) {
                                String href = "https://thstore.ru" + element2.select("span").attr("data-href");
                                if (!productUrlList.contains(href)) {
                                    productUrlList.add(href);
                                }
                            }
                        }
                    } else {
                        String href = "https://thstore.ru" + element.attr("href");
                        if (!productUrlList.contains(href)) {
                            productUrlList.add(href);
                        }
                    }
                }
            }
        }
        urlList.getUrlList().clear();
        urlList.getUrlList().addAll(productUrlList);
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
}
