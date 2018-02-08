package by.boiko.crm.model;

public class ItemsOrder {

    private String sku;
    private String amount;
    private String price;

    public ItemsOrder() {
    }

    public ItemsOrder(String sku, String amount, String price) {
        this.sku = sku;
        this.amount = amount;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
