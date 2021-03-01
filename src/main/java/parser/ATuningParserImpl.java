package parser;

import com.opencsv.CSVWriter;
import model.Product;
import model.UrlList;
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
import java.util.List;

public class ATuningParserImpl extends MainParser {

    @Override
    public void processing(List<UrlList> urlLists) throws IOException {
        List<Product> productList = new ArrayList<>();
        for (UrlList urlList : urlLists) {
            if (urlList.isCategoryUrls()) {
                extractProductLinks(urlList);
            }
            productList.addAll(processingUrls(urlList));
        }
        writeCsv(productList);
    }

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
            Elements elements = doc.getElementsByClass("product-box");
            for (Element element : elements) {
                String href = "https://a-tuning.ru" + element.getElementsByClass("item-href").attr("href");
                if (!productUrlList.contains(href)) {
                    productUrlList.add(href);
                }
            }
        }
        urlList.getUrlList().clear();
        urlList.getUrlList().addAll(productUrlList);
    }

    @Override
    public Product parse(Document doc, String category) throws IOException {
        Product product = new Product();
        product.setSiteUrl("https://a-tuning.ru");
        product.setCategory(category);
        product.setArticule(doc.getElementsByClass("code-id").select("span").get(1).text().substring(9));
        product.setShortTitle(doc.getElementsByClass("product-info__head").select("h1").text());
        String price = doc.getElementsByClass("price").select("p").text();
        if (price != null && price.length() > 0) {
            product.setPrice(new BigDecimal(price.substring(0, price.length() - 3).replaceAll(" ", "")));
        }
        return product;
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
