package by.boiko.crm.model;

import java.util.List;

public class Onliner {



    private String amountStars;
    private String review;
    private String plus;
    private String minus;

    public Onliner() {
    }

    public Onliner(String amountStars, String review, String plus, String minus) {
        this.amountStars = amountStars;
        this.review = review;
        this.plus = plus;
        this.minus = minus;
    }

    public String getAmountStars() {
        return amountStars;
    }

    public void setAmountStars(String amountStars) {
        this.amountStars = amountStars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getMinus() {
        return minus;
    }

    public void setMinus(String minus) {
        this.minus = minus;
    }

    @Override
    public String toString() {
        return "Onliner{" +
                "amountStars='" + amountStars + '\'' +
                ", review='" + review + '\'' +
                ", plus='" + plus + '\'' +
                ", minus='" + minus + '\'' +
                '}';
    }
}
