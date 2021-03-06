package by.boiko.crm.model;

import java.util.List;
import java.util.Map;

public class Order {

    private String name;
    private String number;
    private String email;
    private String address;
    private List<Product> nameProduct;
    private String error;
    private String nameProductCall;
    private String source;
    private String errorName;
    private Map listSku;
    private String comments;

    public Order(String error) {
        this.error = error;
    }

    public Order(String name, String number, String email, String address, String source, Map listSku, String comments) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.source = source;
        this.listSku = listSku;
        this.comments = comments;
    }

    public Order(String name, String number, String email, String source, String comments) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.source = source;
        this.comments = comments;
    }

    public Order(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Order(String name, String number, String nameProductCall, String source) {
        this.name = name;
        this.number = number;
        this.nameProductCall = nameProductCall;
        this.source = source;
    }

    public Order(String name, String number, String email, String address, List<Product> nameProduct, String source) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.nameProduct = nameProduct;
        this.source = source;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Map getListSku() {
        return listSku;
    }

    public void setListSku(Map listSku) {
        this.listSku = listSku;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getNameProductCall() {
        return nameProductCall;
    }

    public void setNameProductCall(String nameProductCall) {
        this.nameProductCall = nameProductCall;
    }

    public List<Product> getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(List<Product> nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
