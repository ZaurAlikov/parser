import java.util.List;
import java.util.Map;

public class Product {
    private String title;
    private String description;
    private String manufacturer;
    private Map<String, String> characteristics;
    private List<String> photos;
    private List<String> autos;

    public Product(String title, String description, String manufacturer, Map<String, String> characteristics, List<String> photos, List<String> autos) {
        this.title = title;
        this.description = description;
        this.manufacturer = manufacturer;
        this.characteristics = characteristics;
        this.photos = photos;
        this.autos = autos;
    }

    public Product() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Map<String, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getAutos() {
        return autos;
    }

    public void setAutos(List<String> autos) {
        this.autos = autos;
    }
}
