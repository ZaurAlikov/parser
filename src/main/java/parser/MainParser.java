package parser;

import model.Product;
import model.UrlList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.Utils.writeCsv;

public abstract class MainParser implements Parser {

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
}
