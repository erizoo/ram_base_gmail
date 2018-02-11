package by.boiko.crm.service.impl.ParserMail;

import by.boiko.crm.model.ItemsOrder;

import java.util.List;

/**
 * Created by Erizo on 11.02.2018.
 */
public class DealBy {

    private String name;
    private String phoneNumber;
    private String address;
    private String notes;
    private String email;
    private List<ItemsOrder> listOrder;

    public DealBy() {
    }

    public DealBy(String name, String phoneNumber, String address, String notes, String email, List<ItemsOrder> listOrder) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.notes = notes;
        this.email = email;
        this.listOrder = listOrder;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ItemsOrder> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<ItemsOrder> listOrder) {
        this.listOrder = listOrder;
    }
}
