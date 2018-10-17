package ru.alcotester.parser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.alcotester.parser.config.AppConfig;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BuildMapCategory mapCategory = context.getBean(BuildMapCategory.class);
        Parser parser = context.getBean(Parser.class);
        parser.urlManager(mapCategory.getCategories());
    }
}
