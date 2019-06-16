package parser;

import com.opencsv.CSVWriter;
import model.Product;
import model.category.Category;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATuningParserImpl implements Parser {
    @Override
    public void urlManager(Map<String, Category> categories) throws InterruptedException, IOException {
        Map<String, List<String>> catToUrlsMap = new HashMap<>();
        catToUrlsMap.put("Автомобильные боксы", getUrls("https://a-tuning.ru/catalog/avtomobilnye_boksy/?count=100"));
        catToUrlsMap.put("Велокрепления", getUrls("https://a-tuning.ru/catalog/krepleniya_dlya_velosipedov/?count=100"));
        catToUrlsMap.put("Грузовые корзины", getUrls("https://a-tuning.ru/catalog/gruzovye_korziny/?count=100"));
        catToUrlsMap.put("Крепления для лыж и сноубордов", getUrls("https://a-tuning.ru/catalog/krepleniya_dlya_lyzh_snoubordov/?count=100"));
        catToUrlsMap.put("Крепления для водного спортивного снаряжения", getUrls("https://a-tuning.ru/catalog/krepleniya_dlya_lodok_kayakov/?count=100"));
        List<String> accessories = new ArrayList<>();
        accessories.addAll(getUrls("https://a-tuning.ru/catalog/aksessuary_dlya_bagazhnikov/?count=100"));
        accessories.addAll(getUrls("https://a-tuning.ru/catalog/aksessuary_dlya_boksov_1/?count=100"));
        accessories.addAll(getUrls("https://a-tuning.ru/catalog/organayzery_i_sumki/?count=100"));
        catToUrlsMap.put("Аксессуары", accessories);

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
        product.setCategory(category);
        product.setArticule(doc.getElementsByClass("code-id").select("span").get(1).text().substring(9));
        product.setShortTitle(doc.getElementsByClass("product-info__head").select("h1").text());
        String price = doc.getElementsByClass("price").select("p").text();
        if (price != null && price.length() > 0) {
            product.setPrice(new BigDecimal(price.substring(0, price.length() - 3).replaceAll(" ", "")));
        }
        return product;
    }

    private List<String> getUrls(String parentUrl) {
        List<String> urls = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(parentUrl).get();
            Elements elementsByClass = doc.getElementsByClass("product-box");
            for (Element prod : elementsByClass) {
                urls.add("https://a-tuning.ru" + prod.getElementsByClass("item-href").attr("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    private void writeCsv(List<Product> products) throws IOException {
        String csv = "C:\\esAutoCSV\\data1.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv), ';', '"');
        for (Product product : products) {
            String [] prod = (
                    product.getArticule() + "%" +
                    product.getCategory() + "%" +
                    product.getShortTitle() + "%" +
                    product.getPrice()).split("%");
            writer.writeNext(prod);
        }
        writer.close();
    }

}
