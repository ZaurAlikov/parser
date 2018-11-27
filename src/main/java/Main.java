import model.category.*;
import parser.EurodetalParserImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        ESAutoParserImpl ESAutoParserImpl = new ESAutoParserImpl();
        Map<String, Category> categories = new HashMap<>();
//        categories.put("Автобагажники", new CarTrunks());
//        categories.put("Автомобильные боксы", new CarBoxes());
//        categories.put("Автомобильные боксы на фаркоп", new CarBoxesOnFarcop());
//        categories.put("Велокрепления", new BikeRack());
//        categories.put("Крепления для лыж и сноубордов", new SkyFasteners());
//        categories.put("Грузовые корзины", new CargoBaskets());
//        categories.put("Крепления для водного спортивного снаряжения", new WaterSports());
//        categories.put("Аксессуары", new Accessories());
//        ESAutoParserImpl.urlManager(categories);

        EurodetalParserImpl evrodetalParser = new EurodetalParserImpl();
        evrodetalParser.urlManager(categories);
    }
}
