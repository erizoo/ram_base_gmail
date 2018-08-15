package by.boiko.crm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Api {

    @SerializedName("products")
    private List<Products> productList;
    @SerializedName("total")
    private int total;

    public Api() {
    }

    public Api(List<Products> productList, int total) {
        this.productList = productList;
        this.total = total;
    }

    public List<Products> getProductList() {
        return productList;
    }

    public void setProductList(List<Products> productList) {
        this.productList = productList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
