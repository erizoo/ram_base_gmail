package by.boiko.crm.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class User {

    private String nameCategory, nameManufacturer, nameModel, skuMarket, skuRam;
    private String numberPopular, linkMarket;

    public User() {
    }

    public User(String nameCategory, String nameManufacturer, String nameModel, String skuMarket, String skuRam, String numberPopular, String linkMarket) {
        this.nameCategory = nameCategory;
        this.nameManufacturer = nameManufacturer;
        this.nameModel = nameModel;
        this.skuMarket = skuMarket;
        this.skuRam = skuRam;
        this.numberPopular = numberPopular;
        this.linkMarket = linkMarket;
    }

    public String getStringNull() {
        return "null";
    }

    public String getLinkMarket() {
        return linkMarket;
    }

    public void setLinkMarket(String linkMarket) {
        this.linkMarket = linkMarket;
    }

    public String getSkuRam() {
        return skuRam;
    }

    public void setSkuRam(String skuRam) {
        this.skuRam = skuRam;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameManufacturer() {
        return nameManufacturer;
    }

    public void setNameManufacturer(String nameManufacturer) {
        this.nameManufacturer = nameManufacturer;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public String getSkuMarket() {
        return skuMarket;
    }

    public void setSkuMarket(String skuMarket) {
        this.skuMarket = skuMarket;
    }

    public String getNumberPopular() {
        return numberPopular;
    }

    public void setNumberPopular(String numberPopular) {
        this.numberPopular = numberPopular;
    }

    @Override
    public String toString() {
        return "User{" +
                "nameCategory='" + nameCategory + '\'' +
                ", nameManufacturer='" + nameManufacturer + '\'' +
                ", nameModel='" + nameModel + '\'' +
                ", skuMarket='" + skuMarket + '\'' +
                ", numberPopular=" + numberPopular +
                '}';
    }
}