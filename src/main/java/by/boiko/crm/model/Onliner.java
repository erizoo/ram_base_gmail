package by.boiko.crm.model;

import java.util.ArrayList;
import java.util.List;

public class Onliner {

    private String sku;
    private String description;
    private List<Review> reviews;
    private List<Table> tables;
    private List<String> images;

    public Onliner() {
    }

    public Onliner(List<Review> reviews, List<Table> tables, List<String> images) {
        this.reviews = reviews;
        this.tables = tables;
        this.images = images;
    }

    public Onliner(String sku, String description, List<Table> tables, List<String> images) {
        this.sku = sku;
        this.description = description;
        this.tables = tables;
        this.images = images;
    }

    public Onliner(String sku, List<Review> reviews, ArrayList<Table> description, List<String> images) {
        this.sku = sku;
        this.reviews = reviews;
        this.tables = tables;
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
