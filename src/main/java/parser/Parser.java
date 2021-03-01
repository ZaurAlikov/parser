package parser;

import model.Product;
import model.UrlList;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface Parser {

    void processing(List<UrlList> urlLists) throws IOException;

    List<Product> processingUrls(UrlList urlList) throws IOException;

    void extractProductLinks(UrlList urlList) throws IOException;

    Product parse(Document doc, String category) throws IOException;
}
