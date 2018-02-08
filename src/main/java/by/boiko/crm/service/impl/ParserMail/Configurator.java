package by.boiko.crm.service.impl.ParserMail;

import by.boiko.crm.model.ItemsOrder;

import java.util.List;

public class Configurator {

    private String name;
    private String phoneNumber;
    private String address;
    private String notes;
    private List<ItemsOrder> listOrder;

    public Configurator() {
    }

    public Configurator(String name, String phoneNumber, String address, String notes, List<ItemsOrder> listOrder) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.notes = notes;
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

    public List<ItemsOrder> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<ItemsOrder> listOrder) {
        this.listOrder = listOrder;
    }

    @Override
    public String toString() {
        return "Configurator{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", notes='" + notes + '\'' +
                ", listOrder=" + listOrder +
                '}';
    }
}
