import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BuildMapCategory mapCategory = (BuildMapCategory)context.getBean("mapCategory");
        Parser parser = new Parser();
        parser.urlManager(mapCategory.getCategories());
    }
}
