package by.boiko.crm.model;

import java.util.List;

public class Parser {

    private String name;
    private String phoneNumber;
    private String address;
    private String notes;
    private List<ItemsOrder> listOrder;
    private String source;

    public Parser() {
    }

    public Parser(String source) {
        this.source = source;
    }

    public Parser(String name, String phoneNumber, String address, String notes, List<ItemsOrder> listOrder, String source) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.notes = notes;
        this.listOrder = listOrder;
        this.source = source;
    }

    public Parser(String name, String phoneNumber, String source) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.source = source;
    }

    public Parser(String name, String phoneNumber, List<ItemsOrder> listOrder, String source) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.listOrder = listOrder;
        this.source = source;
    }

    public Parser(String name, String phoneNumber, String address, List<ItemsOrder> listOrder, String source) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.listOrder = listOrder;
        this.source = source;
    }

    public Parser(String phoneNumber, String source) {
        this.phoneNumber = phoneNumber;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ItemsOrder> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<ItemsOrder> listOrder) {
        this.listOrder = listOrder;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
