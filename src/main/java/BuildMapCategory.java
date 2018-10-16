import java.util.HashMap;
import java.util.Map;

public class BuildMapCategory {

    private Map<String, Category> categories = new HashMap<>();

    public BuildMapCategory(){
        categories.put("Автобагажники", new CarTrunks());
        categories.put("Автомобильные боксы", new CarBoxes());
        categories.put("Автомобильные боксы на фаркоп", new CarBoxesOnFarcop());
        categories.put("Велокрепления", new BikeRack());
        categories.put("Крепления для лыж и сноубордов", new SkyFasteners());
        categories.put("Грузовые корзины", new CargoBaskets());
        categories.put("Крепления для водного спортивного снаряжения", new WaterSports());
        categories.put("Аксессуары", new Accessories());
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Category> categories) {
        this.categories = categories;
    }
}
