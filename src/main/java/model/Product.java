package model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Product {
    private String articule;
    private String category;
    private String title;
    private String shortTitle;
    private String description;
    private String manufacturer;
    private String country;
    private BigDecimal price;
    private Map<String, String> characteristics;
    private List<String> photosUrl;
    private List<String> photosName;
    private List<String> autos;
    private List<String> crossSale;
    private List<String> upSale;
    private String videoUrl;
    private String seoUrl;
    private String baseUrl;

    public Product() {
    }

    public String getArticule() {
        return articule;
    }

    public void setArticule(String articule) {
        this.articule = articule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
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

    public List<String> getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(List<String> photosUrl) {
        this.photosUrl = photosUrl;
    }

    public List<String> getAutos() {
        return autos;
    }

    public void setAutos(List<String> autos) {
        this.autos = autos;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getPhotosName() {
        return photosName;
    }

    public void setPhotosName(List<String> photosName) {
        this.photosName = photosName;
    }

    public List<String> getCrossSale() {
        return crossSale;
    }

    public void setCrossSale(List<String> crossSale) {
        this.crossSale = crossSale;
    }

    public List<String> getUpSale() {
        return upSale;
    }

    public void setUpSale(List<String> upSale) {
        this.upSale = upSale;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
