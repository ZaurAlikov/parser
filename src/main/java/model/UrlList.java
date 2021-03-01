package model;

import java.util.ArrayList;
import java.util.List;

public class UrlList {

    private String categoryName;
    private List<String> urlList = new ArrayList<>();
    private boolean isCategoryUrls;

    public UrlList(String categoryName, boolean isCategoryUrls) {
        this.categoryName = categoryName;
        this.isCategoryUrls = isCategoryUrls;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public boolean isCategoryUrls() {
        return isCategoryUrls;
    }

    public void setCategoryUrls(boolean categoryUrls) {
        isCategoryUrls = categoryUrls;
    }
}
