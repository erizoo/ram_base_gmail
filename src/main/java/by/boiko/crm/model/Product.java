package by.boiko.crm.model;


public class Product {

    private String sku;
    private String nameProduct;
    private String priceProduct;
    private String amountProduct;

    public Product() {
    }

    public Product(String nameProduct, String priceProduct, String amountProduct) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.amountProduct = amountProduct;
    }

    public Product(String sku, String nameProduct, String priceProduct, String amountProduct) {
        this.sku = sku;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.amountProduct = amountProduct;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(String amountProduct) {
        this.amountProduct = amountProduct;
    }
}

