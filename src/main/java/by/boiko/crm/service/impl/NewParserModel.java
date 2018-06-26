package by.boiko.crm.service.impl;

public class NewParserModel {

    private String sku;
    private String url;

    public NewParserModel() {
    }

    public NewParserModel(String sku, String url) {
        this.sku = sku;
        this.url = url;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
