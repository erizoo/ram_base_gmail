package by.boiko.crm.model;

import java.util.List;

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

    public Order() {

    }

    public Order(List<Product> nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Order(String error) {
        this.error = error;
    }

    public Order(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Order(String name, String email, String source ) {
        this.name = name;
        this.email = email;
        this.source = source;
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

    public Order(String name, String number, String email, String nameProductCall, String source) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.nameProductCall = nameProductCall;
        this.source = source;
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
