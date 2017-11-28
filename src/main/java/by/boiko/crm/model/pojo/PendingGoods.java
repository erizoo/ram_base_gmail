package by.boiko.crm.model.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "pending_goods")
public class PendingGoods {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "NAME")
    private String name;

    public PendingGoods() {
    }

    public PendingGoods(String sku, String name) {
        this.sku = sku;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
