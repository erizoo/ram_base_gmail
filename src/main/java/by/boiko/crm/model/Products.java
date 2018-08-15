package by.boiko.crm.model;

import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("url")
    private String url;

    public Products() {
    }

    public Products(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
