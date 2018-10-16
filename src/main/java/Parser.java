import com.ibm.icu.text.Transliterator;
import com.opencsv.CSVWriter;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

public class Parser {

    private boolean anotherColor = false;
    private String color = "";
    private List<String> usedBaseUrls = new ArrayList<>();

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
                        Thread.sleep(1000);
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
                    Thread.sleep(1000);
                }
            }
        }
        writeCsv(productList);
//        List<String> articleCrossSale = new ArrayList<>();
//        for (Product product : productList) {
//            for (String shortTitle : product.getCrossSale()) {
//                String article = findArticle(productList, shortTitle);
//                articleCrossSale.add(article);
//            }
//            product.getCrossSale().clear();
//            product.setCrossSale(articleCrossSale);
//        }
    }

    private Product parse(Document doc, String category) throws IOException {
        Product product = new Product();
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
            photos.add("https:" + element.attr("src"));
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
                downloadPdf(product, href);
            }
        }
        Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
        if (!color.equals("")) product.setSeoUrl(filterSeoUrl(toLatinTrans.transliterate(product.getShortTitle()+color)));
        else product.setSeoUrl(filterSeoUrl(toLatinTrans.transliterate(product.getShortTitle())));
        product.setBaseUrl(doc.baseUri());
        downloadImg(product);
        return product;
//        List<String> crossSaleList = new ArrayList<>();
//        Elements elementsByClass2 = doc.getElementsByClass("swiper-wrapper").get(2).select(".name");
//        for (Element element : elementsByClass2) {
//            crossSaleList.add(trimNew(element.text()));
//        }
//        product.setCrossSale(crossSaleList);
//        List<String> upSaleList = new ArrayList<>();
//        Elements elementsByClass3 = doc.getElementsByClass("swiper-wrapper").get(3).select(".name");
//        for (Element element : elementsByClass3) {
//            upSaleList.add(trimNew(element.text()));
//        }
//        product.setUpSale(upSaleList);
    }

    private void downloadImg(Product product) throws IOException {
        BufferedImage image;
        List<String> photosName = new ArrayList<>();
        int i = 1;
        for (String urlPic : product.getPhotosUrl()) {
            if (isRussian(urlPic) || urlPic.equals("https:")) {
                continue;
            }
            URL url = new URL(urlPic);
            image = ImageIO.read(url);

            if (product.getCharacteristics().get("Артикул:") == null) {
                product.getCharacteristics().put("Артикул:", "01-" + String.valueOf(nextInt()));
            }

            String imgName = product.getCharacteristics().get("Артикул:") + "_" + i;
            File catFolder = new File("C:\\esAutoImg\\" + product.getCategory());
            if (!catFolder.exists()) {
                catFolder.mkdir();
            }
            File goodsFolder = new File(catFolder + File.separator + validFoldName(product.getCharacteristics().get("Артикул:")));
            if (!goodsFolder.exists()) {
                goodsFolder.mkdir();
            }
            ImageIO.write(image, "jpg",new File(goodsFolder + File.separator + validFoldName(imgName) + ".jpg"));
            photosName.add(imgName + ".jpg");
            ++i;
        }
        product.setPhotosName(photosName);
    }

    private void downloadPdf(Product product, String urlPdf) throws IOException {
        if (isRussian(urlPdf)) {
            return;
        }
        URL url = new URL("https://es-auto.ru" + urlPdf);
        File catFolder = new File("C:\\esAutoPDF\\" + product.getCategory());
        if (!catFolder.exists()) {
            catFolder.mkdir();
        }
        File goodsFolder = new File(catFolder + File.separator + validFoldName(product.getShortTitle()) + " (" + product.getCharacteristics().get("Артикул:") + ")");
        if (!goodsFolder.exists()) {
            goodsFolder.mkdir();
        }
        String fileName = goodsFolder.getAbsolutePath() + File.separator + product.getCharacteristics().get("Артикул:") + ".pdf";
        ReadableByteChannel readableByteChannel = null;
        FileChannel fileChannel = null;
        FileOutputStream fileOutputStream = null;
        try {
            readableByteChannel = Channels.newChannel(url.openStream());
            if (!new File(fileName).exists()) {
                fileOutputStream = new FileOutputStream(fileName);
                fileChannel = fileOutputStream.getChannel();
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
        } finally {
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (readableByteChannel != null) {
                readableByteChannel.close();
            }
        }
    }

    private String trimNew(String text) {
        if (text.toLowerCase().endsWith(". new")) {
            return text.substring(0, text.length()-5);
        } else {
            return text;
        }
    }

    private String findArticle(List<Product> productList, String shortTitle) {
        for (Product product : productList) {
            if (product.getShortTitle().equals(shortTitle)) {
                return product.getCharacteristics().get("Артикул:");
            }
        }
        return shortTitle;
    }

    private String filterSeoUrl(String text) {
        text = text.replaceAll(" ", "-");
        text = text.replaceAll("'", "");
        text = text.replaceAll("ʺ", "");
        text = text.replaceAll("`", "");
        text = text.replaceAll(",", "");
        text = text.replaceAll("ʹ", "");
        text = text.replaceAll("=", "-");
        text = text.replaceAll("\\(", "");
        text = text.replaceAll("\\)", "");
        text = text.replaceAll("\"", "");
        return text.toLowerCase();
    }

    public void writeCsv(List<Product> products) throws IOException {
        String csv = "C:\\esAutoCSV\\data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv), ';', '"');
        String fullCsv = "C:\\esAutoCSV\\fullData.csv";
        CSVWriter writer1 = new CSVWriter(new FileWriter(fullCsv), ';', '"');
        for (Product product : products) {
            String imgPath = "";
            if (product.getPhotosName().size() > 0) {
                imgPath = product.getPhotosName().get(0);
            }
            String [] prod = ("" + "%" +
                    product.getTitle() + "%" +
                    product.getCharacteristics().get("Артикул:") + "%" +
                    product.getCharacteristics().get("Артикул:") + "%" +
                    product.getManufacturer() + "%" +
                    product.getPrice() + "%" +
                    "20" + "%" +
                    "Купить " + product.getTitle() + " в интернет магазине berivdorogu.ru" + "%" +
                    "" + "%" +
                    "✔ " + product.getTitle() + ". ✈Быстрая доставка по всей России. ☺Низкие цены. ★Официальная гарантия ✔Заказывайте у нас!" + "%" +
                    product.getDescription() + "%" +
                    "https://berivdorogu.ru/image/catalog/product/" + imgPath + "%" +
                    "1" + "%" +
                    "1" + "%" +
                    product.getSeoUrl() + "%" +
                    "" + "%" +
                    "" + "%" +
                    getCharacters(product) + "%" +
                    getImagesPath(product) + "%" +
                    "").split("%");
            writer.writeNext(prod);

            String [] prodFullInfo = (
                    product.getCategory() + "%" +
                    product.getTitle() + "%" +
                    product.getShortTitle() + "%" +
                    product.getCharacteristics().get("Артикул:") + "%" +
                    product.getManufacturer() + "%" +
                    product.getCountry() + "%" +
                    product.getPrice() + "%" +
                    product.getVideoUrl() + "%" +
                    product.getSeoUrl() + "%" +
                    getAuto(product) + "%" +
                    product.getDescription() + "%" +
                    "Купить " + product.getTitle() + " в интернет магазине berivdorogu.ru" + "%" +
                    "✔ " + product.getTitle() + ". ✈Быстрая доставка по всей России. ☺Низкие цены. ★Официальная гарантия ✔Заказывайте у нас!" + "%" +
                    getCharacters(product) + "%" +
                    "https://berivdorogu.ru/image/catalog/product/" + imgPath + "%" +
                    getImagesPath(product) + "%" +
                    product.getBaseUrl())
                    .split("%");
            writer1.writeNext(prodFullInfo);
        }
        writer.close();
        writer1.close();
    }

    private String getCharacters(Product product) {
        StringBuilder characters = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : product.getCharacteristics().entrySet()) {
            characters.append("Характеристики|").append(stringStringEntry.getKey()).append("|").append(stringStringEntry.getValue()).append('\n');
        }
        return characters.toString();
    }

    private String getImagesPath(Product product) {
        StringBuilder imagesPath = new StringBuilder();
        if (product.getPhotosUrl().size() < 2) {
            return "";
        }
        for (int i = 1; i < product.getPhotosName().size(); i++) {
            imagesPath.append("https://berivdorogu.ru/image/catalog/product/").append(product.getPhotosName().get(i)).append(",");
        }
        return imagesPath.substring(0, imagesPath.length() - 1);
    }

    private String getAuto(Product product) {
        StringBuilder autos = new StringBuilder();
        if (product.getAutos().size() == 0) {
            return "";
        }
        for (String auto : product.getAutos()) {
            autos.append(auto).append(",").append('\n');
        }
        return autos.substring(0, autos.length() - 1);
    }

    private boolean isRussian(String str)
    {
        char[] chr = str.toCharArray();
        for (int i = 0; i < chr.length; i++)
        {
            if (chr[i] >= 'А' && chr[i] <= 'я')
                return true;
        }
        return false;
    }

    private String validFoldName(String folder) {
        folder = folder.replaceAll("\\*", " ");
        folder = folder.replaceAll("\\|", " ");
        folder = folder.replaceAll(":", " ");
        folder = folder.replaceAll("\"", " ");
        folder = folder.replaceAll("<", " ");
        folder = folder.replaceAll(">", " ");
        folder = folder.replaceAll("\\?", " ");
        folder = folder.replaceAll("/", "_");
        folder = folder.replaceAll("\\)", " ");
        return folder;
    }

    public int nextInt() {
        Random random = new Random();
        return random.nextInt((9999 - 1000) + 1) + 1000;
    }
}
