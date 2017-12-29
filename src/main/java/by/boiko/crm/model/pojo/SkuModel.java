package by.boiko.crm.model.pojo;

import javax.persistence.*;

/**
 * Created by Erizo on 26.11.2017.
 */
@Entity
@javax.persistence.Table(name = "sku_model")
public class SkuModel {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "URL_API")
    private String urlApi;

    public SkuModel() {
    }

    public SkuModel(String sku, String name, String url, String urlApi) {
        this.sku = sku;
        this.name = name;
        this.url = url;
        this.urlApi = urlApi;
    }

    public SkuModel(String sku, String name, String url) {
        this.sku = sku;
        this.name = name;
        this.url = url;
    }

    public SkuModel(String sku, String url) {
        this.sku = sku;
        this.url = url;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
