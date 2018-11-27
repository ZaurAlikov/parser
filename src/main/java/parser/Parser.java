package parser;

import model.Product;
import model.category.Category;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public interface Parser {

    void urlManager(Map<String, Category> categories) throws InterruptedException, IOException;

    Product parse(Document doc, String category) throws IOException;
}
