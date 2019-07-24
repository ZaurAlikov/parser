import model.category.*;
import model.category.bagajnik.*;
import model.category.esauto.Accessories;
import model.category.esauto.BikeRack;
import parser.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Map<String, Category> categories = new HashMap<>();

//        ESAutoParserImpl ESAutoParserImpl = new ESAutoParserImpl();
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

//        BagajnikParserImpl bagajnikParser = new BagajnikParserImpl();
//        categories.put("Автомобильные боксы", new CarBoxesBgjnk());
//        categories.put("Велокрепления", new BikeRackBgjnk());
//        categories.put("Крепления для лыж и сноубордов", new SkyFastenersBgjnk());
//        categories.put("Грузовые корзины", new CargoBasketsBgjnk());
//        categories.put("Аксессуары", new AccessoriesBgjnk());
//        bagajnikParser.urlManager(categories);

//        ATuningParserImpl aTuningParser = new ATuningParserImpl();
//        aTuningParser.urlManager(null);

//        THStoreParserImpl thStoreParser = new THStoreParserImpl();
//        thStoreParser.urlManager(null);

    }
}
