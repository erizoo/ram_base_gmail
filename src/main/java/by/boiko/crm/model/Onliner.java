package by.boiko.crm.model;

import java.util.List;

public class Onliner {

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
