package by.boiko.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Erizo on 08.02.2018.
 */
@Entity
@javax.persistence.Table(name = "market")
public class Market {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "URL")
    private String url;

    public Market() {
    }

    public Market(String sku, String url) {
        this.sku = sku;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Market{" +
                "sku='" + sku + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
